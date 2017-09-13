package com.igalaxy.boot.domain.od;

import com.igalaxy.boot.domain.BaseDomain;

/**
 * Created by fuguolei
 */
public class OdOrderSKU extends BaseDomain {

    private Long orderId;
    private Long skuId;
    private Double price;
    private Integer count;

    //
    private String number;
    private String name;
    private String picture;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getSum() {
        if (price == null || count == null)
            return 0;
        return price * count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
