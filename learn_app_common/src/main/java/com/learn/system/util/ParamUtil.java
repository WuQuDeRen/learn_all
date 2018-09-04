package com.learn.system.util;

import java.util.Collection;

import org.springframework.util.ObjectUtils;


public class ParamUtil {
	
	/**
	 * 判断是否均为空
	 * @author ji_fei
	 * 2018年7月30日 下午3:29:47
	 * @param obj
	 * @return
	 */
	public static boolean isAllEmpty(Object... obj) {
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
	 * 判断存在一个非空
	 * @author ji_fei
	 * 2018年7月30日 下午3:35:23
	 * @return
	 */
	public static boolean isAnyEmpty(Object...obj) {
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
	 * 长度为0
	 * @author ji_fei
	 * 2018年8月31日 下午4:08:29
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("null")
	public static boolean isZeroNotEmpty(Collection<?> collection) {
		if (collection != null || collection.size() == 0) {
			return true;
		}
		return false;
	}
}
