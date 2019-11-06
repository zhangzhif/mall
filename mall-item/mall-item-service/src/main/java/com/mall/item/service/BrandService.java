package com.mall.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.common.pojo.PageResult;
import com.mall.item.mapper.BrandMapper;
import com.mall.item.pojo.Brand;

import tk.mybatis.mapper.entity.Example;

/**
 * 商标信息service
 * 
 * @author Administrator
 * @date 2019年9月29日
 */
@Service
public class BrandService {

    @Autowired
    public BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc,
        String key) {
        // 开始分页
        rows = rows<=0?0:rows;
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (!StringUtils.isEmpty(key)) {
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        if (!StringUtils.isEmpty(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<Brand> pageInfo = (Page<Brand>)brandMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }
    
    
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        // 新增品牌信息
        this.brandMapper.insertSelective(brand);
        // 新增品牌和分类中间表
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
    
    
    @Transactional
    public void updateBrand(Brand brand, List<Long> cids) {
        // 新增品牌信息
        this.brandMapper.updateByPrimaryKey(brand);
        // 删除品牌和分类中间表
        this.brandMapper.deleteCategoryBrand(brand.getId());
        // 重新插入品牌和分类中间表
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
    
    
    @Transactional
    public void deleteBrand(long bid) {
        // 新增品牌信息
        this.brandMapper.deleteByPrimaryKey(bid);
        // 删除品牌和分类中间表
        this.brandMapper.deleteCategoryBrand(bid);
    }
    
    
    /**
     * 根据分类id查询商品信息
     * @param cid
     * @return
     */
    public List<Brand> queryBrandByCategoryId(long cid){
        return this.brandMapper.queryByCategoryId(cid);
    }
    
    public Brand queryById(Long id){
        return this.brandMapper.selectByPrimaryKey(id);
    }
    
}
