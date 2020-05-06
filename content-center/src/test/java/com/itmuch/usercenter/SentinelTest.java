package com.itmuch.usercenter;

import org.springframework.web.client.RestTemplate;

public class SentinelTest {
    public static void main(String[] args) throws InterruptedException {
        // 关联 另一个接口，查询接口的被修改的接口限流
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10000; i++) {
            String object = restTemplate.getForObject("http://localhost:8010/actuator/sentinel", String.class);
            Thread.sleep(500);
        }
    }

    public static void main1(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10000; i++) {
            String object = restTemplate.getForObject("http://localhost:8010/test-a", String.class);
            System.out.println("-----" + object + "-----");
        }
    }
}
