package com.learn.system.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {

	/**
	 * 字符串序列化为 json 字符串
	 * @author ji_fei
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 字符串序列化为 json 字符串, 字段会进行排序
	 * @author ji_fei
	 * @param obj
	 * @return
	 */
	public static String toStringSortField(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.SortField);
	}

	/**
	 * json 字符串反序列化
	 * @author ji_fei
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	
}
