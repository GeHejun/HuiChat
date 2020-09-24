package com.ghj.common.base;
/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class Result<T> {
    private T data;
    private boolean success;
    private int code;
    private String message;

    private Result() {}


    private Result(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private Result(T data, boolean success, int code, String message) {
        this.data = data;
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 返回失败，code码和msg自定义
     */
    public static <T> Result<T> newInstance(){
        return new Result<T>();
    }


    /**
     * 调用默认成功
     */
    public static <T> Result<T> defaultSuccess(T data){
        return new Result<T>(data, true, 200, "返回成功");
    }

    /**
     * 调用默认成功
     */
    public static Result defaultSuccess(Code code){
        return new Result(code.getMessage(), true, code.getCode(), "返回成功");
    }

    /**
     * 调用默认成功
     */
    public static  <T> Result<T> defaultSuccess(Code code, T data){
        return new Result(data, true, code.getCode(), "返回成功");
    }

    /**
     * 返回默认失败
     */
    public static <T> Result<T> defaultFailure(){
        return new Result<T>(false, 500, "系统内部错误");
    }

    /**
     * 自定义失败一
     */
    public static <T> Result<T> failure(T data, int code, String message){
        return new Result<T>(data, false, code, message);
    }

    /**
     * 自定义失败二
     */
    public static <T> Result<T> failure(int code, String message){
        return new Result<T>(false, code, message);
    }

    public T getData() {
        return data;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<T> sucess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }


    public Result<T> code(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> message(String message) {
        this.message = message;
        return this;
    }
}
