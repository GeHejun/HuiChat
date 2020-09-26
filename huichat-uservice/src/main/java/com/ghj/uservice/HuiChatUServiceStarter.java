package com.ghj.uservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class HuiChatUServiceStarter {

    public static void main(String[] args) {
        SpringApplication.run(HuiChatUServiceStarter.class, args);
    }
}
