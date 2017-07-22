package com.org.spring.boot.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessagingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessagingTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void setup(){
        System.out.println("Integration Test");
    }
}
