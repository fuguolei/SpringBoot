package com.igalaxy.boot.domain.gd;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.SysProperty.SysSaleEnum;

/**
 * Created by fuguolei on 2017/8/31.
 */
public class GdSKU extends BaseDomain {
    private Long productId;
    private String number;
    private String name;
    private String description;
    private String ad;
    private Double price;
    private Double originalPrice;
    private String picture;
    private Long inventory;
    private SysSaleEnum sale;
    private String category;
    private Integer sort;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public SysSaleEnum getSale() {
        return sale;
    }

    public void setSale(SysSaleEnum sale) {
        this.sale = sale;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
