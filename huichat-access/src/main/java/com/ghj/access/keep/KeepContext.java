package com.ghj.access.keep;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class KeepContext {

    private static final ConcurrentHashMap<Long, HSession> context = new ConcurrentHashMap<>();


    public static void addHSession(HSession hSession) {
        context.put(hSession.getUId(), hSession);
    }

    public static HSession getHSession(Long uId) {
        return context.get(uId);
    }
}
