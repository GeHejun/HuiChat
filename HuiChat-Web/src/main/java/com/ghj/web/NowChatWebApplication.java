package com.ghj.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gehj
 * @date 2019/6/24 19:05
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NowChatWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(NowChatWebApplication.class, args);
    }
}
