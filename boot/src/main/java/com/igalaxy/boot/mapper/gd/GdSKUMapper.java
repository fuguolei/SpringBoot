package com.igalaxy.boot.mapper.gd;

import com.igalaxy.boot.domain.gd.GdSKU;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fuguolei on 2017/7/8.
 */
@Mapper
public interface GdSKUMapper extends BaseMapper<GdSKU> {
    GdSKU queryFirstByProductId(long productId);

    GdSKU queryByNumber(String number);
}
