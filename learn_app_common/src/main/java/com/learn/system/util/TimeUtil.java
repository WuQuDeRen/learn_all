package com.learn.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learn.system.enums.ExceptionMsgEnum;
import com.learn.system.exception.ServerException;

public class TimeUtil {
	
	private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);
	
	/**
	 * 按照分钟统计 时长（不足1分钟按照一分钟计算）
	 * @author ji_fei
	 * 2018年8月27日 上午11:54:15
	 * @param a  被除数
	 * @param b  除数
	 * @return
	 */
	public static int getCallDurationUnitMin(long a, long b) {
		if (a < b || b < 0) {
			throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
		}
		long source = (a - b);
		// 分钟 
		int unit = 60 * 1000;
		if (source % unit == 0) {
			return (int) (source / unit);
		}
		return (int) ((source / unit) + 1);
	}
	
	/**
	 * 按照秒统计 时长
	 * @author ji_fei
	 * 2018年8月28日 上午9:16:40
	 * @return
	 */
	public static int getCallDuartionUnitSecond(long a, long b) {
		if (a < b || a < 0) {
			logger.error("exception【计算通话时长 入参异常】params => a【{}】b【{}】", a, b);
			throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
		}
		long source = (a - b);
		// 单位：秒
		double unit = 1000.0;
		// 秒
		double second = source / unit;
		if (source % unit != 0) {
			second += 1.0;
		}
		logger.info("info【计算通话时长】response => second【[}】params => a【{}】b【{}】", second, a, b);
		return (int) second;
	}
	
	/**
	 * 秒 换算成 分钟
	 * @author ji_fei
	 * 2018年8月28日 上午9:27:52
	 * @param a 
	 * @return
	 */
	public static int getCallDruationUnitMinute(Integer a) {
		if (a == null || a < 0) {
			logger.error("exception【计算通话时长 入参异常】params => a【{}】", a);
			throw new ServerException(ExceptionMsgEnum.PARAMS_EMPTY);
		}
		// 单位: 分钟
		double unit = 60.0;
		double minute = a / unit;
		if (a % unit != 0) {
			minute += 1;
		}
		logger.info("info【计算通话时长】response => minute【[}】params => a【{}】", minute, a);
		return (int) minute;
	}
}
