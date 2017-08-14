package com.igalaxy.boot.domain.od;

import com.igalaxy.boot.domain.BaseDomain;

/**
 * Created by fuguolei
 */
public class OdOrderDetail extends BaseDomain {

    private Long orderId;
    private Long skuId;
    private Double price;
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
