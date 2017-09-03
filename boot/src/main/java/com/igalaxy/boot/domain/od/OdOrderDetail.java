package com.igalaxy.boot.domain.od;

import java.util.List;

/**
 * Created by fuguolei
 */
public class OdOrderDetail extends OdOrder {

    private List<OdOrderSKU> skus;

    public List<OdOrderSKU> getSkus() {
        return skus;
    }

    public void setSkus(List<OdOrderSKU> skus) {
        this.skus = skus;
    }
}
