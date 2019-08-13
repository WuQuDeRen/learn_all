package com.learn.system.exception;


/**
 * 异常消息信息
 */
public class ErrorMessage {
    private int httpCode;
    private String errorCode;
    private String errorMessage;
    
    private ErrorMessage(int httpCode, String errorCode, String errorMessage) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ErrorMessage errorMessage(int httpCode, String errorCode, String errorMessage) {
        return new ErrorMessage(httpCode, errorCode, errorMessage);
    }
    
    public static ErrorMessage errorMessage(String errorCode, String errorMessage) {
        return errorMessage(HttpStatusCodes.STATUS_500, errorCode, errorMessage);
    }
    
    public static ErrorMessage errorMessage(int httpCode, String errorCode) {
        return errorMessage(HttpStatusCodes.STATUS_500, errorCode, null);
    }
    
    public int getHttpCode() {
        return httpCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
