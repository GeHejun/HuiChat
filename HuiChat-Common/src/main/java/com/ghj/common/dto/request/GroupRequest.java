package com.ghj.common.dto.request;

import lombok.Data;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/10 13:56
 */
@Data
public class GroupRequest {
    private String name;
    private Integer adminId;
    private List<Integer> friendIds;
}
