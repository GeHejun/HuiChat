package com.ghj.common.base;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/15 14:51
 */
public enum ContentType {

    Text(1), Picture(2);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    ContentType(Integer code) {
        this.code = code;
    }
}
