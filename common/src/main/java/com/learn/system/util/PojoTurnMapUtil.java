package com.learn.system.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.learn.system.enums.ExceptionMsgEnum;
import com.learn.system.exception.ServerException;

/**
 * Description 实体类、map互转util
 * @Author WindPursuer
 * @Date 2018/5/7 下午4:37
 */
public class PojoTurnMapUtil {

    private static final Logger logger = LoggerFactory.getLogger(PojoTurnMapUtil.class);

    /**
     * Description 将实体转化成map
     * @Author WindPursuer
     * @Date 2018/5/7 下午4:48
     */
    public static Map<String, Object> beanToMap(Object bean) {
        if (null == bean) {
            logger.error("参数为空，转化失败");
            throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
        }
        Class<? extends Object> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            Map<String, Object> returnMap = Maps.newHashMap();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    returnMap.put(field.getName(), field.get(bean));
                }
            } catch (IllegalAccessException e) {
                logger.error("转化异常:【{}】", e.getMessage(), e);
                return null;
            }
            return returnMap;
        }
        return null;
    }


    /**
     * Description 将map转化成bean
     * @Author WindPursuer
     * @Date 2018/5/7 下午4:47
     */
    public static Object map2Bean(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
