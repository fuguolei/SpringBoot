package com.igalaxy.boot.service.od;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.ListViewPage;
import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.domain.od.OdOrderDetail;
import com.igalaxy.boot.domain.od.OdOrderSKU;
import com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei
 */
public interface OdOrderService extends BaseService<OdOrder> {

    OdOrderDetail queryOdOrderDetailByNumber(String number);

    BaseResult<OdOrder> createOrder(List<OdOrderSKU> details);

    OdOrderDetail queryByNumber(String number);

    BaseResult updateOdOrderByNumber(OdOrderStatusEnum status, OdPayWayEnum payWay, String number);

    ListViewPage queryMyOdOrderDetailPageList(Map<String, Object> params);

}
