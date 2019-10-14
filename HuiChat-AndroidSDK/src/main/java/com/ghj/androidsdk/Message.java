package com.ghj.androidsdk;

import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 16:07
 */
@Data
public class Message {

    com.ghj.protocol.Message.Chat chat;

    Long invalidTime;

}
