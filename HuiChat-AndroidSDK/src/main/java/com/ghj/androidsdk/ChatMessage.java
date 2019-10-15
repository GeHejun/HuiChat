package com.ghj.androidsdk;

import lombok.Builder;
import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 16:07
 */
@Data
@Builder
public class ChatMessage {

    com.ghj.protocol.Message.Chat chat;

    Long invalidTime;

}
