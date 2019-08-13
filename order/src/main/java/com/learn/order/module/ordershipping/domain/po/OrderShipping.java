package com.learn.order.module.ordershipping.domain.po;

import java.util.Date;

public class OrderShipping {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 收货人名字
     */
    private String receiverName;

    /**
     * 收件人手机号
     */
    private String receiverPhone;

    /**
     * 收件人移动电话
     */
    private String receiverMobile;

    /**
     * 省份
     */
    private String receiverProvince;

    /**
     * 城市
     */
    private String receiverCity;

    /**
     * 区/县
     */
    private String receiverDistrict;

    /**
     * 收货人地址
     */
    private String receiverAddress;

    /**
     * 邮编
     */
    private String receiverZip;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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
     * 订单号
     * @return order_no 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单号
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 收货人名字
     * @return receiver_name 收货人名字
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 收货人名字
     * @param receiverName 收货人名字
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 收件人手机号
     * @return receiver_phone 收件人手机号
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 收件人手机号
     * @param receiverPhone 收件人手机号
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    /**
     * 收件人移动电话
     * @return receiver_mobile 收件人移动电话
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * 收件人移动电话
     * @param receiverMobile 收件人移动电话
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    /**
     * 省份
     * @return receiver_province 省份
     */
    public String getReceiverProvince() {
        return receiverProvince;
    }

    /**
     * 省份
     * @param receiverProvince 省份
     */
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince == null ? null : receiverProvince.trim();
    }

    /**
     * 城市
     * @return receiver_city 城市
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * 城市
     * @param receiverCity 城市
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    /**
     * 区/县
     * @return receiver_district 区/县
     */
    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    /**
     * 区/县
     * @param receiverDistrict 区/县
     */
    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
    }

    /**
     * 收货人地址
     * @return receiver_address 收货人地址
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 收货人地址
     * @param receiverAddress 收货人地址
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    /**
     * 邮编
     * @return receiver_zip 邮编
     */
    public String getReceiverZip() {
        return receiverZip;
    }

    /**
     * 邮编
     * @param receiverZip 邮编
     */
    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip == null ? null : receiverZip.trim();
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
}