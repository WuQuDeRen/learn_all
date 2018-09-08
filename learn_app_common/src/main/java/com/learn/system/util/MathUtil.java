package com.learn.system.util;

import java.math.BigDecimal;

public class MathUtil {
	
	
	/**
	 * 是否为偶数
	 * @param val
	 * @return
	 */
	public static boolean isEven(int val) {
		
		return val % 2 == 0 ? true : false;
	}
	
	/** 
	* @description: 参数是否为正
	* @author: fei_ji 
	* @date: 2018年4月10日 下午5:00:52
	* @version: v1.0
	* @param cmp
	* @return  
	*/
	public static boolean isPositive(BigDecimal cmp) {
		Integer rvt = compareBigDecimal(cmp, BigDecimal.ZERO);
		if (rvt > 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* @description: 参数是否为负
	* @author: fei_ji 
	* @date: 2018年4月10日 下午5:00:40
	* @version: v1.0
	* @param cmp
	* @return  
	*/
	public static boolean isNegative(BigDecimal cmp) {
		Integer rvt = compareBigDecimal(cmp, BigDecimal.ZERO);
		if (rvt < 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* @description: 参数是否为0
	* @author: fei_ji 
	* @date: 2018年4月10日 下午5:00:21
	* @version: v1.0
	* @param cmp
	* @return  
	*/
	public static boolean isZero(BigDecimal cmp) {
		int rvt = compareBigDecimal(BigDecimal.ZERO, cmp);
		if (rvt == 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* @description: 参数一是否大于参数二
	* @author: fei_ji 
	* @date: 2018年4月10日 下午5:00:03
	* @version: v1.0
	* @param source
	* @param target
	* @return  
	*/
	public static boolean isGt(BigDecimal source, BigDecimal target) {
		int rvt = compareBigDecimal(source, target);
		if (rvt > 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* @description: 参数一是否大于等于参数二
	* @author: fei_ji 
	* @date: 2018年4月10日 下午4:59:39
	* @version: v1.0
	* @param source
	* @param target
	* @return  
	*/
	public static boolean isGe(BigDecimal source, BigDecimal target) {
		int rvt = compareBigDecimal(source, target);
		if (rvt >= 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* @description: 参数一 是否小于 参数二
	* @author: fei_ji 
	* @date: 2018年4月10日 下午4:59:20
	* @version: v1.0
	* @param source
	* @param target
	* @return  
	*/
	public static boolean isLt(BigDecimal source, BigDecimal target) {
		int rvt = compareBigDecimal(source, target);
		if (rvt < 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* @description: 参数一是否小于等于参数二
	* @author: fei_ji 
	* @date: 2018年4月10日 下午4:59:02
	* @version: v1.0
	* @param source
	* @param target
	* @return  
	*/
	public static boolean isLe(BigDecimal source, BigDecimal target) {
		int rvt = compareBigDecimal(source, target);
		if (rvt <= 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* @description: 参数一是否等于参数二
	* @author: fei_ji 
	* @date: 2018年4月10日 下午4:58:04
	* @version: v1.0
	* @param source
	* @param target
	* @return  
	*/
	public static boolean isEq(BigDecimal source, BigDecimal target) {
		int rvt = compareBigDecimal(source, target);
		if (rvt == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 比较BigDcimal的大小
	 * @param source 被减数
	 * @param target 减数
	 * @return 1：source > target; -1: source < target; 0： source = target
	 */
	public static Integer compareBigDecimal(BigDecimal source, BigDecimal target) {
		int compareTo = source.compareTo(target);
		return compareTo;
	}
	
}
