package com.ghj.access.keep;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${keep.server.port}")
    private int keepServerPort;

    @Value("${registry.server.port}")
    private int registryServerPort;

    @Value("${registry.server.url}")
    private String registryServerHost;


    public int getKeepServerPort() {
        return keepServerPort;
    }

    public int getRegistryServerPort() {
        return registryServerPort;
    }

    public String getRegistryServerHost() {
        return registryServerHost;
    }
}
