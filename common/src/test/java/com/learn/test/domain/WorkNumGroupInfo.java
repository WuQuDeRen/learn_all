package com.learn.test.domain;

import java.io.Serializable;
import java.util.Date;


public class WorkNumGroupInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3248447174353240225L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 集团号
     */
    private String groupNo;

    /**
     * 门店号
     */
    private String shopNo;

    /**
     * 工作号信息号
     */
    private String workNumInfoNo;

    /**
     * 工作号
     */
    private String workNum;

    /**
     * 是否绑定 ‘yes’，‘no’
     */
    private String isBind;

    /**
     * 绑定的用户姓名
     */
    private String bindUseName;

    /**
     * 绑定的电话号码
     */
    private String bindPhoneNum;

    /**
     * 集团侧的失效时间
     */
    private Date groupInvalidTime;

    /**
     * 集团侧的工作号状态。工作号状态-‘NORMAL’：正常，‘INVALID’：已失效，‘WILL_INVALID’：即将失效
     */
    private String workNumStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 门店号
     * @return shop_no 门店号
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 门店号
     * @param shopNo 门店号
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 工作号信息号
     * @return work_num_info_no 工作号信息号
     */
    public String getWorkNumInfoNo() {
        return workNumInfoNo;
    }

    /**
     * 工作号信息号
     * @param workNumInfoNo 工作号信息号
     */
    public void setWorkNumInfoNo(String workNumInfoNo) {
        this.workNumInfoNo = workNumInfoNo == null ? null : workNumInfoNo.trim();
    }

    /**
     * 工作号
     * @return work_num 工作号
     */
    public String getWorkNum() {
        return workNum;
    }

    /**
     * 工作号
     * @param workNum 工作号
     */
    public void setWorkNum(String workNum) {
        this.workNum = workNum == null ? null : workNum.trim();
    }

    /**
     * 是否绑定 ‘yes’，‘no’
     * @return is_bind 是否绑定 ‘yes’，‘no’
     */
    public String getIsBind() {
        return isBind;
    }

    /**
     * 是否绑定 ‘yes’，‘no’
     * @param isBind 是否绑定 ‘yes’，‘no’
     */
    public void setIsBind(String isBind) {
        this.isBind = isBind == null ? null : isBind.trim();
    }

    /**
     * 绑定的用户姓名
     * @return bind_use_name 绑定的用户姓名
     */
    public String getBindUseName() {
        return bindUseName;
    }

    /**
     * 绑定的用户姓名
     * @param bindUseName 绑定的用户姓名
     */
    public void setBindUseName(String bindUseName) {
        this.bindUseName = bindUseName == null ? null : bindUseName.trim();
    }

    /**
     * 绑定的电话号码
     * @return bind_phone_num 绑定的电话号码
     */
    public String getBindPhoneNum() {
        return bindPhoneNum;
    }

    /**
     * 绑定的电话号码
     * @param bindPhoneNum 绑定的电话号码
     */
    public void setBindPhoneNum(String bindPhoneNum) {
        this.bindPhoneNum = bindPhoneNum == null ? null : bindPhoneNum.trim();
    }

    /**
     * 集团侧的失效时间
     * @return group_invalid_time 集团侧的失效时间
     */
    public Date getGroupInvalidTime() {
        return groupInvalidTime;
    }

    /**
     * 集团侧的失效时间
     * @param groupInvalidTime 集团侧的失效时间
     */
    public void setGroupInvalidTime(Date groupInvalidTime) {
        this.groupInvalidTime = groupInvalidTime;
    }

    /**
     * 集团侧的工作号状态。工作号状态-‘NORMAL’：正常，‘INVALID’：已失效，‘WILL_INVALID’：即将失效
     * @return work_num_status 集团侧的工作号状态。工作号状态-‘NORMAL’：正常，‘INVALID’：已失效，‘WILL_INVALID’：即将失效
     */
    public String getWorkNumStatus() {
        return workNumStatus;
    }

    /**
     * 集团侧的工作号状态。工作号状态-‘NORMAL’：正常，‘INVALID’：已失效，‘WILL_INVALID’：即将失效
     * @param workNumStatus 集团侧的工作号状态。工作号状态-‘NORMAL’：正常，‘INVALID’：已失效，‘WILL_INVALID’：即将失效
     */
    public void setWorkNumStatus(String workNumStatus) {
        this.workNumStatus = workNumStatus == null ? null : workNumStatus.trim();
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


    public WorkNumGroupInfo(Integer id, String groupNo, String shopNo, String workNumInfoNo, String workNum, String isBind, String bindUseName, String bindPhoneNum, Date groupInvalidTime, String workNumStatus, Date createTime) {
        this.id = id;
        this.groupNo = groupNo;
        this.shopNo = shopNo;
        this.workNumInfoNo = workNumInfoNo;
        this.workNum = workNum;
        this.isBind = isBind;
        this.bindUseName = bindUseName;
        this.bindPhoneNum = bindPhoneNum;
        this.groupInvalidTime = groupInvalidTime;
        this.workNumStatus = workNumStatus;
        this.createTime = createTime;
    }

    public WorkNumGroupInfo() {
    }
}