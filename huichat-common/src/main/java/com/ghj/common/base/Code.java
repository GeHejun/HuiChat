package com.ghj.common.base;
/**
 * @author GeHejun
 * @date 2019-06-24
 */
public enum  Code {

    SUCCESS(Constant.SUCCESS_CODE, Constant.SUCCESS),

    LOGIN_SUCCESS(Constant.LOGIN_SUCCESS_CODE, Constant.LOGIN_SUCCESS),

    PING_SUCCESS(Constant.PING_SUCCESS_CODE, Constant.PING_SUCCESS),

    MESSAGE_SEND_SUCCESS(Constant.MESSAGE_SEND_SUCCESS_CODE, Constant.MESSAGE_SEND_SUCCESS),

    ACK_SEND_SUCCESS(Constant.ACK_SEND_SUCCESS_CODE, Constant.ACK_SEND_SUCCESS),

    LOGIN_OUT_SUCCESS(Constant.LOGIN_OUT_SUCCESS_CODE, Constant.LOGIN_OUT_SUCCESS),

    MESSAGE_RECEIVER_SUCCESS(Constant.MESSAGE_RECEIVER_SUCCESS_CODE, Constant.MESSAGE_RECEIVER_SUCCESS),

    REGISTER_SUCCESS(Constant.REGISTER_SUCCESS_CODE, Constant.REGISTER_SUCCESS),

    FAILURE(Constant.FAILURE_CODE, Constant.FAILURE),

    LOGIN_FAILURE(Constant.LOGIN_FAILURE_CODE, Constant.LOGIN_FAILURE),

    PING_FAILURE(Constant.PING_FAILURE_CODE, Constant.PING_FAILURE),

    MESSAGE_SEND_FAILURE(Constant.MESSAGE_SEND_FAILURE_CODE, Constant.MESSAGE_SEND_FAILURE),

    ACK_SEND_FAILURE(Constant.ACK_SEND_FAILURE_CODE, Constant.ACK_SEND_FAILURE),

    LOGIN_OUT_FAILURE(Constant.LOGIN_OUT_FAILURE_CODE, Constant.LOGIN_OUT_FAILURE),

    GROUP_MEMBER_REQUEST_FAILURE(Constant.GROUP_MEMBER_REQUEST_FAILURE_CODE, Constant.GROUP_MEMBER_REQUEST_FAILURE),

    REGISTER_FAILURE(Constant.REGISTER_FAILURE_CODE, Constant.REGISTER_FAILURE);

    private int code;
    private String message;

     Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }}
