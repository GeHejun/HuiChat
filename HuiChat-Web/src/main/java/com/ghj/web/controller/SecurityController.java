package com.ghj.web.controller;

import com.ghj.common.base.Constant;
import com.ghj.common.dto.request.FriendRequest;
import com.ghj.common.dto.request.GroupRequest;
import com.ghj.common.dto.request.UserRequest;
import com.ghj.common.dto.response.UserResponse;
import com.ghj.common.exception.UserException;
import com.ghj.web.service.SecurityService;
import com.ghj.web.vo.GroupVO;
import com.ghj.web.vo.ResultVO;
import com.ghj.web.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author GeHejun
 */
@RestController
@RequestMapping("/security")
public class SecurityController {

    @Resource
    SecurityService securityService;

    @RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    public ResultVO<UserVO> validateUser(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        try {
            UserVO userVO = securityService.validateUser(loginName, password);
            return ResultVO.defaultSuccess(userVO);
        } catch (UserException e) {
            return ResultVO.failure(Constant.LOGIN_FAILURE_CODE, Constant.LOGIN_FAILURE);
        }
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public ResultVO<Boolean> checkUser(@RequestParam("loginName") String loginName) {
        try {
            Boolean isOk = securityService.checkUser(loginName);
            return ResultVO.defaultSuccess(isOk);
        } catch (UserException e) {
            return ResultVO.failure(Constant.REGISTER_FAILURE_CODE, Constant.REGISTER_FAILURE);
        }
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResultVO<UserResponse> register(@RequestBody UserRequest userRequest) {
//        try {
//            UserResponse userResponse = securityService.register(userRequest);
//            return ResultVO.defaultSuccess(userResponse);
//        } catch (UserException e) {
//            return ResultVO.failure(Constant.REGISTER_FAILURE_CODE, Constant.REGISTER_FAILURE);
//        }
//    }

//    @RequestMapping("/findUser")
//    public ResultVO<List<UserVO>> findUser(@NotNull @RequestParam("name") String nickName) {
//        try {
//            List<UserVO> userVOList = securityService.findUser(nickName);
//            return ResultVO.defaultSuccess(userVOList);
//        } catch (UserException e) {
//            return ResultVO.failure(USER_NO_EXISTS_CODE,USER_NO_EXISTS);
//        }
//    }

//    @RequestMapping("/findGroup")
//    public ResultVO<List<GroupVO>> findGroup(@NotNull @RequestParam("name") String name) {
//        try {
//            List<GroupVO> groupVOList = securityService.findGroup(name);
//            return ResultVO.defaultSuccess(groupVOList);
//        } catch (UserException e) {
//            return ResultVO.failure(GROUP_NO_EXISTS_CODE,GROUP_NO_EXISTS);
//        }
//    }

    @RequestMapping("/agreeFriend")
    public ResultVO<Boolean> agreeFriend(@RequestBody FriendRequest friendRequest) {
        return ResultVO.defaultSuccess(securityService.agreeFriend(friendRequest));
    }

    @RequestMapping("/agreeGroup")
    public ResultVO<Integer> agreeGroup(
            @NotNull @RequestParam("validationMessageId") Long validationMessageId,
            @NotNull @RequestParam("fromUserId") Integer fromUserId,
            @RequestParam("toGroupId") Integer toGroupId) {
        return ResultVO.defaultSuccess(securityService.agreeGroup(validationMessageId, fromUserId, toGroupId));
    }

    @RequestMapping("/refuseFriend")
    public ResultVO<Boolean> refuseFriend(@NotNull @RequestParam("validationMessageId") Long validationMessageId) {
        return ResultVO.defaultSuccess(securityService.refuseFriend(validationMessageId));
    }

    @RequestMapping("/createGroup")
    public ResultVO<Boolean> createGroup(@RequestBody GroupRequest groupRequest) {
        return ResultVO.defaultSuccess(securityService.createGroup(groupRequest));
    }

    @RequestMapping("/createNewFriendGroup")
    public ResultVO<Integer> createNewFriendGroup(@NotNull @RequestParam("userId") Integer userId,@RequestParam("newFriendGroupName") String newFriendGroupName) {
        return ResultVO.defaultSuccess(securityService.createNewFriendGroup(userId, newFriendGroupName));
    }
}
