package com.learn.system.converter;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.learn.system.dto.Page;
import com.learn.system.dto.PageParam;

public class GenericConverter {
	
	private static Logger logger = LoggerFactory.getLogger(GenericConverter.class);
	/**
	 * 通用方式（不会抛出异常）
	 * @author ji_fei
	 * 2018年8月15日 下午3:16:44
	 * @param source
	 * @param targetClazz
	 * @return  null/obj
	 */
	public static <V, D> V toItem(D source, Class<V> targetClazz) {
		if (!ObjectUtils.isEmpty(source)) {
			V target = null;
			try {
				target = targetClazz.newInstance();
			} catch (Exception e) {
				logger.error("exception【DTO 转 VO 错误】", e);
				return null;
			}
			BeanUtils.copyProperties(source, target);
			return target;
		}
		logger.error("exception【D 转 V 错误】params => dto【{}】", JSON.toJSONString(source));
		return null;
	}
	
	/**
	 * 通用方式类型转换（必然返回非空指针集合）
	 * @author ji_fei
	 * 2018年8月15日 下午3:16:52
	 * @param sourceList
	 * @param targetClazz
	 * @return  !null集合
	 */
	public static <V, D> List<V> toItems(List<D> sourceList, Class<V> targetClazz) {
		if (!CollectionUtils.isEmpty(sourceList)) {
			List<V> targetList = Lists.newArrayList();
			for (D item : sourceList) {
				V target = toItem(item, targetClazz);
				if (target != null) {
					targetList.add(target);
				}
			}
			return targetList;
		}
		logger.error("exception【D list 转 V list 错误】params => dto【{}】", JSON.toJSONString(sourceList));
		return Lists.newArrayList();
	}
	
	/**
	 * 通用的属性拷贝
	 * @author ji_fei
	 * 2018年8月15日 下午5:12:47
	 * @param sourceList
	 * @param targetClazz
	 * @param pageParam
	 * @param totalNum
	 * @return 非空对象
	 */
	public static <V, D> Page<V> toPage(List<D> sourceList, Class<V> targetClazz, PageParam pageParam, Integer totalNum) {
		if (CollectionUtils.isNotEmpty(sourceList)) {
			List<V> items = toItems(sourceList, targetClazz);
			if (CollectionUtils.isNotEmpty(items)) {
				Page<V> pageInfo = new Page<V>(totalNum, pageParam.getCurrentPageIndex(), pageParam.getSize(), items);
				return pageInfo;
			}
		}
		return new Page<V>(0, pageParam.getCurrentPageIndex(), pageParam.getSize(), Lists.newArrayList());
	}
	
	/**
	 * 通用数据拷贝 分页
	 * @author ji_fei
	 * 2018年8月16日 上午10:02:11
	 * @param page
	 * @param targetClazz
	 * @return
	 */
	public static <V, D> Page<V> toPage(Page<D> page,  Class<V> targetClazz) {
		if (!ObjectUtils.isEmpty(page) && CollectionUtils.isNotEmpty(page.getItems())) {
			List<V> items = toItems(page.getItems(), targetClazz);
			if (CollectionUtils.isNotEmpty(items)) {
				return new Page<V>(page.getTotalNumber(), page.getCurrentIndex(), page.getPageSize(), items);
			}
		}
		logger.warn("warnning【入参警告，无分页数据】params => page【{}】targetClazz【{}】", JSON.toJSONString(page), targetClazz);
		return new Page<V>(0, page.getCurrentIndex(), page.getPageSize(), Lists.newArrayList());
	}
	
	/**
	 * 通用数据拷贝 分页
	 * @author ji_fei
	 * 2018年8月16日 上午10:02:11
	 * @param page
	 * @param targetClazz
	 * @return
	 */
	public static <V, D> Page<V> toPageByJson(Page<D> page,  Class<V> targetClazz) {
		if (!ObjectUtils.isEmpty(page) && CollectionUtils.isNotEmpty(page.getItems())) {
			List<V> items = toListByJson(page.getItems(), targetClazz);
			if (CollectionUtils.isNotEmpty(items)) {
				return new Page<V>(page.getTotalNumber(), page.getCurrentIndex(), page.getPageSize(), items);
			}
		}
		logger.warn("warnning【入参警告，无分页数据】params => page【{}】targetClazz【{}】", JSON.toJSONString(page), targetClazz);
		return new Page<V>(0, page.getCurrentIndex(), page.getPageSize(), Lists.newArrayList());
	}
	
	/**
	 * 获取分页数据
	 * @author ji_fei
	 * 2018年8月16日 上午11:47:27
	 * @param sourceList
	 * @param pageParam
	 * @param totalNum
	 * @return
	 */
	public static <V> Page<V> getPage(List<V> sourceList, PageParam pageParam, Integer totalNum) {
		if (CollectionUtils.isNotEmpty(sourceList) && pageParam != null && totalNum != null && totalNum > 0) {
			return new Page<V>(totalNum, pageParam.getCurrentPageIndex(), pageParam.getSize(), sourceList);
		}
		logger.warn("warnning【入参警告，无分页数据】params => sourceList【{}】pageParam【{}】totalNum【{}】", JSON.toJSONString(sourceList), JSON.toJSONString(pageParam), totalNum);
		return new Page<V>(0, pageParam.getCurrentPageIndex(), pageParam.getSize(), Lists.newArrayList());
	}
	
	/**
	 * 通过fastjson正反序列化转换类型
	 * @author ji_fei
	 * 2018年8月21日 下午2:07:26
	 * @param sourceList
	 * @param targetClazz
	 * @return
	 */
	public static <S, T> List<T> toListByJson(List<S> sourceList, Class<T> targetClazz) {
		if (CollectionUtils.isEmpty(sourceList) || targetClazz == null) {
			return Lists.newArrayList();
		}
		List<T> targetList = JSON.parseArray(JSON.toJSONString(sourceList), targetClazz);
		if (targetList == null) {
			targetList = Lists.newArrayList();
		}
		return targetList;
	}
	
	/**
	 * 生成单个对象
	 * @author ji_fei
	 * 2018年8月24日 下午6:13:18
	 * @param source
	 * @param targetClazz
	 * @return
	 */
	public static <S, T> T toItemByJson(S source, Class<T> targetClazz) {
		if (source == null || targetClazz == null) {
			return null;
		}
		T target = JSON.parseObject(JSON.toJSONString(source), targetClazz);
		if (target == null) {
			target = null;
		}
		return target;
	}
	
	
	/**
	 * 将值变成字符串
	 * @author ji_fei
	 * 2018年8月21日 下午2:14:32
	 * @param obj
	 * @return
	 */
	public static String toStr(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj);
	}
	
	/**
	 * 由Integer转Long
	 * @author ji_fei
	 * 2018年8月22日 下午12:56:00
	 * @param source
	 * @return
	 */
	public static Long fromInteger(Integer source) {
		if (source == null) {
			return null;
		}
		int target = source;
		return (long) target;
	}
	
	/**
	 * Long转 Integer
	 * @author ji_fei
	 * 2018年8月22日 下午12:57:55
	 * @param source
	 * @return
	 */
	public static Integer fromLong(Long source) {
		if (source == null) {
			return null;
		}
		long target = source;
		return (int) target;
	}
	
}
