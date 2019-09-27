package com.ghj.web.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description 主视图数据
 * @date 2019/8/22 10:11
 */
@Data
@Builder
public class MainFrameVO {

    private UserVO mine;

    List<FriendGroupVO> friend;

    List<GroupVO> group;

}
