package com.itmuch.contentcenter.feignclient;

import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import com.itmuch.contentcenter.feignclient.fallback.UserCenterFeignClientFallback;
import com.itmuch.contentcenter.feignclient.fallbackfactory.UserCenterFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign.Builder Feign的入口
 * Client 底层用什么去请求 - 双击shift 默认HttpURLConnection，没有连接池(可以优化)
 * LoadBalancerFeignClient
 *
 * Contract - 注解支持
 *
 * fallback - 一旦远程调用失败了，进入fallback
 *fallbackFactory - 可以拿到异常，功能强大
 */
@FeignClient(name = "user-center", fallbackFactory = UserCenterFeignClientFallbackFactory.class)
public interface UserCenterFeignClient {
    /**
     * Feign 自动构建
     * http://user-center/users/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable Integer id);
}

