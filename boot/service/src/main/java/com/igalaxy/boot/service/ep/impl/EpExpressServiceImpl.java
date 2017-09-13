package com.igalaxy.boot.service.ep.impl;

import com.igalaxy.boot.domain.ep.EpExpress;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.ep.EpExpressMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.ep.EpExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class EpExpressServiceImpl extends BaseServiceImpl<EpExpress> implements EpExpressService {

    @Autowired
    EpExpressMapper epExpressMapper;

    @Override
    public BaseMapper<EpExpress> getMapper() {
        return epExpressMapper;
    }

}
