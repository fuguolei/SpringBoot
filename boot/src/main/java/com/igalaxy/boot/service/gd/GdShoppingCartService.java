package com.igalaxy.boot.service.gd;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdShoppingCart;
import com.igalaxy.boot.domain.gd.GdShoppingCartSKU;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;

public interface GdShoppingCartService extends BaseService<GdShoppingCart> {

    BaseResult add(long skuId, int count);

    BaseResult minus(long id, int count);

    BaseResult delete(long id);

    BaseResult clean();

    List<GdShoppingCartSKU> queryList();

    List<GdShoppingCartSKU> queryList(List<Long> skuIds);
}
