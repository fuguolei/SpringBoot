package com.igalaxy.boot.service.od;

import com.igalaxy.boot.domain.od.OdOrderDetail;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface OdOrderDetailService extends BaseService<OdOrderDetail> {

    float querySumByOrderId(Long orderId);
}