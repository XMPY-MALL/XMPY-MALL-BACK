package com.xmpy.demo.mapper;

import com.xmpy.demo.dto.res.order.OrderRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderRes> findAdminOrders();

    int updateOrderStatus(@Param("ordersId") long ordersId,
                          @Param("status") String status);
}