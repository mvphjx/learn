package com.learn.hanjx.exceptionAndError.exception;

/**
 * 基础异常
 */
public class HanException extends RuntimeException {
    private int bundleId = 002;
    private int errorNo = 001;
    public HanException(String message) {
        super(message);
    }
    public HanException(Exception e)
    {
        super(e);
    }
    public HanException(String message, Throwable cause) {
        super(message, cause);
    }
}
