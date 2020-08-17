package com.itmuch.usercenter.service.user;

import com.itmuch.usercenter.dao.bonus.BonusEventLogMapper;
import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.itmuch.usercenter.domain.dto.user.UserLoginDTO;
import com.itmuch.usercenter.domain.entity.bonus.BonusEventLog;
import com.itmuch.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    public User findById(Integer id) {
        // select * from user where id = #{id}
        return this.userMapper.selectByPrimaryKey(id);
    }

    /**
     * 独立封装 支持本地事务
     * rocketMQ事务消息如果半消息提交成功，且本地事务执行成功，消费端消费失败会进行重试。最后进入死信队列
     * @param msgDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO msgDTO) {




        // 1. 为用户加积分
        Integer userId = msgDTO.getUserId();
        Integer bonus = msgDTO.getBonus();
        User user = this.userMapper.selectByPrimaryKey(userId);

        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateByPrimaryKeySelective(user);

        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
            BonusEventLog.builder()
                .userId(userId)
                .value(bonus)
                .event(msgDTO.getEvent())
                .createTime(new Date())
                .description(msgDTO.getDescription())
                .build()
        );

// 主动的抛出了一个异常来模拟消息的消费失败。
//        try {
//            log.error("啦啦啦啦,这里出现了异常...");
//            throw new Exception("啦啦啦啦");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        log.info("积分添加完毕...");
    }

    public User login(UserLoginDTO loginDTO, String openId){
        User user = this.userMapper.selectOne(
            User.builder()
                .wxId(openId)
                .build()
        );
        if (user == null) {
            User userToSave = User.builder()
                .wxId(openId)
                .bonus(300)
                .wxNickname(loginDTO.getWxNickname())
                .avatarUrl(loginDTO.getAvatarUrl())
                .roles("user")
                .createTime(new Date())
                .updateTime(new Date())
                .build();
            this.userMapper.insertSelective(
                userToSave
            );
            return userToSave;
        }
        return user;
    }
}
