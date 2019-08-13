package com.learn.system.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class ParamUtil {

	private static Logger logger = LoggerFactory.getLogger(ParamUtil.class);

    /**
	 * 判断是否均为空
	 * @author ji_fei
	 * 2018年7月30日 下午3:29:47
	 * @param obj
	 * @return
	 */
	public static boolean allEmpty(Object... obj) {
		if (obj != null) {
			for (Object item : obj) {
				if (!ObjectUtils.isEmpty(item)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断存在一个为空的
	 * @author ji_fei
	 * 2018年7月30日 下午3:35:23
	 * @return
	 */
	public static boolean existEmpty(Object...obj) {
		if (obj == null) {
			return true;
		}
		for (Object item : obj) {
			if (ObjectUtils.isEmpty(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断参数为 Null
	 * @param obj
	 * @return
	 */
	public static boolean allNull(Object... obj) {
		if (obj == null) {
			return true;
		}
		for (Object item : obj) {
			if (item != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @desc 存在 Null
	 * @author fei_ji
	 * @date 2019-07-16 10:13
	 * @param obj
	 * @return
	 */
	public static boolean existNull(Object... obj) {
		if (obj == null) {
			return true;
		}

		for (Object item : obj) {
			if (item == null) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 对象以及其表示符是否为空
	 * @author ji_fei
	 * 2018年9月13日 下午3:57:31
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	private static boolean isEmpty(Object obj, String fieldName) {
		if (obj == null || StringUtils.isEmpty(fieldName)) {
			return true;
		}
		Field field = ReflectionUtils.findField(obj.getClass(), fieldName);
		if (field == null) {
			return false;
		}
		boolean result = true;
		try {
			field.setAccessible(true);
			if (!ObjectUtils.isEmpty(field.get(obj))) {
				result = false;
			}
		} catch (Exception e) {
			logger.error("exception【获取字段异常】");
		}
		return result;
	}

	/**
	 * 是否为存在空
	 * @author ji_fei
	 * 2018年9月18日 下午4:41:27
	 * @param obj
	 * @param fieldNameList
	 * @return
	 */
	public static boolean existEmpty(Object obj, String ... fieldNameList) {
		if (obj == null || fieldNameList == null) {
			return true;
		}
		for (String fieldName : fieldNameList) {
			if (isEmpty(obj, fieldName)) {
				return true;
			}
		}
		return false;
	}





}
