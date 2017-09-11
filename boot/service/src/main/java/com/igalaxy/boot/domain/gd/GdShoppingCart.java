package com.igalaxy.boot.domain.gd;

import com.igalaxy.boot.domain.BaseDomain;

/**
 * Created by fuguolei on 2017/9/1.
 */
public class GdShoppingCart extends BaseDomain {
    private Long userId;
    private Long skuId;
    private Integer count;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
