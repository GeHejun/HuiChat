package com.ghj.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/15 14:05
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HuiChatWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuiChatWebApplication.class, args);
    }

}
