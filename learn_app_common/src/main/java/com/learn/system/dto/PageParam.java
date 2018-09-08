package com.learn.system.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class PageParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1959589150125340511L;
	/**	当前页码（从1开始）	*/
    private Integer currentPageIndex;
    /**	分页查询数据量	*/
    private Integer size;
    /**	mysql分页参数-开始位编号	*/
    private Integer mysqlBegin;
    /**	oracle分页参数-开始位编号	*/
    private Integer oracleBegin;
    /**	oracle分页参数-结束位编号	*/
    private Integer oracleEnd;
    
    private PageParam(){}
    
    /**
     * 以当前页码初始化
     * @param size	分页查询数据量
     * @param currentPageIndex	当前页码（从1开始）
     * @return
     */
    public static PageParam instanceForPageIndex(int size,int currentPageIndex){
    	return instanceForPageIndex(size, currentPageIndex, false);
    }
    
    /**
     * 以当前页码初始化
     * @param size	分页查询数据量
     * @param currentPageIndex	当前页码
     * @param isStartAtZero	当前页码是否从0开始
     * @return
     */
    public static PageParam instanceForPageIndex(int size,int currentPageIndex,boolean isStartAtZero){
    	PageParam pageParam = new PageParam();
    	pageParam.size=size;
    	if(isStartAtZero){
    		currentPageIndex+=1;
    	}
    	pageParam.currentPageIndex=currentPageIndex;
    	pageParam.mysqlBegin=(pageParam.currentPageIndex-1)*pageParam.size;
    	pageParam.oracleBegin=pageParam.mysqlBegin+1;
    	pageParam.oracleEnd=pageParam.mysqlBegin+pageParam.size;
    	return pageParam;
    }
    
    /**
     * 以当前数据编号初始化
     * @param size	分页查询数据量
     * @param currentDataIndex	当前数据编号（从1开始）
     * @return
     */
    public static PageParam instanceForDataIndex(int size,int currentDataIndex){
    	return instanceForDataIndex(size, currentDataIndex, false);
    }
    
    /**
     * 以当前数据编号初始化
     * @param size	分页查询数据量
     * @param currentDataIndex	当前数据编号
     * @param isStartAtZero	当前数据编号是否从0开始
     * @return
     */
    public static PageParam instanceForDataIndex(int size,int currentDataIndex,boolean isStartAtZero){
    	PageParam pageParam = new PageParam();
    	pageParam.size=size;
    	if(isStartAtZero){
    		currentDataIndex+=1;
    	}
    	pageParam.mysqlBegin=currentDataIndex-1;
    	pageParam.oracleBegin=currentDataIndex;
    	pageParam.oracleEnd=pageParam.mysqlBegin+pageParam.size;
    	int pageIndex = currentDataIndex/pageParam.size;
    	pageParam.currentPageIndex=currentDataIndex%pageParam.size==0?pageIndex:pageIndex+1;
    	return pageParam;
    }

	public Integer getCurrentPageIndex() {
		return currentPageIndex;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getMysqlBegin() {
		return mysqlBegin;
	}

	public Integer getOracleBegin() {
		return oracleBegin;
	}

	public Integer getOracleEnd() {
		return oracleEnd;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
	
}
