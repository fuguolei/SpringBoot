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
}
