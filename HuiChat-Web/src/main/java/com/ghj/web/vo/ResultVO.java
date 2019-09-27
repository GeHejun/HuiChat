package com.ghj.web.vo;

import com.ghj.common.base.Code;

public class ResultVO<T> {
    private T data;
    private boolean success;
    private int code;
    private String msg;

    private ResultVO() {}


    private ResultVO(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.msg = message;
    }

    private ResultVO(T data, boolean success, int code, String message) {
        this.data = data;
        this.success = success;
        this.code = code;
        this.msg = message;
    }

    /**
     * 返回失败，code码和msg自定义
     */
    public static <T> ResultVO<T> newInstance(){
        return new ResultVO<T>();
    }


    /**
     * 调用默认成功
     */
    public static <T> ResultVO<T> defaultSuccess(T data){
        return new ResultVO<T>(data, true, 0, "返回成功");
    }

    /**
     * 调用默认成功
     */
    public static  ResultVO defaultSuccess(Code code){
        return new ResultVO(code.getMessage(), true, code.getCode(), "返回成功");
    }

    /**
     * 返回默认失败
     */
    public static <T> ResultVO<T> defaultFailure(){
        return new ResultVO<T>(false, 0, "系统内部错误");
    }

    /**
     * 自定义失败一
     */
    public static <T> ResultVO<T> failure(T data, int code, String message){
        return new ResultVO<T>(data, false, code, message);
    }

    /**
     * 自定义失败二
     */
    public static <T> ResultVO<T> failure(int code, String message){
        return new ResultVO<T>(false, code, message);
    }

    public T getData() {
        return data;
    }

    public ResultVO<T> data(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResultVO<T> sucess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }


    public ResultVO<T> code(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultVO<T> msg(String message) {
        this.msg = message;
        return this;
    }
}
