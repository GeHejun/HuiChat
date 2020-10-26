package com.ghj.access.keep;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class KeepClientContext {

    private KeepClientContext() {}

    private static ConcurrentHashMap<String, KeepClient> keepClientMap = new ConcurrentHashMap<>();

    public static KeepClient getBestKeepClint() {
        return keepClientMap.values().parallelStream().findAny().orElseThrow(RuntimeException::new);
    }

    public static void adjustKeepClient(String address) {
        String[] addresses = address.split(";");
        List<String> existKey = Lists.newArrayList(keepClientMap.keySet());
        //遍历列表获取router配置
        for (String s : addresses) {
            String routerHost = s.split(":")[0];
            String routerPort = s.split(":")[1];
            if (!keepClientMap.containsKey(s)) {
                keepClientMap.put(s, new KeepClient(routerHost, Integer.parseInt(routerPort)));
            }
            existKey.remove(s);
        }
        existKey.forEach(k -> keepClientMap.remove(k));
    }
}
