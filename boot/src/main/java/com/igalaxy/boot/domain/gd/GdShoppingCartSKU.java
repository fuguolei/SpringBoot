package com.igalaxy.boot.domain.gd;

/**
 * Created by fuguolei on 2017/9/1.
 */
public class GdShoppingCartSKU extends GdShoppingCart {
    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
