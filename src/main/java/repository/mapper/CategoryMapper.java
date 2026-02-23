package repository.mapper;

import entity.Category;
import entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {



    // 카테고리 디테일 전체조회 (상의 하의 하의 안 카테고리)
    List<Category> findAll();


    // 디테일 Id로 조회해서 수정

    Category findByCategoryDetailId(@Param("categoryDetailId") long categoryDetailId);
}
