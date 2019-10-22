package com.ghj.chat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ghj.chat.Session;
import com.ghj.chat.SessionManager;
import com.ghj.chat.constant.Route;
import com.ghj.common.exception.ChatException;
import com.ghj.common.util.OkHttpUtil;
import com.ghj.common.util.PropertiesUtil;
import com.ghj.protocol.Message;
import io.netty.channel.Channel;

import java.io.IOException;
import java.util.Objects;

import static com.ghj.common.base.Constant.DATA_KEY;
import static com.ghj.common.base.Constant.REST_CONNECT;


/**
 * 消息处理类
 *
 * @author GeHejun
 * @date 2019/6/24 13:30
 */
public class MessageSender {

  public static void sendMsg(Message.Data data) {
      switch (data.getDataType()) {
          case Ack:
              dealAckMessage(data.getAck());
              break;
          case Chat:
              switch (data.getChat().getChatType()) {
                  case Single:
                      dealSingleMessage(data.getChat());
                      break;
                  case Group:
                      dealGroupMessage(data.getChat());
                      break;
                  default:
              }
              break;
          default:
      }
  }

    /**
     * 处理点对点聊天消息
     *
     * @param chat
     */
    private static void dealSingleMessage(Message.Chat chat) {
        Integer sessionKey = chat.getTo();
        Session session = SessionManager.getSession(sessionKey);
        if (session == null) {
        } else {
            session.getChannel().writeAndFlush(chat);
        }
    }

    /**
     * 处理群发消息
     *
     * @param chat
     */
    private static void dealGroupMessage(Message.Chat chat) {
        Integer to = chat.getTo();
        try {
            JSONObject result = OkHttpUtil.get(PropertiesUtil.getInstance().getValue(REST_CONNECT) + Route.GET_GROUP_MEMBER + to);
            JSONArray toIds = result.getJSONArray(DATA_KEY);
            for (Integer id : toIds.toJavaList(Integer.class)) {
                if (!Objects.equals(id, chat.getForm())) {
                    Session session = SessionManager.getSession(Integer.parseInt(id.toString()));
                    if (session == null) {
                        continue;
                    }
                    session.getChannel().writeAndFlush(chat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理反馈消息
     *
     * @param ack
     */
    private static void dealAckMessage(Message.Ack ack) {
        Integer sessionKey = ack.getTo();
        Session session = SessionManager.getSession(sessionKey);
        if (session == null) {
            throw new ChatException();
        }
        session.getChannel().writeAndFlush(ack);
    }
}
