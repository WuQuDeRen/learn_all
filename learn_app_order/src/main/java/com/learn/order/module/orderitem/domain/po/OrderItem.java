package com.learn.order.module.orderitem.domain.po;

public class OrderItem {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 
     */
    private Integer itemId;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品单价
     */
    private Long price;

    /**
     * 商品总金额
     */
    private Long totalPrice;

    /**
     * 商品图片地址
     */
    private String picPath;

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
     * 
     * @return item_id 
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 
     * @param itemId 
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 商品数量
     * @return num 商品数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 商品数量
     * @param num 商品数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 商品标题
     * @return title 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 商品标题
     * @param title 商品标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 商品单价
     * @return price 商品单价
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 商品单价
     * @param price 商品单价
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 商品总金额
     * @return total_price 商品总金额
     */
    public Long getTotalPrice() {
        return totalPrice;
    }

    /**
     * 商品总金额
     * @param totalPrice 商品总金额
     */
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 商品图片地址
     * @return pic_path 商品图片地址
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * 商品图片地址
     * @param picPath 商品图片地址
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }
}