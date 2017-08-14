package com.igalaxy.boot.service.od;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface OdOrderService extends BaseService<OdOrder> {

    BaseResult orderPay(OdPayWayEnum payWay, Long orderId);

    BaseResult updateOdOrderByNumber(OdOrderStatusEnum status, OdPayWayEnum payWay, String number);
}
