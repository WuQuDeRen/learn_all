package com.learn.system.util;

import java.lang.reflect.Field;
import java.text.Collator;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
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
	 *
	 * @param sourceList
	 * @param fieldMap  key: 原字段，value：映射字段
	 * @param <S>
	 * @param <R>
	 * @return
	 */
	public static <S, R> List<R> list(List<S> sourceList, Map<String, String> fieldMap, Class<R> resultClass) {
		if (ObjectUtils.isEmpty(sourceList) || ObjectUtils.isEmpty(fieldMap) || resultClass == null) {
			return Lists.newArrayList();
		}
		List<R> resultList = Lists.newArrayList();
		for (S item : sourceList) {
			Class<? extends Object> clazz = item.getClass();
			try {
				R result = resultClass.newInstance();
				for (String keyName : fieldMap.keySet()) {
					Field sourceField = ReflectionUtils.findField(clazz, keyName);
					Field targetField = ReflectionUtils.findField(resultClass, fieldMap.get(keyName));
					if (sourceField != null && targetField != null) {
						try {
							//
							sourceField.setAccessible(true);
							targetField.setAccessible(true);
							// 获取值，设置值
							Object sourceValue = ReflectionUtils.getField(sourceField, item);
							targetField.set(result, sourceValue);
						} catch (Exception e) {
							logger.error("取出标志符组成新集合入参错误 params => fieldMap【{}】", fieldMap, e);
						}
					}
				}
				resultList.add(result);
			} catch (Exception e) {
				logger.error("取出标志符组成新集合入参错误 params => fieldMap【{}】", fieldMap, e);
			}
		}
		return resultList;
	}


	/**
	 * List集合处理  取出标识键组成新集合
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
					logger.error("desc => 依据标识键,生成列表失败 params => keyName【{}】targetList -> {}", keyName, JSONUtils.toString(targetList), e);
				}
			}
		}
		return targetList;
	}


	/**
	 * 移除 null 或 size == 0 或 length == 0
	 * @param sourceList
	 * @param <K>
	 * @return
	 */
	public static <K> List<K> listNotEmpty(List<K> sourceList) {
		if (ObjectUtils.isEmpty(sourceList)) {
			return Lists.newArrayList();
		}
		List<K> resultList = Lists.newArrayList();
		for (K item : sourceList) {
			if (!ObjectUtils.isEmpty(item)) {
				resultList.add(item);
			}
		}
		return resultList;
	}


	/**
	 * List集合处理  取出标志符组成新集合（移除 null 或 ""）
	 * @author ji_fei
	 * 2018年8月28日 下午5:15:36
	 * @param sourceList
	 * @param keyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, T> List<T> listNotEmpty(List<K> sourceList, String keyName) {
		if (ParamUtil.isExistEmpty(sourceList, keyName)) {
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
		targetList = listNotEmpty(targetList);
		return targetList;
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


	/**
	 * 获得初始化的Map 集合
	 * @author ji_fei
	 * @date 2019-04-14 15:30
	 * @param keyValue   key-value 对
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K,V> Map<K, V> initMap(Object... keyValue) {
		Map<K, V> map = Maps.newHashMap();
		if (keyValue.length % 2 != 0) {
			return map;
		}
		for (int i = 0; i < keyValue.length; i += 2) {
			map.put((K) keyValue[i], (V) keyValue[i + 1]);
		}
		return map;
	}


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
					logger.error("映射Map集合出错 params => keyName【{}】targetMap -> {}", keyName, JSONUtils.toString(targetMap), e);
				}
			}
		}
		return targetMap;
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
						logger.error("desc => 依据标识键组装Map失败 params => keyName【{}】mapEntity -> {}", keyName, JSONUtils.toString(mapEntity), e);
					}
				}
			}
		}
		return mapEntityOuter;
	}



	/**
	 * 对入参List<String>进行去重、除null、除空字符串""，并且保持顺序
	 * @param input
	 */
	public static void removeDuplicateAndEmptyItem(List<String> input){
		input.removeAll(Arrays.asList("",null));
		LinkedHashSet<String> set = Sets.newLinkedHashSetWithExpectedSize(input.size());
		set.addAll(input);
		input.clear();
		input.addAll(set);
	}


	public static void main(String[] args) {
		System.out.println(JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(JSONObject.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat));
	}
}
