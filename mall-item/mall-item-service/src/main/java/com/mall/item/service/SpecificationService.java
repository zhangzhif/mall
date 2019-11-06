 package com.mall.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.mall.item.mapper.SpecGroupMapper;
import com.mall.item.mapper.SpecParamMapper;
import com.mall.item.mapper.SpecificationMapper;
import com.mall.item.pojo.SpecGroup;
import com.mall.item.pojo.SpecParam;
import com.mall.item.pojo.Specification;

@Service
 public class SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecGroupMapper specGroupMapper;
    
    @Autowired
    private SpecParamMapper specParamMapper;
    
    
    //分组信息处理begin
    /**
     * 新增规格组
     * @param group
     */
    public void saveGroup(SpecGroup group){
        specGroupMapper.insert(group);
    }
    
    
    /**
     * 更新规格组
     * @param group
     */
    public void updateGroup(SpecGroup group){
        specGroupMapper.updateByPrimaryKeySelective(group);
    }
    
    /**
     * 删除规格组
     * @param group
     */
    @Transactional
    public void delGroup(Long gid){
        //同时删除分组下面的内容信息
        SpecParam param  = new SpecParam();
        param.setGroupId(gid);
        specParamMapper.delete(param);
        specGroupMapper.deleteByPrimaryKey(gid);
    }
    
    
    
    /**
     * 通过商品分类查找规格组
     * @return
     */
    public List<SpecGroup> queryGroupByCid(long cid){
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return specGroupMapper.select(record);
    }
    
    
    /**
     * 跟据组id查询参数
     *
     * @param gid
     * @return
     */
    public List<SpecParam> queryParamByList(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam p = new SpecParam();
        p.setGroupId(gid);
        p.setCid(cid);
        p.setSearching(searching);
        p.setGeneric(generic);
        List<SpecParam> select = specParamMapper.select(p);
        return select;
    }

    
    /**
     * 新增参数
     * @param param 
     */
    public void saveParam(SpecParam param) {
        specParamMapper.insert(param);
    }
    /**
     * 修改参数信息
     * @param param 
     */
    public void updateParam(SpecParam param) {
        specParamMapper.updateByPrimaryKeySelective(param);
    }
    /**
     * 删除参数信息
     * @param param 
     */
    public void delParam(Long pid) {
        specParamMapper.deleteByPrimaryKey(pid);
    }
    
    /**
     * 根据id查询商品规格
     * @param id
     * @return
     */
    public Specification queryById(long id){
       return specificationMapper.selectByPrimaryKey(id);
    }
}
