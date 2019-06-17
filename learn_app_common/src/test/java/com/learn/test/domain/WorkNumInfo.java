package com.learn.test.domain;

import java.io.Serializable;
import java.util.Date;


public class WorkNumInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 721872680575737866L;

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
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 分发日期
     */
    private Date handOutDate;

    /**
     * 失效日期
     */
    private Date invalidTime;

    /**
     * 真实的失效时间，用于失效时间早于预计失效时间
     */
    private Date realInvalidTime;

    /**
     * 已经使用短信条数
     */
    private Integer smsUsed;

    /**
     * 使用通话时间数（秒）
     */
    private Integer callTimeUsed;

    /**
     * 接通次数
     */
    private Integer timeOfCallGoThrough;

    /**
     * 拨打和被打的数量
     */
    private Integer timesOfCall;

    /**
     * 是否绑定。‘yes’，‘no’
     */
    private String isBind;

    /**
     * 绑定的电话号码
     */
    private String bindPhoneNum;

    /**
     * 绑定的用户名
     */
    private String bindUserName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 三元组的绑定关系ID
     */
    private String bindId;

    /**
     * 工作号是否有效（yes/no）
     */
    private String isValid;

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
     * 省份
     * @return province 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 省份
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 城市
     * @return city 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 分发日期
     * @return hand_out_date 分发日期
     */
    public Date getHandOutDate() {
        return handOutDate;
    }

    /**
     * 分发日期
     * @param handOutDate 分发日期
     */
    public void setHandOutDate(Date handOutDate) {
        this.handOutDate = handOutDate;
    }

    /**
     * 失效日期
     * @return invalid_time 失效日期
     */
    public Date getInvalidTime() {
        return invalidTime;
    }

    /**
     * 失效日期
     * @param invalidTime 失效日期
     */
    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    /**
     * 真实的失效时间，用于失效时间早于预计失效时间
     * @return real_invalid_time 真实的失效时间，用于失效时间早于预计失效时间
     */
    public Date getRealInvalidTime() {
        return realInvalidTime;
    }

    /**
     * 真实的失效时间，用于失效时间早于预计失效时间
     * @param realInvalidTime 真实的失效时间，用于失效时间早于预计失效时间
     */
    public void setRealInvalidTime(Date realInvalidTime) {
        this.realInvalidTime = realInvalidTime;
    }

    /**
     * 已经使用短信条数
     * @return sms_used 已经使用短信条数
     */
    public Integer getSmsUsed() {
        return smsUsed;
    }

    /**
     * 已经使用短信条数
     * @param smsUsed 已经使用短信条数
     */
    public void setSmsUsed(Integer smsUsed) {
        this.smsUsed = smsUsed;
    }

    /**
     * 使用通话时间数（秒）
     * @return call_time_used 使用通话时间数（秒）
     */
    public Integer getCallTimeUsed() {
        return callTimeUsed;
    }

    /**
     * 使用通话时间数（秒）
     * @param callTimeUsed 使用通话时间数（秒）
     */
    public void setCallTimeUsed(Integer callTimeUsed) {
        this.callTimeUsed = callTimeUsed;
    }

    /**
     * 接通次数
     * @return time_of_call_go_through 接通次数
     */
    public Integer getTimeOfCallGoThrough() {
        return timeOfCallGoThrough;
    }

    /**
     * 接通次数
     * @param timeOfCallGoThrough 接通次数
     */
    public void setTimeOfCallGoThrough(Integer timeOfCallGoThrough) {
        this.timeOfCallGoThrough = timeOfCallGoThrough;
    }

    /**
     * 拨打和被打的数量
     * @return times_of_call 拨打和被打的数量
     */
    public Integer getTimesOfCall() {
        return timesOfCall;
    }

    /**
     * 拨打和被打的数量
     * @param timesOfCall 拨打和被打的数量
     */
    public void setTimesOfCall(Integer timesOfCall) {
        this.timesOfCall = timesOfCall;
    }

    /**
     * 是否绑定。‘yes’，‘no’
     * @return is_bind 是否绑定。‘yes’，‘no’
     */
    public String getIsBind() {
        return isBind;
    }

    /**
     * 是否绑定。‘yes’，‘no’
     * @param isBind 是否绑定。‘yes’，‘no’
     */
    public void setIsBind(String isBind) {
        this.isBind = isBind == null ? null : isBind.trim();
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
     * 绑定的用户名
     * @return bind_user_name 绑定的用户名
     */
    public String getBindUserName() {
        return bindUserName;
    }

    /**
     * 绑定的用户名
     * @param bindUserName 绑定的用户名
     */
    public void setBindUserName(String bindUserName) {
        this.bindUserName = bindUserName == null ? null : bindUserName.trim();
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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

    /**
     * 修改者
     * @return modifier 修改者
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改者
     * @param modifier 修改者
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 三元组的绑定关系ID
     * @return bind_id 三元组的绑定关系ID
     */
    public String getBindId() {
        return bindId;
    }

    /**
     * 三元组的绑定关系ID
     * @param bindId 三元组的绑定关系ID
     */
    public void setBindId(String bindId) {
        this.bindId = bindId == null ? null : bindId.trim();
    }

    /**
     * 对集团来说，工作号是否有效（yes/no）
     * @return is_valid 对集团来说，工作号是否有效（yes/no）
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 对集团来说，工作号是否有效（yes/no）
     * @param isValid 对集团来说，工作号是否有效（yes/no）
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }


    public WorkNumInfo(Integer id, String groupNo, String shopNo, String workNumInfoNo, String workNum, String province, String city, Date handOutDate, Date invalidTime, Date realInvalidTime, Integer smsUsed, Integer callTimeUsed, Integer timeOfCallGoThrough, Integer timesOfCall, String isBind, String bindPhoneNum, String bindUserName, String creator, Date createTime, String modifier, Date updateTime, String bindId, String isValid) {
        this.id = id;
        this.groupNo = groupNo;
        this.shopNo = shopNo;
        this.workNumInfoNo = workNumInfoNo;
        this.workNum = workNum;
        this.province = province;
        this.city = city;
        this.handOutDate = handOutDate;
        this.invalidTime = invalidTime;
        this.realInvalidTime = realInvalidTime;
        this.smsUsed = smsUsed;
        this.callTimeUsed = callTimeUsed;
        this.timeOfCallGoThrough = timeOfCallGoThrough;
        this.timesOfCall = timesOfCall;
        this.isBind = isBind;
        this.bindPhoneNum = bindPhoneNum;
        this.bindUserName = bindUserName;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.updateTime = updateTime;
        this.bindId = bindId;
        this.isValid = isValid;
    }


    public WorkNumInfo() {
    }
}