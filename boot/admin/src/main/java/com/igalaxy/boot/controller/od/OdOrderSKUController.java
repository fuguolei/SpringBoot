package com.igalaxy.boot.controller.od;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdType;
import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.od.OdOrderSKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuguolei on 2017/2/3.
 */
@Controller
@RequestMapping("/od/ordersku")
public class OdOrderSKUController extends BaseController {

    @Autowired
    OdOrderSKUService odOrderSKUService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Long orderId, HttpServletRequest request) {
        request.setAttribute("orderId", orderId);
        return "od/order_sku";
    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(OdOrder t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult<GdType> result = service.save(t);
        return result;
    }

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult edit(OdOrder t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.update(t);
        return result;
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(OdOrder t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.delete(t);
        return result;
    }

    @Override
    public Object listData(Map<String, Object> params) throws Exception {
        HttpServletRequest request = getServletRequest();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("orderId", request.getParameter("orderId"));
        return super.listData(pageParams);
    }

    @Override
    protected BaseService getService() {
        return odOrderSKUService;
    }


}
