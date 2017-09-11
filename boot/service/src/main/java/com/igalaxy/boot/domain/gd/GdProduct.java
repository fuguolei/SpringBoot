package com.igalaxy.boot.domain.gd;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.GdProperty.GdSaleEnum;

/**
 * Created by fuguolei on 2017/8/31.
 */
public class GdProduct  extends BaseDomain {
    private Long type;
    private Long number;
    private String name;
    private GdSaleEnum sale;
    private String categoryTitle;
    private Integer sort;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GdSaleEnum getSale() {
        return sale;
    }

    public void setSale(GdSaleEnum sale) {
        this.sale = sale;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
