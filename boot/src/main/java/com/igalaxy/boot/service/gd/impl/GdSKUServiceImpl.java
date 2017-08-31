package com.igalaxy.boot.service.gd.impl;

import com.google.gson.Gson;
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
    static Gson gson = new Gson();

    @Autowired
    GdSKUMapper gdSKUMapper;

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
        if (number == null || number.length() <= 0)
            return null;
        return gdSKUMapper.queryByNumber(number);
    }
}