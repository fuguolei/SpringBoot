package com.igalaxy.boot.service.gd;

import com.igalaxy.boot.domain.gd.GdSKU;
import com.igalaxy.boot.service.base.BaseService;

public interface GdSKUService extends BaseService<GdSKU> {
    GdSKU queryFirstByProductId(long id);
    GdSKU queryByNumber(String number);
}
