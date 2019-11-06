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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.common.pojo.PageResult;
import com.mall.item.pojo.Brand;
import com.mall.item.service.BrandService;
/**
 * 
 * 商标信息管理Controller
 * @author 张治峰
 * @date 2019年9月29日
 */
@RestController
@RequestMapping("brand")
 public class BrandController {
    @Autowired
    private BrandService brandService;
    
    
    
    /**
     * 新增商标信息
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
       this.brandService.saveBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    /**
     * 修改商标信息
     * @param brand
     * @param cids
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> putBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
       this.brandService.updateBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * 删除商品
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("id") Long id){
        
        this.brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    /**
     * 根据分类获取品牌信息
     * @return
     */
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid")Long cid){
        return ResponseEntity.ok(this.brandService.queryBrandByCategoryId(cid));
    }
    
    
    
    /**
     * 
     * 分页获取品牌信息
    * @param page
    * @param rows      
    * @param sortBy
    * @param desc
    * @param key
    * @return
    */
   @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page,rows,sortBy,desc, key);
      /*  if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        return ResponseEntity.ok(result);
    }
     
}
