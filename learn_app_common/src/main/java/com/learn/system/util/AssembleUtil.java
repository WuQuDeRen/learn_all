package com.learn.system.util;

import java.lang.reflect.Field;
import java.text.Collator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
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
	public static <K, T> Map<K, T> mapEntity(List<T> sourceList, String keyName) {
		if (ParamUtil.isExistEmpty(sourceList, keyName)) {
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
	public static <K, T> List<T> list(List<K> sourceList, String keyName) {
		if (ParamUtil.isExistEmpty(sourceList, keyName)) {
			logger.error("desc => 入参异常,无法组装列表 params => keyName【{}】", keyName);
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
					logger.error("desc => 依据标识键,生成列表失败 params => keyName【{}】", keyName, e);
				}
			}
		}
		return targetList;
	}

	/**
	 * 组装 Map 集合
	 * @param sourceList
	 * @param keyNameList
	 * @param <K>
	 * @param <T>
	 * @return  {"key1": [value1, value2, ..., valuen], "key2": [value1, value2, ..., valuen]}
	 */
	public static <K, T> Map<String, Map<K, T>> mapEntity(List<T> sourceList, String... keyNameList) {
		if (ParamUtil.isExistEmpty(sourceList, keyNameList)) {
			return Maps.newHashMap();
		}
		Map<String, Map<K, T>> mapEntityOuter = Maps.newHashMap();
		for (String keyName : keyNameList) {
			if (StringUtils.isEmpty(keyName)) {
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
						logger.error("desc => 依据标识键组装Map异常 params => keyName【{}】", keyName, e);
					}
				}
			}
		}
		return mapEntityOuter;
	}

	public static <T> String join(List<T> sourceList, String separator) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < sourceList.size(); i++) {
			builder.append(JSON.toJSONString(sourceList.get(i)));
			if (i < sourceList.size() -1) {
				builder.append(separator);
			}
		}
		return builder.toString();
	}


}
