package com.ghj.access.keep;

public enum HSStatusEnum {
    ON_LINE(1, "在线"),
    AUTO_OFF_LINE(2, "自动下线"),
    FORCE_OFF_LINE(3, "被迫下线");

    private Integer code;

    private String name;

    HSStatusEnum(Integer code, String name) {
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
