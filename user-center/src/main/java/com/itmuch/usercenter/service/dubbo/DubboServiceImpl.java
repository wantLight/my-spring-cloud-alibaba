package com.itmuch.usercenter.service.dubbo;


import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.entity.user.User;
import com.itmuch.usercenter.service.DubboService;
import lombok.RequiredArgsConstructor;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Service注解仅声明该Java服务（本地）实现为Dubbo服务。
 * 建议由服务提供方设置超时, 在 Provider 上尽量多配置 Consumer 端属性
 *  *  timeout 方法调用超时
 *  *  retries 失败重试次数，缺省是 2 [2]
 *  *  loadbalance 负载均衡算法 [3]，
 *  缺省是随机 random。还可以有轮询 roundrobin、最不活跃优先
 *  [4] leastactive 等
 *  *  actives 消费者端，最大并发调用限制，即当 Consumer 对一个服务的并发调用到上限后，新调用会阻塞直到超时
 */

@Service(timeout = 3000,
retries = 3,loadbalance = "random", actives = 5)
public class DubboServiceImpl implements DubboService {

//    private  UserMapper userMapper;

    @Override
    public String findById(Integer id) {
        // this.userMapper.selectByPrimaryKey(id)
        return "6666666666666666666666666";
    }

}
