package com.ghj.web.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/22 11:10
 */
@Data
@Builder
public class FriendGroupVO {

    private String groupname;

    private Integer id;

    private List<UserVO> list;
}
