package com.springcloud.dubbo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API模块，存放Dubbo服务接口和模型定义，非必要，
 * 这里创建仅为更好的代码重用以及接口、模型规格控制管理。
 */
@SpringBootApplication
public class DubboApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboApiApplication.class, args);
    }

}
