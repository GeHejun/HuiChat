package com.ghj.chat;

import com.ghj.common.base.Constant;
import com.ghj.common.util.RedisPoolUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class SessionManager {

    private static ConcurrentHashMap SESSION_MAP = new ConcurrentHashMap(16);

    public static void putSession(Integer id, Session session) {
        SESSION_MAP.put(id, session);
        InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getChannel().localAddress();
        RedisPoolUtil.set(Constant.DISTRIBUTED_SESSION + id.toString(),  inetSocketAddress.getAddress()+"_"+ inetSocketAddress.getPort());
    }

    public static Session getSession(Integer id) {
        return (Session) SESSION_MAP.get(id);
    }

    public static Session removeSession(Integer id) {
        return (Session) SESSION_MAP.remove(id);
    }




//    public static void watchSessionStatus() {
//        ThreadPoolManager.getsInstance().execute(()->{
//            for (;;) {
//                SESSION_MAP.forEach((k,v)->{
//                    if (v != null && ((Session)v).channel != null && NettyAttrUtil.getReaderTime(((Session)v).channel) != null) {
//                        if (NettyAttrUtil.getReaderTime(((Session)v).channel) < System.currentTimeMillis()) {
//                            removeSession((Integer) k);
//                            Thread.yield();
//                        }
//                    }
//
//                });
//            }
//        });
//    }

}
