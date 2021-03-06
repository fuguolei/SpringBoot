package com.igalaxy.boot.service.od.impl;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.PageData;
import com.igalaxy.boot.domain.gd.GdSKU;
import com.igalaxy.boot.domain.od.OdOrderSKU;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.od.OdOrderSKUMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.gd.GdSKUService;
import com.igalaxy.boot.service.od.OdOrderSKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class OdOrderSKUServiceImpl extends BaseServiceImpl<OdOrderSKU> implements OdOrderSKUService {

    @Autowired
    OdOrderSKUMapper odOrderSKUMapper;

    @Autowired
    GdSKUService gdSKUService;

    @Override
    public BaseMapper<OdOrderSKU> getMapper() {
        return odOrderSKUMapper;
    }

    @Override
    public BaseResult batchSave(List<OdOrderSKU> list) {
        odOrderSKUMapper.batchSave(list);
        return BaseResult.ok("添加成功");
    }

    @Override
    public float querySumByOrderId(Long orderId) {
        return odOrderSKUMapper.querySumByOrderId(orderId);
    }

    @Override
    public List<OdOrderSKU> queryListByOrderId(Long orderId) {
        List<OdOrderSKU> list = odOrderSKUMapper.queryListByOrderId(orderId);
        if (list != null)
            for (OdOrderSKU odOrderSKU : list) {
                GdSKU sku = gdSKUService.queryById(odOrderSKU.getSkuId());
                if (sku != null) {
                    odOrderSKU.setName(sku.getName());
                    odOrderSKU.setPicture(sku.getPicture());
                }
            }
        return list;
    }

    @Override
    public PageData queryPage(Map<String, Object> params) {
        PageData pageData = super.queryPage(params);
        List<OdOrderSKU> list = pageData.getRows();
        if (list != null) {
            GdSKU gdSKU;
            for (OdOrderSKU o : list) {
                gdSKU = gdSKUService.queryById(o.getSkuId());
                if (gdSKU != null) {
                    o.setName(gdSKU.getName());
                    o.setPicture(gdSKU.getPicture());
                    o.setNumber(gdSKU.getNumber());
                }

            }
        }
        return pageData;
    }
}
