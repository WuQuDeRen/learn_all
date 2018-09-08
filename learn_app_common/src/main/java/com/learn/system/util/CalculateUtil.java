package com.learn.system.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CalculateUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CalculateUtil.class);
	
	public static final Integer SECOND = 0;
	
	public static final Integer MINUTE = 1;
	
	public static final Integer HOUR = 2;
	
	public static final Integer DAY = 3;
	
	public static String[] chineseFormat = {"秒", "分钟", "小时", "天"};
	
	
	/**
	 * 计算百分率
	 * @author ji_fei
	 * 2018年8月20日 下午6:41:35
	 * @param dividend 被除数
	 * @param divider 除数
	 * @return
	 */
	public static String getPercent(Integer dividend, Integer divider) {
		if (dividend == null || divider == null || divider == 0) {
			return "0";
		}
		return new BigDecimal(dividend / (divider * 1.0) * 100).setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
	}
	
	/**
	 * 将 秒   转换成   xx天xx小时xx分xx秒   格式
	 * @author ji_fei
	 * 2018年8月22日 下午2:33:43
	 * @param totalSeconds
	 * @param startIndex 最大单位 0：秒 1:分 2:小时 3: 天
	 * @param endIndex 最小单位 0：秒 1:分 2:小时 3: 天
	 * @return
	 * convertTime(3, 1)  满足要求时：转换成  xx天xx小时xx分
	 */
	public static String convertTime(Integer totalSeconds, Integer startIndex, Integer endIndex) {
		if (ParamUtil.isAnyEmpty(totalSeconds, startIndex, endIndex) || startIndex < endIndex) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = startIndex; i >= 0; i--) {
			int base = (int) Math.pow(60, i);
			int tmp = totalSeconds % base;
			int consult = totalSeconds / base;
			if (consult > 0) {
				builder.append(totalSeconds / base).append(chineseFormat[i]);
			}
			if (tmp != 0) {
				totalSeconds = tmp;
			} else {
				break;
			}
		}
		return builder.toString();
	}
	
	/**
	 * 向上取整（辗转相处）
	 * @author ji_fei
	 * 2018年8月22日 下午2:44:57
	 * @param totalSeconds
	 * @param startIndex 最大单位 0：秒 1:分 2:小时 3: 天
	 * @param endIndex 最小单位 0：秒 1:分 2:小时 3: 天
	 * @return
	 */
	public static String convertTimeCeil(Integer totalSeconds, Integer startIndex, Integer endIndex) {
		if (ParamUtil.isAnyEmpty(totalSeconds, startIndex, endIndex) || startIndex < endIndex) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = startIndex; i >= endIndex; i--) {
			int base = (int) Math.pow(60, i);
			int tmp = totalSeconds % base;
			int consult = totalSeconds / base;
			if (consult > 0) {
				if (i == endIndex && tmp != 0) {
					builder.append((totalSeconds / base) + 1).append(chineseFormat[i]);
				} else {
					builder.append(totalSeconds / base).append(chineseFormat[i]);
				}
			} else {
				if (i == endIndex && tmp != 0) {
					builder.append((totalSeconds / base) + 1).append(chineseFormat[i]);
				} 
			}
			if (tmp != 0) {
				totalSeconds = tmp;
			} else {
				break;
			}
		}
		return builder.toString();
	}
	
	/**
	 * 不足一分钟按照一分钟计算
	 * @author ji_fei
	 * 2018年8月22日 下午12:52:00
	 * @param dividend
	 * @param divide
	 * @return
	 */
	public static Integer getMinutes(Integer dividend, Integer divide) {
		if (dividend == null || divide == null || divide == 0) {
			return null;
		}
		if (dividend % divide != 0) {
			return (dividend / divide) + 1;
		}
		return dividend / divide;
	}
	
	/**
	 * 向上取整（辗转相处）最小位置 分钟
	 * @author ji_fei
	 * 2018年8月22日 下午2:44:57
	 * @param totalSeconds
	 * @param startIndex 最大单位 1:分 2:小时 3: 天
	 * @param endIndex 最小单位 1:分 2:小时 3: 天 （入参必须大于 0）
	 * @return
	 */
	public static String convertTimeCeilUnitMin(Integer totalMintues, Integer startIndex, Integer endIndex) {
		logger.info("分钟转化通话时长 params => totalMintues【{}】startIndex【{}】endIndex【{}】", totalMintues, startIndex, endIndex);
		if (ParamUtil.isAnyEmpty(totalMintues, startIndex, endIndex) || startIndex < endIndex || endIndex == 0) {
			logger.error("分钟转化通话时长 入参异常 params => totalMintues【{}】startIndex【{}】endIndex【{}】", totalMintues, startIndex, endIndex);
			return "";
		}
		Integer totalSeconds = totalMintues * 60;
		StringBuilder builder = new StringBuilder();
		for (int i = startIndex; i >= endIndex; i--) {
			int base = (int) Math.pow(60, i);
			int tmp = totalSeconds % base;
			int consult = totalSeconds / base;
			if (consult > 0) {
				if (i == endIndex && tmp != 0) {
					builder.append((totalSeconds / base) + 1).append(chineseFormat[i]);
				} else {
					builder.append(totalSeconds / base).append(chineseFormat[i]);
				}
			} else {
				if (i == endIndex && tmp != 0) {
					builder.append((totalSeconds / base) + 1).append(chineseFormat[i]);
				} 
			}
			if (tmp != 0) {
				totalSeconds = tmp;
			} else {
				break;
			}
		}
		return builder.toString();
	}
	
	public static String getCallTimeStr(Integer totalMintues, Integer startIndex, Integer endIndex) {
		String timeFormat = convertTimeCeilUnitMin(totalMintues, startIndex, endIndex);
		if (StringUtils.isEmpty(timeFormat)) {
			return "0";
		}
		return timeFormat;
	}
	
	public static String getSmsUsed(Integer usedSmsNum) {
		return usedSmsNum == null ? "0" : usedSmsNum + "";
	}
}
