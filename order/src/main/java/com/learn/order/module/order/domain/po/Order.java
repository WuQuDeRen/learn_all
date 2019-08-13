package com.learn.order.module.order.domain.po;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("order")
public class Order {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderNo;

    /**
     * 实付金额
     */
    private String paymentPrice;

    /**
     * 支付类型
     */
    private String paymentType;

    /**
     * 邮费 精确到2位小数
     */
    private BigDecimal postFee;

    /**
     * 交易状态 1: 未付款 2: 已付款 3: 未发货 4: 已发货 5：交易成功 6：交易关闭
     */
    private String status;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date consignTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 交易关闭时间
     */
    private Date closeTime;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流code
     */
    private String shippingCode;

    /**
     * 
     */
    private String userId;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 卖家昵称
     */
    private String buyerNick;

    /**
     *  买家是否已评价
     */
    private String buyerRate;

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
     * 订单ID
     * @return order_no 订单ID
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 订单ID
     * @param orderNo 订单ID
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 实付金额
     * @return payment_price 实付金额
     */
    public String getPaymentPrice() {
        return paymentPrice;
    }

    /**
     * 实付金额
     * @param paymentPrice 实付金额
     */
    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice == null ? null : paymentPrice.trim();
    }

    /**
     * 支付类型
     * @return payment_type 支付类型
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * 支付类型
     * @param paymentType 支付类型
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    /**
     * 邮费 精确到2位小数
     * @return post_fee 邮费 精确到2位小数
     */
    public BigDecimal getPostFee() {
        return postFee;
    }

    /**
     * 邮费 精确到2位小数
     * @param postFee 邮费 精确到2位小数
     */
    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    /**
     * 交易状态 1: 未付款 2: 已付款 3: 未发货 4: 已发货 5：交易成功 6：交易关闭
     * @return status 交易状态 1: 未付款 2: 已付款 3: 未发货 4: 已发货 5：交易成功 6：交易关闭
     */
    public String getStatus() {
        return status;
    }

    /**
     * 交易状态 1: 未付款 2: 已付款 3: 未发货 4: 已发货 5：交易成功 6：交易关闭
     * @param status 交易状态 1: 未付款 2: 已付款 3: 未发货 4: 已发货 5：交易成功 6：交易关闭
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 订单创建时间
     * @return create_time 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 订单创建时间
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建时间
     * @return update_time 创建时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 创建时间
     * @param updateTime 创建时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 支付时间
     * @return payment_time 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 支付时间
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 发货时间
     * @return consign_time 发货时间
     */
    public Date getConsignTime() {
        return consignTime;
    }

    /**
     * 发货时间
     * @param consignTime 发货时间
     */
    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    /**
     * 交易完成时间
     * @return end_time 交易完成时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 交易完成时间
     * @param endTime 交易完成时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 交易关闭时间
     * @return close_time 交易关闭时间
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * 交易关闭时间
     * @param closeTime 交易关闭时间
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 物流名称
     * @return shipping_name 物流名称
     */
    public String getShippingName() {
        return shippingName;
    }

    /**
     * 物流名称
     * @param shippingName 物流名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    /**
     * 物流code
     * @return shipping_code 物流code
     */
    public String getShippingCode() {
        return shippingCode;
    }

    /**
     * 物流code
     * @param shippingCode 物流code
     */
    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode == null ? null : shippingCode.trim();
    }

    /**
     * 
     * @return user_id 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId 
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 买家留言
     * @return buyer_message 买家留言
     */
    public String getBuyerMessage() {
        return buyerMessage;
    }

    /**
     * 买家留言
     * @param buyerMessage 买家留言
     */
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    /**
     * 卖家昵称
     * @return buyer_nick 卖家昵称
     */
    public String getBuyerNick() {
        return buyerNick;
    }

    /**
     * 卖家昵称
     * @param buyerNick 卖家昵称
     */
    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick == null ? null : buyerNick.trim();
    }

    /**
     *  买家是否已评价
     * @return buyer_rate  买家是否已评价
     */
    public String getBuyerRate() {
        return buyerRate;
    }

    /**
     *  买家是否已评价
     * @param buyerRate  买家是否已评价
     */
    public void setBuyerRate(String buyerRate) {
        this.buyerRate = buyerRate == null ? null : buyerRate.trim();
    }
}