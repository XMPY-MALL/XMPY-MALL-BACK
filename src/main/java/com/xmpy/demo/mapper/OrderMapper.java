package com.xmpy.demo.mapper;

import com.xmpy.demo.dto.res.order.OrderResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderResDto> findOrdersByEmail(@Param("email") String email);
}
