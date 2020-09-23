package com.itmuch.contentcenter.rocketmq;

import com.alibaba.fastjson.JSON;
import com.itmuch.contentcenter.dao.messaging.RocketmqTransactionLogMapper;
import com.itmuch.contentcenter.domain.dto.content.ShareAuditDTO;
import com.itmuch.contentcenter.domain.entity.messaging.RocketmqTransactionLog;
import com.itmuch.contentcenter.service.content.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * rocketmq 自带分布式事务功能
 *
 * 1、在扣款之前，先发送预备消息   (第一步先给 Broker 发送事务消息即半消息,并且 RocketMQ 的发送方会提供一个反查事务状态接口，
 * 如果一段时间内半消息没有收到任何操作请求，那么 Broker 会通过反查接口得知发送方事务是否执行成功，然后执行 Commit 或者 RollBack 命令)
 * 2、发送预备消息成功后，执行本地扣款事务
 * 3、扣款成功后，再发送确认消息
 * 4、消息端（加钱业务）可以看到确认消息，消费此消息，进行加钱
 *
 */
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

    private final ShareService shareService;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    /**
     * 发送预备消息成功后，执行本地事务
     * 执行本地事务 - 记录本地事务日志
     *
     * RocketMQ 提供了事务消息的功能，我们只需要定义好事务反查接口即可。
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        MessageHeaders headers = msg.getHeaders();

        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer shareId = Integer.valueOf((String) headers.get("share_id"));

        String dtoString = (String) headers.get("dto");
        ShareAuditDTO auditDTO = JSON.parseObject(dtoString, ShareAuditDTO.class);

        try {
            this.shareService.auditByIdWithRocketMqLog(shareId, auditDTO, transactionId);
            // 发送确认消息 -- 在这挂了的话 需要事务回查
            // ...
            // 如果是 Commit 那么订阅方就能收到这条消息，然后再做对应的操作，做完了之后再消费这条消息即可。
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            // 如果是 RollBack 那么订阅方收不到这条消息，等于事务就没执行过。
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 事务反查接口
     * 本地事务检查接口（回查）
     *
     * kill -9 后会进来回查
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        MessageHeaders headers = msg.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);

        // select * from xxx where transaction_id = xxx
        RocketmqTransactionLog transactionLog = this.rocketmqTransactionLogMapper.selectOne(
            RocketmqTransactionLog.builder()
                .transactionId(transactionId)
                .build()
        );
        log.info("本地事务检查接口（回查）{}",transactionLog==null);
        if (transactionLog != null) {
            // 本地事务检查成功，commit
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
