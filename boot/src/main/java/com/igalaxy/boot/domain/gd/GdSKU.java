package com.igalaxy.boot.domain.gd;

import com.igalaxy.boot.domain.BaseDomain;

/**
 * Created by fuguolei on 2017/8/31.
 */
public class GdSKU extends BaseDomain {
    private Long productId;
    private String name;
    private String description;
    private String ad;
    private Double price;
    private Double originalPrice;
    private String picture;
    private Long inventory;
    private Integer sort;

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
