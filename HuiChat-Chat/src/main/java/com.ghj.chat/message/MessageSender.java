package com.ghj.chat.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ghj.chat.Session;
import com.ghj.chat.SessionManager;
import com.ghj.chat.constant.Route;
import com.ghj.common.base.Code;
import com.ghj.common.base.Constant;
import com.ghj.common.dto.PersistentMessage;
import com.ghj.common.exception.ChatException;
import com.ghj.common.mq.SendUtil;
import com.ghj.common.util.MachineSerialNumber;
import com.ghj.common.util.OKHttpUtil;
import com.ghj.common.util.SnowFlakeIdGenerator;
import com.ghj.protocol.Message;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ghj.common.base.Constant.*;


/**
 * 消息处理类
 *
 * @author GeHejun
 * @date 2019/6/24 13:30
 */
public class MessageSender implements Runnable {

    /**
     * 消息
     */
    private Message.Data data;

    /**
     * 初始化消息
     *
     * @param data
     */
    public MessageSender(Message.Data data) {
        this.data = data;
    }

    /**
     * 处理消息
     */
    @Override
    public void run() {
          switch (data.getDataType()) {
              case Ack:
                  break;
              case Chat:
                  break;
          }
//        if (ACK == message.getMessageBehavior()) {
//            dealAckMessage(message);
//        } else {
//            switch (message.getMessageDirect()) {
//                case PERSONAL:
//                    if (VALIDATION_MESSAGE == message.getMessageBehavior()
//                            || REPLY_VALIDATION_MESSAGE == message.getMessageBehavior()) {
//                        dealValidationMessage(message);
//                    } else {
//                        dealPersonalMessage(message);
//                    }
//                    break;
//                case GROUP:
//                    if (VALIDATION_MESSAGE == message.getMessageBehavior()) {
//                        dealValidationMessage(message);
//                    } else {
//                        dealGroupMessage(message);
//                    }
//                    break;
//                case SERVER:
//                    dealServerMessage(message);
//                default:
//            }
//        }
    }


//    /**
//     * 处理反馈到服务端消息（tome）
//     *
//     * @param message
//     */
//    private void dealServerMessage(MessageProto.Message message) {
//        //在这里如果接收失败更改消息状态
//        MessageManager.getInstance().putMessage(buildAckMessage(Code.ACK_SEND_SUCCESS, true, message));
//        MessageManager.getInstance().putMessage(buildAckMessage(Code.MESSAGE_RECEIVER_SUCCESS, false, message));
//    }
//
//    /**
//     * 处理点对点聊天消息
//     *
//     * @param message
//     */
//    private void dealPersonalMessage(MessageProto.Message message) {
//        PersistentMessage persistentMessage;
//        Integer sessionKey = message.getToUserId();
//        Session session = SessionManager.getSession(sessionKey);
//        if (session == null) {
//            persistentMessage = buildPersistentMessage(message, false, false, null, null, Constant.MESSAGE );
//        } else {
//            session.getChannel().writeAndFlush(message);
//            persistentMessage = buildPersistentMessage(message, true, false, null, null,  Constant.MESSAGE);
//        }
//        SendUtil.sendForQueue(persistentMessage);
//    }
//
//    /**
//     * 处理点对点聊天消息
//     *
//     * @param message
//     */
//    private void dealValidationMessage(MessageProto.Message message) {
//        String type = null;
//        if (VALIDATION_MESSAGE == message.getMessageBehavior()) {
//            type = MessageProto.Message.MessageDirect.PERSONAL == message.getMessageDirect() ? Constant.FRIEND_VALIDATION_MESSAGE : Constant.GROUP_VALIDATION_MESSAGE;
//        }
//        if (REPLY_VALIDATION_MESSAGE == message.getMessageBehavior()) {
//            type = MessageProto.Message.MessageDirect.PERSONAL == message.getMessageDirect() ? REPLAY_FRIEND_VALIDATION_MESSAGE : REPLAY_GROUP_VALIDATION_MESSAGE;
//
//        }
//        PersistentMessage persistentMessage = buildPersistentMessage(message, false, false, null, null,   type);
//        Integer sessionKey = message.getToUserId();
//        Session session = SessionManager.getSession(sessionKey);
//        if (session != null) {
//            session.getChannel().writeAndFlush(message);
//        }
//        SendUtil.sendForQueue(persistentMessage);
//    }
//
//    /**
//     * 处理群发消息
//     *
//     * @param message
//     */
//    private void dealGroupMessage(MessageProto.Message message) {
//        Integer toGroupId = message.getToGroupId();
//        OKHttpUtil.get(Route.GET_GROUP_MEMBER + toGroupId, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                MessageManager.getInstance().putMessage(buildAckMessage(Code.GROUP_MEMBER_REQUEST_FAILURE, true, message));
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                JSONObject jsonObject = (JSONObject) JSON.parse(result);
//                JSONArray toIds = jsonObject.getJSONArray(DATA_KEY);
//                List<Integer> offLineUserIds = new ArrayList<>(toIds.size());
//                List<Integer> onLineUserIds = new ArrayList<>(toIds.size());
//                for (Integer id : toIds.toJavaList(Integer.class)) {
//                    if (!Objects.equals(id, message.getFromUserId())) {
//                        Session session = SessionManager.getSession(Integer.parseInt(id.toString()));
//                        if (session == null) {
//                            offLineUserIds.add(id);
//                            continue;
//                        }
//                        session.getChannel().writeAndFlush(message);
//                        onLineUserIds.add(id);
//                    }
//                }
//                PersistentMessage persistentMessage = buildPersistentMessage(message, true, true, onLineUserIds, offLineUserIds, Constant.MESSAGE);
//                SendUtil.sendForQueue(persistentMessage);
//            }
//        });
//    }
//
//    /**
//     * 处理反馈消息（tome）
//     *
//     * @param message
//     */
//    private void dealAckMessage(MessageProto.Message message) {
//        Integer sessionKey = message.getToUserId();
//        Session session = SessionManager.getSession(sessionKey);
//        if (session == null) {
//            throw new ChatException();
//        }
//        session.getChannel().writeAndFlush(message);
//    }
//
//    /**
//     * 构建反馈消息
//     *
//     * @param code
//     * @param isAckSender
//     * @param message
//     * @return
//     */
//    private MessageProto.Message buildAckMessage(Code code, boolean isAckSender, MessageProto.Message message) {
//        return MessageProto.Message.newBuilder()
//                .setCode(code.getCode())
//                .setContent(code.getMessage())
//                .setId(new SnowFlakeIdGenerator(message.getDeviceId(), MachineSerialNumber.get()).nextId())
//                .setToUserId(isAckSender ? message.getFromUserId() : message.getToUserId())
//                .setAssociatedGroupId(message.getAssociatedGroupId())
//                .setMatchMessageId(message.getId())
//                .setMessageBehavior(ACK)
//                .build();
//    }
//
//    /**
//     * 构建持久化消息
//     *
//     * @param message
//     * @param status
//     * @param isGroup
//     * @param onLineUserIds
//     * @param offLineUserIds
//     * @return
//     */
//    private PersistentMessage buildPersistentMessage(MessageProto.Message message, Boolean status, Boolean isGroup, List<Integer> onLineUserIds, List<Integer> offLineUserIds, String type) {
//        if (isGroup) {
//            return PersistentMessage.builder()
//                    .id(message.getId())
//                    .fromUserId(message.getFromUserId())
//                    .messageTypeId(message.getMessageTypeId())
//                    .postMessage(message.getContent())
//                    .toGroupId(message.getToGroupId())
//                    .sendTime(Instant.now().toString())
//                    .onLineUserIds(onLineUserIds)
//                    .offLineUserIds(offLineUserIds)
//                    .status(status)
//                    .type(type)
//                    .build();
//        } else {
//            return PersistentMessage.builder()
//                    .id(message.getId())
//                    .fromUserId(REPLAY_FRIEND_VALIDATION_MESSAGE.equals(type) ? null :message.getFromUserId())
//                    .messageTypeId(message.getMessageTypeId())
//                    .postMessage(message.getContent())
//                    .status(status)
//                    .toUserId(message.getToUserId())
//                    .sendTime(Instant.now().toString())
//                    .type(type)
//                    .toGroupId(GROUP_VALIDATION_MESSAGE.equals(type)? message.getToGroupId() : null)
//                    .fromFriendGroupId(REPLAY_FRIEND_VALIDATION_MESSAGE.equals(type) ? null : message.getFromFriendGroupId())
//                    .build();
//        }
//    }
}
