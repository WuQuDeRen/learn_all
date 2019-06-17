package com.learn.test.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 门店实体	
 * @author ji_fei
 * 2018年7月11日	下午2:14:28
 */
public class Shop implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6974092106744767553L;

	/**
     * 
     */
    private Integer id;

    /**
     * 门店号（从AuthZ中获取）
     */
    private String shopNo;

    /**
     * 门店名
     */
    private String shopName;
    
    /**
     * 店铺简称
     */
    private String shortName;

    /**
     * 集团号
     */
    private String groupNo;

    /**
     * 是否有效。‘yes’，‘no’
     */
    private String isValid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 门店号（从AuthZ中获取）
     * @return shop_no 门店号（从AuthZ中获取）
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 门店号（从AuthZ中获取）
     * @param shopNo 门店号（从AuthZ中获取）
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 门店名
     * @return shop_name 门店名
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 门店名
     * @param shopName 门店名
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 集团号
     * @return group_no 集团号
     */
    public String getGroupNo() {
        return groupNo;
    }

    /**
     * 集团号
     * @param groupNo 集团号
     */
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo == null ? null : groupNo.trim();
    }

    /**
     * 是否有效。‘yes’，‘no’
     * @return is_valid 是否有效。‘yes’，‘no’
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 是否有效。‘yes’，‘no’
     * @param isValid 是否有效。‘yes’，‘no’
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
    
}