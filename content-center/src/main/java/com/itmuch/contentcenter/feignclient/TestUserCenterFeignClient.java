package com.itmuch.contentcenter.feignclient;

import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign 的多参数请求构造
 * SpringMVC 注解
 */
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {

    /**
     * get请求需要这么写
     * @param userDTO
     * @return
     */
    @GetMapping("/q")
    UserDTO query(@SpringQueryMap UserDTO userDTO);

}
