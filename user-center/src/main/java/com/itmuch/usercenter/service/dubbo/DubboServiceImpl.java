package com.itmuch.usercenter.service.dubbo;


import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.entity.user.User;
import com.itmuch.usercenter.service.DubboService;
import lombok.RequiredArgsConstructor;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Service注解仅声明该Java服务（本地）实现为Dubbo服务。
 */

@Service
public class DubboServiceImpl implements DubboService {

//    private  UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        // this.userMapper.selectByPrimaryKey(id)
        return new User();
    }

}
