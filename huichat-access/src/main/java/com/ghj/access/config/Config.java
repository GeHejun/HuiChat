package com.ghj.access.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${netty.port}")
    private int port;

    public int getPort() {
        return port;
    }

}
