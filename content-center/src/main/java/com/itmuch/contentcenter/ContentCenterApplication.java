package com.itmuch.contentcenter;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Collections;

// 扫描mybatis哪些包里面的接口
@MapperScan("com.itmuch.contentcenter.dao")
@SpringBootApplication
//使用feign - 远程http调用
@EnableFeignClients
//spring cloud stream使用
//@EnableBinding(Source.class)
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }


    /**
     * 双击shift搜索
     * SentinelBeanPostProcessor
     * Spring Bean 后處理器
     * 反射
     * RestTemplate攔截器
     * @return
     */
    @Bean
    // 为RestTemplate增加Ribbon
    //@LoadBalanced
    // 为RestTemplate整合Sentinel
    //@SentinelRestTemplate
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
