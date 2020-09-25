package com.ghj.common.exception;
/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class BusinessException extends RuntimeException {
    int code;
    String message;
}
