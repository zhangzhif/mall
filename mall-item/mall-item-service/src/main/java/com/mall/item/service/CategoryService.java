package com.mall.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.item.mapper.CategoryMapper;
import com.mall.item.pojo.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    
    /**
     * 新增分类
     * @param category
     */
    public void saveCategory(Category category){
        //将他的父节点修改成有子节点
        Category parentCategory = new Category();
        parentCategory.setId(category.getParentId());
        parentCategory.setIsParent(true);
        categoryMapper.updateByPrimaryKeySelective(parentCategory);
        categoryMapper.insert(category);
    }
    
    
    /**
     * 修改分类
     * @param category
     */
    @Transactional
    public void updateCategory(Category category){
    
        categoryMapper.updateByPrimaryKey(category);
    }
    
    /**
     * 删除分类
     * @param cid
     */
    public void delCategory(Long cid){
        categoryMapper.deleteByPrimaryKey(cid);
    }
    
    
    /**
     * 根据parentId查询子类目
     * 
     * @param pid
     * @return
     */
    public List<Category> queryCategoryListByParentId(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }
    
    public List<Category>  queryByBrandId(Long bid){
        return this.categoryMapper.queryByBrandId(bid);
    }
}
