package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.Size;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SizeMapper {
    Long findIdByName(@Param("sizeName") String sizeName);
    void insertSize(Size size);
}
