package com.itmuch.contentcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Collections;

// 扫描mybatis哪些包里面的接口
@MapperScan("com.itmuch.contentcenter.dao")
@SpringBootApplication
//使用feign - 远程http调用
@EnableFeignClients
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }



    @Bean
    // 为RestTemplate增加Ribbon
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
//        template.setInterceptors(
//            Collections.singletonList(
//                new TestRestTemplateTokenRelayInterceptor()
//            )
//        );
        return template;
    }
}
