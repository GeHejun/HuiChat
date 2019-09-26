package com.ghj.common.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class NettyAttrUtil {

    public static final AttributeKey<String> ATTR_KEY_READER_TIME = AttributeKey.valueOf("readerTime");


    public static void updateReaderTime(Channel channel, Long time) {
        channel.attr(ATTR_KEY_READER_TIME).getAndSet(time.toString());
    }

    public static Long getReaderTime(Channel channel) {
        String value = getAttribute(channel, ATTR_KEY_READER_TIME);

        if (value != null) {
            return Long.valueOf(value);
        }
        return null;
    }


    private static String getAttribute(Channel channel, AttributeKey<String> key) {
        Attribute<String> attr = channel.attr(key);
        return attr.get();
    }
}
