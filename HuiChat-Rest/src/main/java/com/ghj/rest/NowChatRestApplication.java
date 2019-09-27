package com.ghj.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author gehj
 * @date 2019/6/24 19:05
 */
@EnableEurekaClient
//@Import(FdfsClientConfig.class)
@MapperScan(basePackages = {"com.ghj.rest.dao"})
@SpringBootApplication
public class NowChatRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(NowChatRestApplication.class, args);
    }
}
