package com.learn.system.util;


import com.learn.system.enums.ExceptionMsgEnum;
import com.learn.system.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * @desc 参数校验器 (依托 日志的 MDC 功能来判断当前的请求参数)
 * @author ji_fei
 * @date  2019-07-11 15:29
 */
public class ValidatorUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * @desc 实例化校验器
     * @author fei_ji
     * @date 2019-07-11 15:38
     * @param
     * @return
     */
    public static ValidatorUtil instance() {
        return new ValidatorUtil();
    }

    /**
     * @desc 判断对象非空，非 NULL
     * @author fei_ji
     * @date 2019-07-11 16:27
     * @param paramName
     * @param obj
     * @return
     */
    public ValidatorUtil notEmpty(String paramName, Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            logger.error("desc => 参数异常 params => key【{}】value【{}】", paramName, obj);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }

    /**
     * @desc 判断对象非空，非 NULL
     * @author fei_ji
     * @date 2019-07-11 16:27
     * @param obj
     * @return
     */
    public ValidatorUtil notEmpty(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            logger.error("desc => 参数异常 params => value【{}】", obj);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }

    /**
     * @desc 判断对象非 NULL
     * @author fei_ji
     * @date 2019-07-11 16:27
     * @param paramName
     * @param obj
     * @return
     */
    public ValidatorUtil notNull(String paramName, Object obj) {
        if (obj == null) {
            logger.error("desc => 参数异常 params => {}【{}】", paramName, obj);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }

    /**
     * @desc 判断对象非 NULL
     * @author fei_ji
     * @date 2019-07-11 16:28
     * @param obj
     * @return
     */
    public ValidatorUtil notNull(Object obj) {
        if (obj == null) {
            logger.error("desc => 参数异常 params => value【{}】", obj);
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        return this;
    }
}
