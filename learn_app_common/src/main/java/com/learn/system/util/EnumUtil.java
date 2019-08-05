package com.learn.system.util;

import com.learn.system.enums.ExceptionMsgEnum;
import com.learn.system.exception.ServerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 枚举工具
 */
public class EnumUtil {

    private static Logger logger = LoggerFactory.getLogger(EnumUtil.class);

    /**
     * 通过枚举名获取枚举值，必然会有会返回值，无异常抛出
     * @author ji_fei
     * @param enumClass
     * @param enumName
     * @param <T>
     * @return
     */
    public static <T extends Enum> T getEnumByName(Class<T> enumClass, String enumName) {
        T targetEnum = null;
        try {
            targetEnum = (T) Enum.valueOf(enumClass, enumName);
        } catch (Exception e) {
            logger.error("desc => 枚举获取异常 params => enumClass -> {} enumName -> {}", enumClass, enumName);
        }
        return targetEnum;
    }

    /**
     * 通过枚举名获取枚举值，无法类型转换时异常抛出： ExceptionMsgEnum.DATA_NOT_MATCH
     * @author ji_fei
     * @param enumClass
     * @param enumName
     * @param <T>
     * @return
     */
    public static  <T extends Enum> T getEnumByNameWithException(Class<T> enumClass, String enumName) throws ServerException {
        T targetEnum = getEnumByName(enumClass, enumName);
        if (targetEnum == null) {
            logger.error("desc => 枚举获取异常 params => enumClass -> {} enumName -> {}", enumClass, enumName);
            throw new ServerException(ExceptionMsgEnum.DATA_NOT_MATCH);
        }
        return targetEnum;
    }

    /**
     * 通过枚举值的某个属性获取枚举，必然会有会返回值，无异常抛出
     * @param enumClass
     * @param keyName
     * @param keyValue
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends Enum, K> T getEnumByField(Class<T> enumClass, String keyName, K keyValue) {
        T[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants != null) {
            try {
                for (T item : enumConstants) {
                    Field field = ReflectionUtils.findField(enumClass, keyName);
                    field.setAccessible(true);
                    Object value = ReflectionUtils.getField(field, item);
                    if (StringUtils.equals(JSONUtils.toStringSorted(keyValue), JSONUtils.toStringSorted(value))) {
                        return  item;
                    }
                }
            } catch (Exception e) {
                throw new ServerException(ExceptionMsgEnum.DATA_NOT_MATCH);
            }

        }
        throw new ServerException(ExceptionMsgEnum.DATA_NOT_MATCH);
    }

    /**
     * 通过枚举值的某个属性获取枚举，无法类型转换时异常抛出： ExceptionMsgEnum.DATA_NOT_MATCH
     * @author: ji_fei
     * @param enumClass
     * @param keyName
     * @param keyValue
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends Enum, K> T getEnumByFieldWithException(Class<T> enumClass, String keyName, K keyValue) throws ServerException {
        T enumByField = getEnumByField(enumClass, keyName, keyValue);
        if (enumByField == null) {
            logger.error("desc => 枚举获取异常 params => enumClass -> {} keyName -> {} keyValue -> {}", enumClass, keyName, JSONUtils.toString(keyValue));
            throw new ServerException(ExceptionMsgEnum.DATA_NOT_MATCH);
        }
        return enumByField;
    }

}
