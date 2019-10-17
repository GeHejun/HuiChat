package com.ghj.web.service;

import com.ghj.common.base.Result;
import com.ghj.common.dto.request.FriendRequest;
import com.ghj.common.dto.request.GroupRequest;
import com.ghj.common.dto.request.UserRequest;
import com.ghj.common.dto.response.*;
import com.ghj.web.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/22 11:03
 */
@FeignClient(name = "rest")
public interface RestService {
    /**
     * 通过用户查询群
     * @param userId
     * @return
     */
    @RequestMapping("/groupToUser/findGroupByUserId")
    @ResponseBody
    Result<List<GroupToUserResponse>> findGroupsByUserId(@NotNull @RequestParam("userId") Integer userId);

    /**
     * 通过群组查询成员
     * @param groupId
     * @return
     */
    @RequestMapping("/groupToUser/findUserIdByGroupId")
    @ResponseBody
    Result<List<Integer>> findUserIdByGroupId(@RequestParam("groupId") @NotNull Integer groupId);

    /**
     * 通过群组查询群组
     * @param id
     * @return
     */
    @RequestMapping("/userGroup/findGroupById")
    @ResponseBody
    Result<UserGroupResponse> findGroupById(@RequestParam("id") @NotNull Integer id);
    /**
     * 查询好友所在群组信息
     * @param id
     * @return
     */
    @RequestMapping("/friendGroup/queryGroupById")
    @ResponseBody
    Result<FriendGroupResponse> queryGroupById(@NotNull @RequestParam("id") Integer id);

    /**
     * 查询好友列表
     * @param userId
     * @return
     */
    @RequestMapping("/friend/queryFriendList")
    @ResponseBody
    Result<List<FriendResponse>> queryFriendList(@Valid @RequestParam("userId") Integer userId);

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/queryUser")
    @ResponseBody
    Result<UserResponse> queryUser(@NotNull @RequestParam("id") Integer id);


    /**
     * 查询状态信息
     * @param id
     * @return
     */
    @RequestMapping("/state/queryUserStateById")
    @ResponseBody
    Result<UserStateResponse> queryUserStateById(@NotNull @RequestParam(name = "id", defaultValue = "1") Integer id);

    /**
     * 登录验证
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    Result<UserResponse> login(@NotNull @RequestParam("loginName") String loginName, @NotNull @RequestParam("password") String password);

    /**
     * 通过接收人和状态查询群消息列表
     * @param toUserId
     * @param status
     * @return
     */
    @RequestMapping("/groupMessageToUser/listGroupMessageByToUserIdAndStatus")
    @ResponseBody
    Result<List<GroupMessageToUserResponse>> listGroupMessageByToUserIdAndStatus(@RequestParam("toUserId") @NotNull Integer toUserId, @RequestParam(value = "status", defaultValue = "false") Boolean status);

    /**
     * 通过接收人和状态查询好友消息列表
     * @param toUserId
     * @param status
     * @return
     */
    @RequestMapping("/message/queryMessageWithToUserIdAndStatus")
    @ResponseBody
    Result<List<MessageResponse>> queryMessageWithToUserIdAndStatus(@NotNull @RequestParam("toUserId") Integer toUserId, @RequestParam(defaultValue = "false") @NotNull Boolean status);

    /**
     * 检查用户名是否可用
     * @param loginName
     * @return
     */
    @RequestMapping(value = "/user/checkUser")
    @ResponseBody
    Result<Boolean> checkUser(@NotNull @RequestParam("loginName") String loginName);

    /**
     * 注册新用户
     * @param userRequest
     * @return
     */
    @RequestMapping(value = "/user/register")
    @ResponseBody
    Result<UserResponse> register(@RequestBody UserRequest userRequest);

    /**
     * 分页查询好友历史消息
     * @param fromUserId
     * @param toUserId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/message/queryHistoryMessageListForPage")
    @ResponseBody
    Result<HistoryMessage<MessageResponse>> queryHistoryMessageListForPage(
            @NotNull @RequestParam("fromUserId") Integer fromUserId,
            @NotNull @RequestParam("toUserId") Integer toUserId,
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize);

    /**
     * 分页查询群历史消息
     * @param toGroupId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/groupMessage/queryHistoryGroupMessageListForPage")
    @ResponseBody
    Result<HistoryMessage<GroupMessageResponse>> queryHistoryGroupMessageListForPage(@NotNull @RequestParam("toUserId") Integer toGroupId,
                                                                                     @RequestParam(defaultValue = "1") Integer pageIndex,
                                                                                     @RequestParam(defaultValue = "10") Integer pageSize);


    /**
     * 通过登录名查询用户
     * @param loginName
     * @return
     */
    @RequestMapping("/user/queryUserByNickName")
    @ResponseBody
    Result<List<UserResponse>> queryUserByNickName(@NotNull @RequestParam("nickName") String loginName);

