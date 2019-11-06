 package com.mall.item.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mall.item.pojo.Brand;

import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper<Brand>{
    
    /**
     * 商标品牌关系维护
     * @param cid
     * @param bid
     * @return
     */
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);
    
    @Delete("DELETE FROM tb_category_brand WHERE brand_id = #{bid}")
    int deleteCategoryBrand(@Param("bid") Long bid);
    
    @Select("SELECT b.* FROM tb_brand b LEFT JOIN tb_category_brand cb ON b.id = cb.brand_id WHERE cb.category_id = #{cid}")
    List<Brand> queryByCategoryId(@Param("cid") Long cid);
}
