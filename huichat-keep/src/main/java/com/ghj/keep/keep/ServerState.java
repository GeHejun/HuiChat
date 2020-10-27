package com.ghj.keep.keep;

/**
 *
 */
public enum ServerState {

    NOT_ENABLE(0,""),
    ENABLE(1,"");


    private Integer code;

    private String name;

    ServerState(Integer code, String name) {
        this.code = code;

        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
