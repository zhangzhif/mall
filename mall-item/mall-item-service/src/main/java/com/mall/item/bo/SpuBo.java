 package com.mall.item.bo;

import java.util.List;

import javax.persistence.Transient;

import com.mall.item.pojo.Sku;
import com.mall.item.pojo.Spu;
import com.mall.item.pojo.SpuDetail;

public class SpuBo extends Spu {

     @Transient
     String cname;// 商品分类名称
     @Transient
     String bname;// 品牌名称
     @Transient
     SpuDetail spuDetail;// 商品详情
     @Transient
     List<Sku> skus;// sku列表
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getBname() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }
    public SpuDetail getSpuDetail() {
        return spuDetail;
    }
    public void setSpuDetail(SpuDetail spuDetail) {
        this.spuDetail = spuDetail;
    }
    public List<Sku> getSkus() {
        return skus;
    }
    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }
     
     
     
 }
