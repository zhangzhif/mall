 package com.mall.item.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

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
}
