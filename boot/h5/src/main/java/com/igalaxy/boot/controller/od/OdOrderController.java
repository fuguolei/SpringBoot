package com.igalaxy.boot.controller.od;

import com.igalaxy.boot.controller.WeChatController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.PageData;
import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.domain.od.OdOrderSKU;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;
import com.igalaxy.boot.service.od.OdOrderService;
import com.igalaxy.boot.service.pay.PayWeChatService;
import com.igalaxy.boot.util.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/8/5.
 */
@Controller
@RequestMapping("/od/order")
public class OdOrderController extends WeChatController {

    @Autowired
    OdOrderService odOrderService;

    @Autowired
    PayWeChatService payWeChatService;

    @RequestMapping("/list.html")
    public String list(Integer page, HttpServletRequest request) {
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("pageSize", 10);
        pageParams.put("pageNo", page != null ? page : 1);
        PageData pageData = odOrderService.queryMyOdOrderDetailPageList(pageParams);
        request.setAttribute("list", pageData);
        return "od/order/list";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public BaseResult listData(Integer status, HttpServletResponse response) {
        Map<String, Object> pageParams = getPageParams();
        pageParams.put("status", status);
        PageData page = odOrderService.queryMyOdOrderDetailPageList(pageParams);
        return BaseResult.ok(page);
    }

    @RequestMapping("/{number}.html")
    public ModelAndView number(@PathVariable String number, HttpServletRequest request) {
        if (number == null)
            return new ModelAndView("od/order/no_found");
        OdOrder odOrder = odOrderService.queryOdOrderDetailByNumber(number);
        if (odOrder == null)
            return new ModelAndView("od/order/no_found");

        Map<String, Object> wchatParams = getWchatParams(request);
        wchatParams.put("order", odOrder);
        return new ModelAndView("od/order/detail", wchatParams);
    }

    @RequestMapping("/submit.json")
    @ResponseBody
    public BaseResult submit(@RequestParam(value = "sku[]") String[] sku, HttpServletResponse response) {
        if (sku == null)
            return BaseResult.badRequest(-1, "商品为空");
        List<OdOrderSKU> details = new ArrayList<>();
        OdOrderSKU detail;
        for (int i = 0; i < sku.length; i++) {
            String[] arr = sku[i].split("/");
            detail = new OdOrderSKU();
            detail.setSkuId(Long.parseLong(arr[0]));
            detail.setCount(Integer.parseInt(arr[1]));
            details.add(detail);
        }
        BaseResult result = odOrderService.createOrder(details);
        return result;
    }

    @RequestMapping("/submit-pay.json")
    @ResponseBody
    public BaseResult submitPay(@RequestParam(value = "sku[]") String[] sku, String payway, HttpServletRequest request, HttpServletResponse response) {
        if (sku == null)
            return BaseResult.badRequest(-1, "商品为空");
        if (payway != null && OdPayWayEnum.WeChat.getValue() != Integer.parseInt(payway))
            return BaseResult.badRequest(-2, "仅支持微信支付");

        List<OdOrderSKU> details = new ArrayList<>();
        OdOrderSKU detail;
        for (int i = 0; i < sku.length; i++) {
            String[] arr = sku[i].split("/");
            detail = new OdOrderSKU();
            detail.setSkuId(Long.parseLong(arr[0]));
            detail.setCount(Integer.parseInt(arr[1]));
            details.add(detail);
        }
        BaseResult<OdOrder> result = odOrderService.createOrder(details);
        if (!result.isSuccess())
            return result;
        BaseResult o = payWeChatService.unifiedOrder(result.getData().getId(), Ip.getIp(request));
        return o;
    }
}
