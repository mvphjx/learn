package com.learn.hanjx.exceptionAndError.exception;

/**
 * 重复秒杀异常，不需要我们手动去try catch
 */
public class DataException extends HanException{
    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
