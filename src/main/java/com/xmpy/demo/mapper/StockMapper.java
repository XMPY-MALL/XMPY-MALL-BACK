package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StockMapper {

    List<Stock> findByProductId(@Param("productId") long productId);


    int countByOption(@Param("productId") long productId,
                      @Param("sizeId") long sizeId,
                      @Param("colorId") long colorId);

    int insertStock(@Param("productId") long productId,
                    @Param("sizeId") long sizeId,
                    @Param("colorId") long colorId,
                    @Param("count") int count);

    List<Stock> findStocksByProductId(@Param("productId") long productId);

    int updateStockCount(@Param("stockId") long stockId,
                         @Param("count") int count);

    int deleteStock(@Param("stockId") long stockId);

    // 주문 확정 -> 재고 차감
    int decreaseStock(@Param("productId") long productId,
                      @Param("sizeId") long sizeId,
                      @Param("colorId") long colorId,
                      @Param("quantity") int quantity);

    // 주문 취소/실패 -> 재고 복구
    int restoreStock(@Param("productId") long productId,
                     @Param("sizeId") long sizeId,
                     @Param("colorId") long colorId,
                     @Param("quantity") int quantity);

    List<Map<String, Object>> findAllSizes();

    List<Map<String, Object>> findAllColors();

}