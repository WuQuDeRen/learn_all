package com.learn.order.module.orderitem.dao;

import com.learn.order.module.orderitem.domain.po.OrderItem;

public interface OrderItemDao {
    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}