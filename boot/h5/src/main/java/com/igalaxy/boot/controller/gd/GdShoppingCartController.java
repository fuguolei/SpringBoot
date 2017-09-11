package com.igalaxy.boot.controller.gd;

import com.igalaxy.boot.controller.WeChatController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdShoppingCartSKU;
import com.igalaxy.boot.domain.usr.UsrAddress;
import com.igalaxy.boot.service.gd.GdShoppingCartService;
import com.igalaxy.boot.service.usr.UsrAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/8/5.
 */
@Controller
@RequestMapping("/gd/shoppingcart")
public class GdShoppingCartController extends WeChatController {

    @Autowired
    GdShoppingCartService gdShoppingCartService;

    @Autowired
    UsrAddressService usrAddressService;

    @RequestMapping("add.json")
    @ResponseBody
    public BaseResult add(Long skuId, Integer count, HttpServletResponse response) {
        if (skuId == null)
            return BaseResult.badRequest(-1, "参数skuId缺失");
        if (count == null)
            return BaseResult.badRequest(-2, "参数count缺失");
        BaseResult result = gdShoppingCartService.add(skuId, count);
        return result;
    }

    @RequestMapping("minus.json")
    @ResponseBody
    public BaseResult minus(Long id, Integer count, HttpServletResponse response) {
        if (id == null)
            return BaseResult.badRequest(-1, "参数id缺失");
        if (count == null)
            return BaseResult.badRequest(-2, "参数count缺失");
        BaseResult result = gdShoppingCartService.minus(id, count);
        return result;
    }

    @RequestMapping("delete.json")
    @ResponseBody
    public BaseResult delete(Long id, HttpServletResponse response) {
        if (id == null)
            return BaseResult.badRequest(-1, "参数id缺失");
        BaseResult result = gdShoppingCartService.delete(id);
        return result;
    }

    @RequestMapping("clean.json")
    @ResponseBody
    public BaseResult clean(HttpServletResponse response) {
        BaseResult result = gdShoppingCartService.clean();
        return result;
    }

    @RequestMapping("index.html")
    public String index(HttpServletRequest request) {
        List<GdShoppingCartSKU> list = gdShoppingCartService.queryList();
        request.setAttribute("list", list);
        return "gd/shopping_cart";
    }

    @RequestMapping("confirm.html")
    public ModelAndView confirm(String skuIds, HttpServletRequest request) {
        if (skuIds == null) {
            request.setAttribute("msg", "skuIds不能为空");
            return new ModelAndView("error/5xx");
        }
        String[] strIds = skuIds.split(",");
        List<Long> skuIdList = new ArrayList<>();
        for (int i = 0; i < strIds.length; i++)
            skuIdList.add(Long.parseLong(strIds[i]));
        List<GdShoppingCartSKU> list = gdShoppingCartService.queryList(skuIdList);

        Map<String, Object> params = getWchatParams(request);
        params.put("list", list);
        UsrAddress address = usrAddressService.queryDefaultAddress();
        params.put("address", address);
        return new ModelAndView("gd/order_confirm", params);
    }
}
