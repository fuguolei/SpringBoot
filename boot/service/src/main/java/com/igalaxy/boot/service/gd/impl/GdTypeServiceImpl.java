package com.igalaxy.boot.service.gd.impl;

import com.igalaxy.boot.domain.gd.GdType;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.gd.GdTypeMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.gd.GdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class GdTypeServiceImpl extends BaseServiceImpl<GdType> implements GdTypeService {

    @Autowired
    GdTypeMapper gdTypeMapper;

    @Override
    public BaseMapper<GdType> getMapper() {
        return gdTypeMapper;
    }

}
