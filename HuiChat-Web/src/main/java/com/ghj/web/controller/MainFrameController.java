package com.ghj.web.controller;

import com.ghj.web.service.MainFrameService;
import com.ghj.web.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description 主视图数据
 * @date 2019/8/22 10:09
 */
@Controller
@RequestMapping("/index")
public class MainFrameController {


    @Autowired
    MainFrameService mainFrameService;

    /**
     * 暂时写死用id 以后改成token
     */
    @RequestMapping("/initMainFrame")
    @ResponseBody
    public ResultVO<MainFrameVO> initMainFrame(@NotNull @RequestParam("id") Integer id) {
        MainFrameVO mainFrameVO = mainFrameService.initMainFrame(id);
        return ResultVO.defaultSuccess(mainFrameVO);
    }

    /**
     * 通过群ID查询群成员
     * @param groupId
     * @return
     */
    @RequestMapping("/initMembers")
    @ResponseBody
    public ResultVO<MemberVO> initMembers(@NotNull @RequestParam("id") Integer groupId) {
        MemberVO memberVO = mainFrameService.initMembers(groupId);
        return ResultVO.defaultSuccess(memberVO);
    }

    /**
     * 查询离线信息
     * @param toUserId
     * @param status
     * @return
     */
    @RequestMapping("/initOffLineMessages")
    @ResponseBody
    public ResultVO<List<MessageVO>> initOffLineMessages(@NotNull @RequestParam("toUserId") Integer toUserId, @RequestParam(name = "status", defaultValue = "false") Boolean status) {
        List<MessageVO> messageVOList = mainFrameService.initOffLineMessages(toUserId, status);
        return ResultVO.defaultSuccess(messageVOList);
    }

    /**
     * 历史消息
     * @param fromUserId
     * @param toUserId
     * @param type
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/initHistoryMessage")
    @ResponseBody
    public ResultVO<HistoryMessageVO> initHistoryMessage(@NotNull @RequestParam("fromUserId") Integer fromUserId,
                                                         @NotNull @RequestParam("toUserId") Integer toUserId,
                                                         @NotNull @RequestParam("type") String type,
                                                         @NotNull @RequestParam(defaultValue = "1") Integer pageIndex,
                                                         @NotNull @RequestParam(defaultValue = "10") Integer pageSize) {
        HistoryMessageVO historyMessageVO = mainFrameService.initHistoryMessage(fromUserId, toUserId, type, pageIndex, pageSize);
        return ResultVO.defaultSuccess(historyMessageVO);
    }

    /**
     * 初始化好友状态
     * @param userId
     * @return
     */
    @RequestMapping("/initFriendState")
    @ResponseBody
    public ResultVO<Boolean> initFriendState(@NotNull @RequestParam("userId") Integer userId) {
        Boolean friendState = mainFrameService.initFriendState(userId);
        return ResultVO.defaultSuccess(friendState);
    }

    /**
     * 消息标记为已读状态
     * @param fromUserId
     * @param toUserId
     * @param type
     * @return
     */
    @RequestMapping("/readMessage")
    public void readMessage(@NotNull @RequestParam("fromUserId") Integer fromUserId,
                                                 @NotNull @RequestParam("toUserId") Integer toUserId,
                                                 @NotNull @RequestParam("type") String type) {
        mainFrameService.readMessage(fromUserId, toUserId, type);
    }

    /**
     * 初始化消息盒子
     * @return
     */
    @RequestMapping("/initMessageBoxVO")
    @ResponseBody
    public ResultVO<List<MessageBoxVO>> initMessageBoxVO(@NotNull @RequestParam("toUserId") Integer toUserId) {
        List<MessageBoxVO> messageBoxVOList =  mainFrameService.initMessageBoxVO(toUserId);
        return ResultVO.defaultSuccess(messageBoxVOList);
    }


    /**
     * 初始化消息盒子
     * @return
     */
    @RequestMapping("/initMessageBoxNum")
    @ResponseBody
    public ResultVO<Integer> initMessageBoxNum(@NotNull @RequestParam("toUserId") Integer toUserId) {
        Integer messageBoxNum = mainFrameService.initMessageBoxNum(toUserId);
        return ResultVO.defaultSuccess(messageBoxNum);
    }

    /**
     * 初始化消息盒子
     * @return
     */
    @RequestMapping("/readValidationMessage")
    @ResponseBody
    public ResultVO<Boolean> readValidationMessage(@NotNull @RequestParam("toUserId") Integer toUserId) {
        Boolean isSuccess = mainFrameService.readValidationMessage(toUserId);
        return ResultVO.defaultSuccess(isSuccess);
    }

    @RequestMapping("/initUnreadMessageNum")
    @ResponseBody
    public ResultVO<List<UnreadMessageNumVO>> initUnreadMessageNum(@NotNull @RequestParam("toUserId") Integer toUserId) {
        return ResultVO.defaultSuccess(mainFrameService.initUnreadMessageNum(toUserId));
    }

    /**
     * 暂时写死用id 以后改成token
     */
    @RequestMapping("/initFriend")
    @ResponseBody
    public ResultVO<List<UserVO>> initFriend(@NotNull @RequestParam("id") Integer id) {
        List<UserVO> userVOList = mainFrameService.initFriend(id);
        return ResultVO.defaultSuccess(userVOList);
    }



}
