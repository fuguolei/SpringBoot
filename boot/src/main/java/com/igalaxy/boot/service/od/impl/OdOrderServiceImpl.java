package com.igalaxy.boot.service.od.impl;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.od.OdOrderMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.od.OdOrderDetailService;
import com.igalaxy.boot.service.od.OdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class OdOrderServiceImpl extends BaseServiceImpl<OdOrder> implements OdOrderService {

    @Autowired
    OdOrderMapper odOrderMapper;

    @Autowired
    OdOrderDetailService odOrderDetailService;

    @Override
    public BaseMapper<OdOrder> getMapper() {
        return odOrderMapper;
    }

    @Override
    public BaseResult orderPay(OdPayWayEnum payWay, Long orderId) {
        OdOrder odOrder = queryById(orderId);
        return null;
    }

    @Override
    public BaseResult updateOdOrderByNumber(OdOrderStatusEnum status, OdPayWayEnum payWay, String number) {
        OdOrder odOrder = new OdOrder();
        odOrder.setNumber(number);
        odOrder.setPayWay(payWay);
        odOrder.setStatus(status);
        odOrder.setUpdateUser(getUserId());
        odOrderMapper.updateOdOrderByNumber(odOrder);
        return null;
    }
}
