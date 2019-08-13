package com.learn.order.module.ordershipping.dao;

import com.learn.order.module.ordershipping.domain.po.OrderShipping;

public interface OrderShippingDao {
    int insert(OrderShipping record);

    int insertSelective(OrderShipping record);

    OrderShipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderShipping record);

    int updateByPrimaryKey(OrderShipping record);
}