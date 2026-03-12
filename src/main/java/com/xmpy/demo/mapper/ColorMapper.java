package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.Color;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ColorMapper {
    Long findIdByName(@Param("colorName") String colorName);
    void insertColor(Color color);
}
