package com.learn.system.util;

import com.google.common.collect.Maps;
import com.learn.system.enums.ExceptionMsgEnum;
import com.learn.system.exception.ServerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 校验器
 * @author ji_fei
 * 2018年11月3日	下午5:43:51
 */
public class ValidatorUtil {
	private static final Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 方法名
     */
    private String methodName;
    
    /**
     * 参数
     */
    private Map<String, Object> params = Maps.newHashMap();
    
    public ValidatorUtil(String serviceName, String methodName){
        this.serviceName = serviceName;
        this.methodName = methodName;
    }

    /**
     * 对应  集合/数组/字符串/普通对象 为null或长度为0
     * @author ji_fei
     * 2018年11月1日 下午3:49:38
     * @param obj
     * @param paramName
     * @return
     */
    public ValidatorUtil objectNotEmpty(Object obj, String errorDesc) {
        if (ObjectUtils.isEmpty(obj)) {
        	StringBuilder builder = new StringBuilder("error【{}】params => serviceName【{}】methodName【{}】");
            logger.error(builder.append(strParam()).toString(), errorDesc, serviceName, methodName);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
    
    /**
     * 对应  集合/数组/字符串/普通对象 为null或长度为0
     * @author ji_fei
     * 2018年11月1日 下午3:49:38
     * @param obj
     * @param paramName
     * @return
     */
    public ValidatorUtil objectNotEmpty(Object obj) {
    	 if (ObjectUtils.isEmpty(obj)) {
    		 StringBuilder builder = new StringBuilder("error【参数为NULL或长度为0】params => serviceName【{}】methodName【{}】");
             logger.error(builder.append(strParam()).toString(), serviceName, methodName);
             throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
         }
         return this;
    }
    
    /**
     * 对应  字符串 为 非空
     * @author ji_fei
     * 2018年11月1日 下午3:50:06
     * @param str
     * @param paramName
     * @return
     * @throws ServerException
     */
    public ValidatorUtil stringNotEmpty(String str, String errorDesc) throws ServerException {
        if (StringUtils.isEmpty(str)) {
        	StringBuilder builder = new StringBuilder("error【{}】params => serviceName【{}】methodName【{}】");
            logger.error(builder.append(strParam()).toString(), errorDesc, serviceName, methodName);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
    
    /**
     * 对应  字符串 长度为0
     * @author ji_fei
     * 2018年11月1日 下午3:50:06
     * @param str
     * @param paramName
     * @return
     * @throws ServerException
     */
    public ValidatorUtil stringNotEmpty(String str) throws ServerException {
        if (StringUtils.isEmpty(str)) {
        	StringBuilder builder = new StringBuilder("error【参数为NULL或长度为0】params => serviceName【{}】methodName【{}】");
            logger.error(builder.append(strParam()).toString(), serviceName, methodName);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
    
    /**
     * 对应  集合/数组/字符串/普通对象 为null
     * @author ji_fei
     * 2018年11月1日 下午3:50:54
     * @param obj
     * @param paramName
     * @return
     */
    public ValidatorUtil objectNotNull(Object obj, String errorDesc) {
        if (obj == null) {
        	StringBuilder builder = new StringBuilder("error【{}】params => serviceName【{}】methodName【{}】");
            logger.error(builder.append(strParam()).toString(), errorDesc, serviceName, methodName);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
    
    /**
     * 对应  集合/数组/字符串/普通对象 为null
     * @author ji_fei
     * 2018年11月1日 下午3:50:54
     * @param obj
     * @param paramName
     * @return
     */
    public ValidatorUtil objectNotNull(Object obj) {
        if (obj == null) {
        	StringBuilder builder = new StringBuilder("error【参数为NULl】params => serviceName【{}】methodName【{}】");
            logger.error(builder.append(strParam()).toString(), serviceName, methodName);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
    
    /**
     * 针对字符串 为 null
     * @author ji_fei
     * 2018年11月1日 下午3:51:28
     * @param obj
     * @param paramName
     * @return
     */
    public ValidatorUtil stringNotNull(String obj, String errorDesc) {
        if (obj == null) {
        	StringBuilder builder = new StringBuilder("error【{}】params => serviceName【{}】methodName【{}】");
            logger.error(builder.append(strParam()).toString(), errorDesc, serviceName, methodName);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
    
    /**
     * 针对字符串 为 null
     * @author ji_fei
     * 2018年11月1日 下午3:51:28
     * @param obj
     * @param paramName
     * @return
     */
    public ValidatorUtil stringNotNull(String obj) {
        if (obj == null) {
        	StringBuilder builder = new StringBuilder("error【字符串为NULL】params => serviceName【{}】methodName【{}】");
            logger.error(builder.append(strParam()).toString(), serviceName, methodName);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
    
    public ValidatorUtil objectKeyNotEmpty(Object obj, String propertyName, String errorDesc) {
    	if (ParamUtil.isExistEmpty(obj, propertyName)) {
    		StringBuilder builder = new StringBuilder("error【{}}】params => serviceName【{}】methodName【{}】");
    		logger.error(builder.append(strParam()).toString(), errorDesc, serviceName, methodName);
    		throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
    	}
    	return this;
    }
    
    public ValidatorUtil objectKeyNotEmpty(Object obj, String propertyName) {

    	if (ParamUtil.isExistEmpty(obj, propertyName)) {
    		StringBuilder builder = new StringBuilder("error【对象或关键字为NULL或长度为0】params => serviceName【{}】methodName【{}】");
    		logger.error(builder.append(strParam()).toString(), serviceName, methodName);
    		throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
    	}
    	return this;
    }
    
    public static ValidatorUtil getInstance(String serviceName, String methodName) {
    	return new ValidatorUtil(serviceName, methodName);
    }
    
    /**
     * 追加参数
     * @author ji_fei
     * 2018年11月3日 下午5:11:03
     * @param paramName
     * @param paramValue
     * @return
     */
    public ValidatorUtil addParam(String paramName, Object paramValue) {
    	params.put(paramName, paramValue);
    	return this;
    }
    
    private String strParam() {
    	StringBuilder builder = new StringBuilder("");
    	for (Map.Entry<String, Object>  entry : params.entrySet()) {
    		builder.append(" ").append(entry.getKey()).append(": ").append(JSONUtils.toString(entry.getValue()));
    	}
    	return builder.toString();
    }
    
}
