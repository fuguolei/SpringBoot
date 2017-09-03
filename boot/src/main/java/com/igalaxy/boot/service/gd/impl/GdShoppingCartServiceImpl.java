package com.igalaxy.boot.service.gd.impl;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdShoppingCart;
import com.igalaxy.boot.domain.gd.GdShoppingCartSKU;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.gd.GdShoppingCartMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.gd.GdShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class GdShoppingCartServiceImpl extends BaseServiceImpl<GdShoppingCart> implements GdShoppingCartService {

    @Autowired
    GdShoppingCartMapper gdShoppingCartMapper;

    @Override
    public BaseMapper<GdShoppingCart> getMapper() {
        return gdShoppingCartMapper;
    }

    @Override
    public BaseResult add(long skuId, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserId());
        params.put("skuId", skuId);
        GdShoppingCart gdShoppingCart = gdShoppingCartMapper.queryByUserIdSKUId(params);
        if (gdShoppingCart == null) {
            gdShoppingCart = new GdShoppingCart();
            gdShoppingCart.setCount(count);
            gdShoppingCart.setSkuId(skuId);
            gdShoppingCart.setUserId(getUserId());
            return save(gdShoppingCart);
        } else {
            gdShoppingCart.setCount(gdShoppingCart.getCount() + count);
            return update(gdShoppingCart);
        }
    }

    @Override
    public BaseResult minus(long id, int count) {
        GdShoppingCart gdShoppingCart = gdShoppingCartMapper.queryById(id);
        if (gdShoppingCart == null)
            return new BaseResult(true, "此商品已删除");
        int c = gdShoppingCart.getCount() - count;
        if (c > 0) {
            gdShoppingCart.setCount(gdShoppingCart.getCount() + count);
            return update(gdShoppingCart);
        } else {
            return delete(gdShoppingCart);
        }
    }

    @Override
    public BaseResult delete(long id) {
        GdShoppingCart gdShoppingCart = gdShoppingCartMapper.queryById(id);
        if (gdShoppingCart == null)
            return new BaseResult(true, "此商品已删除");
        return delete(gdShoppingCart);
    }

    @Override
    public BaseResult clean() {
        gdShoppingCartMapper.deleteByUserId(getUserId());
        return new BaseResult(true, "清除成功");
    }

    @Override
    public List<GdShoppingCartSKU> queryList() {
        return gdShoppingCartMapper.queryListByUserId(getUserId());
    }

    @Override
    public List<GdShoppingCartSKU> queryList(List<Long> skuIds) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserId());
        params.put("skuIds", skuIds);
        return gdShoppingCartMapper.queryListByUserIdSKUIds(params);
    }
}
