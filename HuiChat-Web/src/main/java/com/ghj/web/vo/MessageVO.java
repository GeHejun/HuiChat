package com.ghj.web.vo;

import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/15 14:41
 */
@Data
public class MessageVO {
    private Integer from;
    private Integer to;
    private Boolean isGroup;
    private String content;
}
