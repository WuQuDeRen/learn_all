package com.learn.system.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;


class A {
	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date currentTime;


	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
}
class B {
	private Integer currentTime;

	public Integer getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Integer currentTime) {
		this.currentTime = currentTime;
	}
}
public class JSONUtils {

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
	 * 字符串序列化为 json 字符串（打印到日志里）
	 * @param obj
	 * @return
	 */
	public static String toStrLog(Object obj) {
		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
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

	public static void main(String[] args) {
		String json = "{\"currentTime\": \"2019-12-12 10:12\"}";
		String jsons = "{\"currentTime\": \"12\"}";

		B b  = new B();
		b.setCurrentTime(12);
		System.out.println(toStrLog(b));
	}
}
