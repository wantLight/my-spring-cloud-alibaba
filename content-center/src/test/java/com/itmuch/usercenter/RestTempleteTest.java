package com.itmuch.usercenter;

import com.itmuch.contentcenter.ContentCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = ContentCenterApplication.class)
@RunWith(SpringRunner.class)
public class RestTempleteTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void url(){
        String userDTO = restTemplate.getForObject(
                "http://baidu1.com/users",String.class
        );
        System.out.println(userDTO);
    }

}
