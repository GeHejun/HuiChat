package com.ghj.common.mq;

import com.alibaba.fastjson.JSON;
import com.ghj.common.base.Constant;
import com.ghj.common.util.PropertiesUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class SendUtil {

        public static void sendForQueue() {
            try {
                String rabbitIP = PropertiesUtil.getInstance().getValue( "rabbit.host", "127.0.0.1");
                String rabbitPort = PropertiesUtil.getInstance().getValue("rabbit.port", "5672");
                String rabbitUsername = PropertiesUtil.getInstance().getValue( "rabbit.username", "guest");
                String rabbitPassword = PropertiesUtil.getInstance().getValue("rabbit.password", "guest");
                String rabbitVirtualHost = PropertiesUtil.getInstance().getValue( "rabbitmq.virtual-host", "/");
                // 获取到连接以及mq通道
                Connection connection = ConnectionUtil.getConnect(rabbitIP, Integer.parseInt(rabbitPort), rabbitUsername, rabbitVirtualHost, rabbitPassword);
                // 从连接中创建通道
                Channel channel = connection.createChannel();
                // 声明（创建）队列
                channel.queueDeclare(Constant.QUEUE_A, true, false, false, null);
//                channel.basicPublish(Constant.EXCHANGE_A, Constant.ROUTING_KEY_A, null, JSON.toJSONString().getBytes());
                //关闭通道和连接
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

}
