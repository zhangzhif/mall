 package com.mall.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.item.mapper.SpecificationMapper;
import com.mall.item.pojo.Specification;

@Service
 public class SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;
    
    /**
     * 根据id查询商品规格
     * @param id
     * @return
     */
    public Specification queryById(long id){
       return specificationMapper.selectByPrimaryKey(id);
    }
}
