package com.learn.system.util;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 对象初始化工具
 */
public class ObjectInitUtil {

    private static Logger logger = LoggerFactory.getLogger(ParamUtil.class);

    /**
     * 置空对象中的属性字段（排除部分字段）
     * @author ji_fei
     * 2018年9月25日 上午11:26:03
     * @param obj
     * @param
     * @return
     */
    public static Object resetNullExcludeField(Object obj, String ...excludeFieldList) {
        if (obj == null) {
            return obj;
        }
        Class<? extends Object> clazz = obj.getClass();
        if (excludeFieldList != null && excludeFieldList.length > 0) {
            List<String> filedNameList = Arrays.asList(excludeFieldList);
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field item : declaredFields) {
                if (!filedNameList.contains(item.getName())) {
                    item.setAccessible(true);
                    try {
                        item.set(obj, null);
                    } catch (Exception e) {
                        logger.error("exception【置空失败】params => obj【{}】", JSONUtils.toString(obj), e);
                    }
                }
            }
        }
        return obj;
    }

    /**
     * 置空对象中的属性字段（只置空对应字段）
     * @author ji_fei
     * 2018年9月25日 上午11:59:46
     * @param obj
     * @param includeFieldList
     * @return
     */
    public static Object resetNullIncludeField(Object obj, String ...includeFieldList) {
        if (obj == null) {
            return obj;
        }
        Class<? extends Object> clazz = obj.getClass();

        if (includeFieldList != null && includeFieldList.length > 0) {
            List<String> filedNameList = Arrays.asList(includeFieldList);
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field item : declaredFields) {
                if (filedNameList.contains(item.getName())) {
                    item.setAccessible(true);
                    try {
                        item.set(obj, null);
                    } catch (Exception e) {
                        logger.error("exception【置空失败】params => obj【{}】", JSONUtils.toString(obj), e);
                    }
                }
            }
        }
        return obj;
    }

    public static <T> List<T> initList(T... args) {
        List<T> targetList = Lists.newArrayList();
        if (args == null) {
            return targetList;
        }
        for (T item : args) {
            targetList.add(item);
        }
        return targetList;
    }

}
