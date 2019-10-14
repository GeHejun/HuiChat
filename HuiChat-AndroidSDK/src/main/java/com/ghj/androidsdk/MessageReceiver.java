package com.ghj.androidsdk;

import com.ghj.protocol.Message;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 15:30
 */
public interface MessageReceiver {

    void dealMessage(Message.Data data);
}
