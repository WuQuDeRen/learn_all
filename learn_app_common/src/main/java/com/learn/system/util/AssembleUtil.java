package com.learn.system.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * Map集合处理
 * @author ji_fei
 * 2018年8月28日	下午5:09:13
 */
public class AssembleUtil {
	
	private static Logger logger = LoggerFactory.getLogger(AssembleUtil.class);
	
	/**
	 * 指定集合
	 * @author ji_fei
	 * 2018年8月28日 下午5:14:43
	 * @param sourceList
	 * @param keyName   Map集合的键  
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, T> Map<K, T> mapEntity(List<T> sourceList, String keyName) {
		if (ParamUtil.isAnyEmpty(sourceList, keyName)) {
			logger.error("映射Map集合出错 params => keyName【{}】", keyName);
			return Maps.newHashMap();
		}
		Map<K, T> targetMap = Maps.newHashMap();
		for (T item : sourceList) {
			Class<? extends Object> clazz = item.getClass();
			Field findField = ReflectionUtils.findField(clazz, keyName);
			if (findField != null) {
				try {
					findField.setAccessible(true);
					Object value = ReflectionUtils.getField(findField, item);
					targetMap.put((K) value, item);
					findField.setAccessible(false);
				} catch (Exception e) {
					logger.error("映射Map集合出错 params => keyName【{}】", keyName, e);
				}
			}
		}
		return targetMap;
	}
	
	/**
	 * List集合处理  取出标志符组成新集合
	 * @author ji_fei
	 * 2018年8月28日 下午5:15:36
	 * @param sourceList
	 * @param keyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, T> List<T> list(List<K> sourceList, String keyName) {
		if (ParamUtil.isAnyEmpty(sourceList, keyName)) {
			logger.error("取出标志符组成新集合入参错误 params => keyName【{}】", keyName);
			return Lists.newArrayList();
		}
		List<T> targetList = Lists.newArrayList();
		for (K item : sourceList) {
			Class<? extends Object> clazz = item.getClass();
			Field findField = ReflectionUtils.findField(clazz, keyName);
			if (findField != null) {
				try {
					findField.setAccessible(true);
					Object value = ReflectionUtils.getField(findField, item);
					targetList.add((T) value);
					findField.setAccessible(false);
				} catch (Exception e) {
					logger.error("取出标志符组成新集合入参错误 params => keyName【{}】", keyName, e);
				}
			}
		}
		return targetList;
	}
	
	@SuppressWarnings("unchecked")
	public static <K, T> Map<String, Map<K, T>> mapEntity(List<T> sourceList, String[] keyNameList) {
		if (ParamUtil.isAnyEmpty(sourceList, keyNameList)) {
			return Maps.newHashMap();
		}
		Map<String, Map<K, T>> mapEntityOuter = Maps.newHashMap();
		for (String keyName : keyNameList) {
			if (keyName == null) {
				continue;
			}
			Map<K, T> mapEntity = Maps.newHashMap();
			mapEntityOuter.put(keyName, mapEntity);
			for (T item : sourceList) {
				Class<? extends Object> clazz = item.getClass();
				Field field = ReflectionUtils.findField(clazz, keyName);
				if (field != null) {
					try {
						field.setAccessible(true);
						Object value = ReflectionUtils.getField(field, item);
						field.setAccessible(false);
						mapEntity.put((K) value, item);
					} catch (Exception e) {
						logger.error("取出标志符组成新集合入参错误 params => keyName【{}】", keyName, e);
					}
				}
			}
		}
		return mapEntityOuter;
	}
}
