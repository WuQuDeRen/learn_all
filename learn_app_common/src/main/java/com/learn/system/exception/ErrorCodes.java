package com.learn.system.exception;


public class ErrorCodes {
    
    /**
     * 表示跨店访问异常
     */
    public final static ErrorMessage CROSS_SHOP = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_402, "CROSS_SHOP", "跨店访问异常");
    
    /**
     * 未知异常
     */
    public final static ErrorMessage UNKNOW_EXCEPTION = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_500, "UNKNOW_EXCEPTION", "未知异常");
    
    /**
     * 表示应用内部对应的实体不存在，应用抛出该异常（如用户、车辆、店铺等）
     */
    public final static ErrorMessage ITEM_NOT_EXISTS = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_404, "ITEM_NOT_EXISTS");
    
    /**
     * 登录时用户密码错误异常
     */
    public final static ErrorMessage LOGIN_BAD_PASSWORD = ErrorMessage.errorMessage(
            HttpStatusCodes.STATUS_402, "LOGIN_BAD_PASSWORD");
//    "LOGIN_BAD_PASSWORD";
    
    /**
     * 登录时用户不存在异常
     */
    public final static ErrorMessage LOGIN_USER_NOT_EXIST = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_402, "LOGIN_USER_NOT_EXIST", "登录时用户不存在");
    
    /**
     * 表示未授权异常
     */
    public final static ErrorMessage NO_PERMISSION = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_402, "NO_PERMISSION", "未授权");
    
    /**
     * 表示未登录异常
     */
    public final static ErrorMessage NOT_LOGIN = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_401, "NOT_LOGIN", "未登录");
    
    /**
     * 用户被封禁
     */
    public final static ErrorMessage USER_FORBIDDEN =  ErrorMessage.errorMessage(HttpStatusCodes.STATUS_403, "USER_FORBIDDEN", "用户禁止访问");
    
    /**
     * 超时
     */
    public final static ErrorMessage TIME_OUT = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_504, "TIMEOUT", "访问超时");
    
    /**
     * 参数类型转换异常
     */
    public final static ErrorMessage PARAM_CONVERT_ERR = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_417, "PARAM_CONVERT_ERR", "参数类型转换异常");
    
    /**
     * 参数处理异常
     */
    public final static ErrorMessage PARAM_ERROR = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_417, "PARAM_ERROR", "参数处理异常");
    
    /**
     * 参数缺失
     */
    public final static ErrorMessage PARAM_MISS = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_417, "PARAM_MISS", "参数缺失");
    
    /**
     * 参数校验异常
     */
    public final static ErrorMessage PARAM_VALIDATION_ERR = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_417, "PARAM_VALIDATION_ERR", "参数校验出错");
    
    /**
     * 路径不存在
     */
    public final static ErrorMessage PATH_NOT_EXISTS = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_404, "PATH_NOT_EXISTS", "路径不存在");
    
    /**
     * dubbo调用异常
     */
    public final static ErrorMessage DUBBO_ERROR = ErrorMessage.errorMessage(HttpStatusCodes.STATUS_503, "DUBBO_ERROR", "内部服务异常");
}

