package com.learn.system.exception;

public class HttpStatusCodes {
    /**
     * 未授权
     */
    public final static int STATUS_401 = 401;
    
    public final static int STATUS_402 = 402;

    /**
     * 服务器拒绝请求
     */
    public final static int STATUS_403 = 403;

    /**
     * 资源不存在
     */
    public final static int STATUS_404 = 404;

    /**
     * 未满足期望值, 参数异常
     */
    public final static int STATUS_417 = 417;

    /**
     * 服务端异常
     */
    public final static int STATUS_500 = 500;

    /**
     * 服务不可用
     */
    public final static int STATUS_503 = 503;

    /**
     * 访问超时异常
     */
    public final static int STATUS_504 = 504;
}