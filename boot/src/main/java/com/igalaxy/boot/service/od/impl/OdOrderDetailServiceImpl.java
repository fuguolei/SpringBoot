package com.igalaxy.boot.service.od.impl;

import com.igalaxy.boot.domain.od.OdOrderDetail;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.od.OdOrderDetailMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.od.OdOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class OdOrderDetailServiceImpl extends BaseServiceImpl<OdOrderDetail> implements OdOrderDetailService {

    @Autowired
    OdOrderDetailMapper odOrderDetailMapper;

    @Override
    public BaseMapper<OdOrderDetail> getMapper() {
        return odOrderDetailMapper;
    }

    @Override
    public float querySumByOrderId(Long orderId) {
        return odOrderDetailMapper.querySumByOrderId(orderId);
    }
}
