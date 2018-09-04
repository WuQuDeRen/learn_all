package com.learn.tmp;

import com.alibaba.fastjson.JSON;

public class Order extends BasicEntity {
	private String name;
	private String orderNo;
	
	public String age;
	
	public Order() {
		super();
	}
	
	public Order(String orderNo, String name) {
		super();
		this.name = name;
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
