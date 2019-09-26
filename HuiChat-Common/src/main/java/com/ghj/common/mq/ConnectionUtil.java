package com.ghj.common.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class ConnectionUtil {
    public static Connection getConnect(String host, int port, String userName,String virtualHost, String password) throws IOException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(host);
        //端口
        factory.setPort(port);
        factory.setVirtualHost(virtualHost);
        factory.setUsername(userName);
        factory.setPassword(password);
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
