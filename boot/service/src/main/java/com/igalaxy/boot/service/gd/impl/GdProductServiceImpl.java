package com.igalaxy.boot.service.gd.impl;

import com.igalaxy.boot.domain.gd.GdProduct;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.gd.GdProductMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.gd.GdProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class GdProductServiceImpl extends BaseServiceImpl<GdProduct> implements GdProductService {

    @Autowired
    GdProductMapper gdProductMapper;

    @Override
    public BaseMapper<GdProduct> getMapper() {
        return gdProductMapper;
    }

}
