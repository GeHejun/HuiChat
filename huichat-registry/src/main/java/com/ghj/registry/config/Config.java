package com.ghj.registry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${registry.server.port}")
    private int registryServerPort;

    public int getRegistryServerPort() {
        return registryServerPort;
    }
}
