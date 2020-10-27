package com.ghj.keep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class HuiChatAccessStarter {
    public static void main(String[] args) {
        SpringApplication.run(HuiChatAccessStarter.class,args);
    }
}
