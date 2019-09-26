package com.ghj.common.netty;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/26 14:56
 */
public interface Connector {
    /**
     * 开启连接
     * @param port
     */
    void start(int port);

    /**
     * 停止连接
     */
    void stop();

    /**
     * 获取端口
     * @return
     */
    int getPort();
}
