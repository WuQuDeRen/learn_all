package com.learn.order.module.order.dao;

import com.learn.order.module.order.domain.po.Order;

public interface OrderDao {
    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}