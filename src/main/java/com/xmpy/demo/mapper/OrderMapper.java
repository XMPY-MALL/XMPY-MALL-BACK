package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.OrderItem;
import com.xmpy.demo.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insertOrder(Orders orders);
    void insertOrderItems(List<OrderItem> orderItems);
}