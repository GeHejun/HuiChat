package com.ghj.web.service;

import com.ghj.web.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/22 10:38
 */
@Service
public interface MainFrameService {

    /**
     * 初始化主面板数据
     * @param id
     * @return
     */
    MainFrameVO initMainFrame(Integer id);

    /**
     * 初始化群成员信息
     * @param groupId
     * @return
     */
    MemberVO initMembers(Integer groupId);

    /**
     * 获取离线消息
     * @param toUserId
     * @param status
     * @return
     */
    List<MessageVO> initOffLineMessages(Integer toUserId, Boolean status);

    /**
     * 查询历史消息
     * @param fromUserId
     * @param toUserId
     * @param type
     * @param pageIndex
     * @param pageSize
     * @return
     */
    HistoryMessageVO initHistoryMessage(Integer fromUserId, Integer toUserId, String type, Integer pageIndex, Integer pageSize);

    /**
     * 查询好友状态
     * @param userId
     * @return
     */
    Boolean initFriendState(Integer userId);

    /**
     * 消息标记为已读
     * @param fromUserId
     * @param toUserId
     * @param type
     * @return
     */
    void readMessage(Integer fromUserId, Integer toUserId, String type);

    /**
     * 初始化消息盒子
     * @param toUserId
     * @return
     */
    List<MessageBoxVO> initMessageBoxVO(Integer toUserId);

    /**
     * 初始化消息盒子未读消息
     * @param toUserId
     * @return
     */
    Integer initMessageBoxNum(Integer toUserId);

    Boolean readValidationMessage(Integer toUserId);

    List<UnreadMessageNumVO> initUnreadMessageNum(Integer toUserId);

    List<UserVO> initFriend(Integer id);
}
