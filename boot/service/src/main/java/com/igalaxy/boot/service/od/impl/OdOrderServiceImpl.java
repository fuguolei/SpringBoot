package com.igalaxy.boot.service.od.impl;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.PageData;
import com.igalaxy.boot.domain.gd.GdSKU;
import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.domain.od.OdOrderSKU;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.od.OdOrderMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.gd.GdSKUService;
import com.igalaxy.boot.service.od.OdOrderSKUService;
import com.igalaxy.boot.service.od.OdOrderService;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum.Unpaid;

/**
 * Created by fuguolei
 */
@Service
public class OdOrderServiceImpl extends BaseServiceImpl<OdOrder> implements OdOrderService {

    @Autowired
    OdOrderMapper odOrderMapper;

    @Autowired
    OdOrderSKUService odOrderSKUService;

    @Autowired
    GdSKUService gdSKUService;

    @Autowired
    UsrUserService usrUserService;

    @Override
    public BaseMapper<OdOrder> getMapper() {
        return odOrderMapper;
    }

    @Override
    public PageData queryPage(Map<String, Object> params) {
        PageData<OdOrder> pageData = super.queryPage(params);
        List<OdOrder> list = pageData.getRows();
        if (list != null) {
            UsrUser usrUser;
            for (OdOrder o : list) {
                usrUser = usrUserService.queryById(o.getUserId());
                if (usrUser != null)
                    o.setUserName(usrUser.getName());
            }
        }
        return pageData;
    }

    @Override
    public BaseResult<OdOrder> createOrder(List<OdOrderSKU> details) {
        BaseResult<OdOrder> result;
        if (details == null) {
            result = BaseResult.badRequest(-1, "商品为空");
            return result;
        }
        GdSKU gdSKU;
        double sum = 0;
        String description = "";
        for (OdOrderSKU detail : details) {//修改为迭代器删除空sku
            gdSKU = gdSKUService.queryById(detail.getSkuId());
            if (gdSKU == null)
                continue;
            detail.setPrice(gdSKU.getPrice());
            sum += detail.getSum();
            if (description.length() > 0)
                description += ",";
            description += gdSKU.getName();
        }
        OdOrder odOrder = new OdOrder();
        odOrder.setNumber(String.valueOf(System.currentTimeMillis()));
        odOrder.setUserId(getUserId());
        odOrder.setName(description);
        odOrder.setDescription(description);
        odOrder.setSum(sum);
        odOrder.setStatus(Unpaid);
        result = save(odOrder);
        if (!result.isSuccess())
            return result;
        Long orderId = result.getData().getId();
        Long userId = getUserId();
        for (OdOrderSKU detail : details) {
            detail.setOrderId(orderId);
            detail.setCreateUser(userId);
            detail.setUpdateUser(userId);
        }
        BaseResult detailResult = odOrderSKUService.batchSave(details);
        if (!detailResult.isSuccess())
            return detailResult;
        return result;
    }

    @Override
    public OdOrder queryOdOrderDetailByNumber(String number) {
        OdOrder detail = queryByNumber(number);
        if (detail == null)
            return null;
        detail.setSkus(odOrderSKUService.queryListByOrderId(detail.getId()));
        return detail;
    }

    @Override
    public OdOrder queryByNumber(String number) {
        return odOrderMapper.queryByNumber(number);
    }

    @Override
    public BaseResult updateOdOrderByNumber(OdOrderStatusEnum status, OdPayWayEnum payWay, String number) {
        OdOrder odOrder = new OdOrder();
        odOrder.setNumber(number);
        odOrder.setPayWay(payWay);
        odOrder.setStatus(status);
        odOrder.setUpdateUser(getUserId());
        odOrderMapper.updateOdOrderByNumber(odOrder);
        return BaseResult.ok();
    }

    @Override
    public PageData queryMyOdOrderDetailPageList(Map<String, Object> params) {
        params.put("userId", getUserId());
        PageData pageData = queryPage(params);
        List<OdOrder> list = (List<OdOrder>) pageData.getRows();
        if (list != null)
            for (OdOrder detail : list)
                detail.setSkus(odOrderSKUService.queryListByOrderId(detail.getId()));
        return pageData;
    }
}
