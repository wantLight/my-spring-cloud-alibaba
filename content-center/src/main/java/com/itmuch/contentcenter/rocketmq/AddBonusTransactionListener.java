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
 * 1、在扣款之前，先发送预备消息
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
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
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
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
