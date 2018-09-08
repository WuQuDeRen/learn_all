package com.learn.system.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class RegUtil {
	
	private static Logger logger = LoggerFactory.getLogger(RegUtil.class);
	
	/**
	 * 获取排除emojo图的字符串
	 * @author ji_fei
	 * 2018年8月13日 下午2:47:53
	 * @param content
	 * @return
	 */
	public static String excludeEmojo(String content) {
		logger.info("正则表达式排除emojo图 params => content [{}]", content);
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		StringBuilder regBuilder = reg();
		Pattern pattern = Pattern.compile(regBuilder.toString());
		Matcher matcher = pattern.matcher(content);
		StringBuilder builder = new StringBuilder("");
		while (matcher.find()) {
			builder.append(matcher.group());
		}
		return builder.toString();
	}

	/**
	 * 获取排除emojo图的正则
	 * @author ji_fei
	 * 2018年8月13日 下午2:47:29
	 * @return
	 */
	public static StringBuilder reg() {
		StringBuilder regBuilder = new StringBuilder("");
		String start = "(";
		String end = ")*";
		String or = "|";
		regBuilder.append(start);
		// 英文标点
		regBuilder.append("[\\[\\]\\{\\}\\(\\)]");
		regBuilder.append(or);
		// 英文标点
		regBuilder.append("[\\,|\\.|\\/|\\<|\\>|\\?|\\;|\\'|\\'|\\:|\\\"|\\\"|\\_|\\+|\\-|\\=|\\*]");
		regBuilder.append(or);
		// 英文标点
		regBuilder.append("[\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\`|\\~|\\~]");
		regBuilder.append(or);
		// 中文标点
		regBuilder.append("[\\u3002|\\uff1f|\\uff01|\\uff0c|\\u3001|\\uff1b|\\uff1a|\\u201c|\\u201d|\\u2018|\\u2019|\\uff08|\\uff09|\\u300a|\\u300b|\\u3008|\\u3009|\\u3010|\\u3011|\\u300e|\\u300f|\\u300c|\\u300d|\\ufe43|\\ufe44|\\u3014|\\u3015|\\u2026|\\u2014|\\uff5e|\\ufe4f|\\uffe5]");
		regBuilder.append(or);
		// 中文，数字以及单词字符
		regBuilder.append("[\\u4e00-\\u9fa5]|\\d|\\w");
		regBuilder.append(end);
		return regBuilder;
	}
}