    /**
     * 通过名称查询群组
     * @param name
     * @return
     */
    @RequestMapping("/userGroup/findGroupByName")
    @ResponseBody
    Result<List<UserGroupResponse>> findGroupByName(@RequestParam("name") @NotNull String name);

    /**
     * 查询状态通过名称
     * @param name
     * @return
     */
    @RequestMapping("/state/queryStateByName")
    @ResponseBody
    Result<UserStateResponse> queryStateByName(@RequestParam("name") String name);

    /**
     * 查询用户状态
     * @param userId
     * @return
     */
    @RequestMapping("/user/queryUserState")
    @ResponseBody
    Result<Boolean> queryUserState(@NotNull @RequestParam("userId") Integer userId);

    /**
     * 消息标记为已读 好友消息
     * @param fromUserId
     * @param toUserId
     * @return
     */
    @RequestMapping("/message/readFriendMessage")
    @ResponseBody
    Result<Boolean> readFriendMessage(@NotNull @RequestParam("fromUserId") Integer fromUserId, @NotNull @RequestParam("toUserId") Integer toUserId);

    /**
     * 群消息标记为已读
     * @param groupId
     * @param toUserId
     * @return
     */
    @RequestMapping("/groupMessageToUser/readGroupMessage")
    @ResponseBody
    Result<Boolean> readGroupMessage(@NotNull @RequestParam("groupId") Integer groupId, @NotNull @RequestParam("toUserId") Integer toUserId);

    /**
     * 查询好友未读消息条数
     * @param toUserId
     * @return
     */
    @RequestMapping("/message/queryUnreadFriendMessage")
    @ResponseBody
    Result<List<UnreadMessageResponse>> queryUnreadFriendMessage(@NotNull @RequestParam("toUserId") Integer toUserId);

    /**
     * 查询群组未读消息条数
     * @param toUserId
     * @return
     */
    @RequestMapping("/groupMessageToUser/queryUnreadGroupMessage")
    @ResponseBody
    Result<List<UnreadMessageResponse>> queryUnreadGroupMessage(@NotNull @RequestParam("toUserId") Integer toUserId);

    /**
     *
     * @param toUserId
     * @return
     */
    @RequestMapping("/systemMessage/queryUnreadValidationMessageNum")
    @ResponseBody
    ResultVO<Integer> queryUnreadValidationMessageNum(@NotNull @RequestParam("toUserId") Integer toUserId);


    /**
     *
     * @param toUserId
     * @return
     */
    @RequestMapping("/systemMessage/queryValidationMessage")
    @ResponseBody
    Result<List<SystemMessageResponse>> queryValidationMessage(@NotNull @RequestParam("toUserId") Integer toUserId);

    /**
     *
     * @param toUserId
     * @return
     */
    @RequestMapping("/systemMessage/readValidationMessage")
    @ResponseBody
    Result<Boolean> readValidationMessage(@NotNull @RequestParam("toUserId") Integer toUserId);


    @RequestMapping("/messageType/queryMessageTypeIdByName")
    @ResponseBody
    Result<Integer> queryMessageTypeIdByName(@RequestParam("name") String name);

    @RequestMapping("/queryMessageTypeNameById")
    @ResponseBody
    Result<String> queryMessageTypeNameById(@RequestParam("id") Integer id);


    @RequestMapping("/friend/agreeFriend")
    @ResponseBody
    Result<Boolean> agreeFriend(@RequestBody FriendRequest friendRequest);

    @RequestMapping("/friend/refuseFriend")
    @ResponseBody
    Result<Boolean> refuseFriend(@NotNull @RequestParam("validationMessageId") Long validationMessageId);

    @RequestMapping("/groupToUser/agreeGroup")
    @ResponseBody
    Result<Integer> agreeGroup(@RequestParam("validationMessageId") Long validationMessageId, @RequestParam("fromUserId") Integer fromUserId, @RequestParam("toGroupId") Integer toGroupId);

    @RequestMapping("/userGroup/createGroup")
    @ResponseBody
    Result<Boolean> createGroup(@RequestBody GroupRequest groupRequest);

    @RequestMapping("/friendGroup/createNewFriendGroup")
    @ResponseBody
    Result<Integer> createNewFriendGroup(@RequestParam("userId") Integer userId, @RequestParam("newFriendGroupName") String newFriendGroupName);
}
