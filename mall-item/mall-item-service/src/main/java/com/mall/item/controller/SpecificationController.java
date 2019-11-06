 package com.mall.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.item.pojo.SpecGroup;
import com.mall.item.pojo.SpecParam;
import com.mall.item.pojo.Specification;
import com.mall.item.service.SpecificationService;

/**
 * 商品规格controller
 * @author 张治峰
 * @date 2019年10月18日
 */
@RestController
@RequestMapping("spec")
 public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    
    
    
    //分组信息处理 begin
    /**
     * 添加分组信息
     * @param cid
     * @return
     */
    @PostMapping("group")
    public ResponseEntity<Void> saveGroup(@RequestBody SpecGroup group) {
       specificationService.saveGroup(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    /**
     * 修改分组信息
     * @param cid
     * @return
     */
    @PutMapping("group")
    public ResponseEntity<Void> updateGroup(@RequestBody SpecGroup group) {
        specificationService.updateGroup(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    /**
     * 删除分组信息
     * @param cid
     * @return
     */
    @DeleteMapping("group/{gid}")
    public ResponseEntity<Void> delGroup(@PathVariable("gid") Long gid) {
        specificationService.delGroup(gid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    
    /**
     * 根据规格查询分组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> specGroups = specificationService.queryGroupByCid(cid);
        if (specGroups == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(specGroups);
    }
    
    
    
    
    /**
     * 通过条件查询规格参数
     * @param gid
     * @param cid
     * @param searching
     * @param generic
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamByList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic) {

        return ResponseEntity.ok(specificationService.queryParamByList(gid, cid, searching, generic));
    }
    
    
    
    
    
    /**
     * 新增规格参数
     * @return
     */
    @PostMapping("param")
    public ResponseEntity<Void> saveParam(@RequestBody SpecParam param){
        specificationService.saveParam(param);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    /**
     * 修改规格参数
     * @param param
     * @return
     */
    @PutMapping("param")
    public ResponseEntity<Void> updateParam(@RequestBody SpecParam param){
        specificationService.updateParam(param);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * 修改规格参数
     * @param param
     * @return
     */
    @DeleteMapping("param/{pid}")
    public ResponseEntity<Void> delParam(@PathVariable("pid") Long pid){
        specificationService.delParam(pid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @GetMapping("{id}")
    public ResponseEntity<String> querySpecificationByCategoryId(@PathVariable("id") Long id){
        Specification spec = this.specificationService.queryById(id);
        if (spec == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(spec.getSpecifications());
    }
    
}
