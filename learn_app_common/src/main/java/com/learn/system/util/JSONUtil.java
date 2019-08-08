package com.learn.system.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JSONUtil {

	/**
	 * 字符串序列化为 json 字符串（打印到日志里）
	 * @param obj
	 * @return
	 */
	@Deprecated
	public static String toStringLog(Object obj) {
		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 字符串序列化为 json 字符串
	 * @author ji_fei
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 字符串序列化为 json 字符串, 字段会进行排序
	 * @author ji_fei
	 * @param obj
	 * @return
	 */
	public static String toStringSorted(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.SortField);
	}

	/**
	 * @desc
	 * @author fei_ji
	 * @date 2019-07-11 15:02
	 * @param list
	 * @return
	 */
	public static <T> List copyList(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList();
		}
		return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
	}

	/**
	 * @desc 根据 Map 获取对应的 对象
	 * @author fei_ji
	 * @date 2019-07-24 16:09
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
		if (map == null) {
			return null;
		}
		return new JSONObject(map).toJavaObject(clazz);
	}

	/**
	 * json 字符串反序列化
	 * @author ji_fei
	 * @param json   {null, "", jsonStr}
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

}
