package com.yu.yimserver.util;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionSocketHolder {

    private static final Map<Long, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);
    private static final Map<String,String> SESSION_MAP = new ConcurrentHashMap<>(16);

    public static void saveSession(Long userid, String userName){

    }
}
