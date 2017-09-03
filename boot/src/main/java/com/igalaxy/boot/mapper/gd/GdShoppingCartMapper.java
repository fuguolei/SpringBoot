package com.igalaxy.boot.mapper.gd;

import com.igalaxy.boot.domain.gd.GdShoppingCart;
import com.igalaxy.boot.domain.gd.GdShoppingCartSKU;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface GdShoppingCartMapper extends BaseMapper<GdShoppingCart> {

    GdShoppingCart queryByUserIdSKUId(Map<String, Object> params);

    List<GdShoppingCartSKU> queryListByUserId(long userId);

    List<GdShoppingCartSKU> queryListByUserIdSKUIds(Map<String, Object> params);

    void deleteByUserId(long userId);
}
