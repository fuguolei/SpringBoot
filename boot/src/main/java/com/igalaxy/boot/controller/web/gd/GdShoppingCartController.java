package com.igalaxy.boot.controller.web.gd;

import com.igalaxy.boot.controller.web.WeChatController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdShoppingCartSKU;
import com.igalaxy.boot.service.gd.GdShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuguolei on 2017/8/5.
 */
@Controller
@RequestMapping("/gd/shoppingcart")
public class GdShoppingCartController extends WeChatController {

    @Autowired
    GdShoppingCartService gdShoppingCartService;


    @RequestMapping("add.json")
    public String add(Long skuId, Integer count, HttpServletResponse response) {
        if (skuId == null)
            return writeErrorResult(response, -1, "参数skuId缺失");
        if (count == null)
            return writeErrorResult(response, -2, "参数count缺失");
        BaseResult result = gdShoppingCartService.add(skuId, count);
        return writeResult(response, result);
    }

    @RequestMapping("minus.json")
    public String minus(Long id, Integer count, HttpServletResponse response) {
        if (id == null)
            return writeErrorResult(response, -1, "参数id缺失");
        if (count == null)
            return writeErrorResult(response, -2, "参数count缺失");
        BaseResult result = gdShoppingCartService.minus(id, count);
        return writeResult(response, result);
    }

    @RequestMapping("delete.json")
    public Object delete(Long id, HttpServletResponse response) {
        if (id == null)
            return writeErrorResult(response, -1, "参数id缺失");
        BaseResult result = gdShoppingCartService.delete(id);
        return writeResult(response, result);
    }

    @RequestMapping("clean.json")
    public Object clean(HttpServletResponse response) {
        BaseResult result = gdShoppingCartService.clean();
        return writeResult(response, result);
    }

    @RequestMapping("index.html")
    public String index(HttpServletRequest request) {
        List<GdShoppingCartSKU> list = gdShoppingCartService.queryList();
        request.setAttribute("list", list);
        return "web/gd/shopping_cart";
    }

    @RequestMapping("confirm.html")
    public String confirm(String skuIds, HttpServletRequest request) {
        if (skuIds == null) {
            request.setAttribute("msg", "skuIds不能为空");
            return "error/5xx";
        }
        String[] strIds = skuIds.split(",");
        List<Long> skuIdList = new ArrayList<>();
        for (int i = 0; i < strIds.length; i++)
            skuIdList.add(Long.parseLong(strIds[i]));
        List<GdShoppingCartSKU> list = gdShoppingCartService.queryList(skuIdList);
        request.setAttribute("list", list);
        return "web/gd/order_confirm";
    }
}
