package com.igalaxy.boot.service.gd.impl;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdSKU;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.gd.GdSKUMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.gd.GdSKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class GdSKUServiceImpl extends BaseServiceImpl<GdSKU> implements GdSKUService {

    @Autowired
    GdSKUMapper gdSKUMapper;

    @Override
    public BaseResult<GdSKU> save(GdSKU gdSKU) {
        if (queryByNumber(gdSKU.getNumber()) != null) {
            return BaseResult.badRequest("此编号已被使用");
        }
        return super.save(gdSKU);
    }

    @Override
    public BaseMapper<GdSKU> getMapper() {
        return gdSKUMapper;
    }

    @Override
    public GdSKU queryFirstByProductId(long id) {
        return gdSKUMapper.queryFirstByProductId(id);
    }

    @Override
    public GdSKU queryByNumber(String number) {
        return gdSKUMapper.queryByNumber(number);
    }
}
