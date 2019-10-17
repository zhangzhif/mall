package com.mall.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品规格参数
 * @author 张治峰
 * @date 2019年10月17日
 */
@Table(name = "tb_specification")
public class Specification {

    @Id
    private Long categoryId;
    //JSON格式
    private String specifications;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
