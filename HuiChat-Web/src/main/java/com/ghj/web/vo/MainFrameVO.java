package com.ghj.web.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/24 13:26
 */
@Data
public class MainFrameVO {

    private UserVO mine;

    private List<FriendVO> frined;

    private List<GroupVO> group;
}
