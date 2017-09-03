package com.igalaxy.boot.service.od;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.od.OdOrderSKU;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;

/**
 * Created by fuguolei
 */
public interface OdOrderSKUService extends BaseService<OdOrderSKU> {

    BaseResult batchSave(List<OdOrderSKU> list);

    float querySumByOrderId(Long orderId);

    List<OdOrderSKU> queryListByOrderId(Long orderId);
}