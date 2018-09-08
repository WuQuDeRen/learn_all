package com.learn.system.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtil {

	public static String toString(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
	}
	
	public static <T> T fromJson(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	
}
