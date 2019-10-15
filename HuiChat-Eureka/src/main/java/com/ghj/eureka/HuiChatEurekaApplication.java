package com.ghj.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * @author GeHejun
 */
@EnableEurekaServer
@SpringBootApplication
public class HuiChatEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HuiChatEurekaApplication.class, args);
    }
}
