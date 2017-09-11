package com.igalaxy.boot.controller.gd;

import com.igalaxy.boot.controller.WeChatController;
import com.igalaxy.boot.domain.gd.GdSKU;
import com.igalaxy.boot.service.gd.GdSKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuguolei on 2017/8/5.
 */
@Controller
@RequestMapping("/gd/product")
public class GdProductController extends WeChatController {

    @Autowired
    GdSKUService gdSKUService;


    @RequestMapping("/{productId}.html")
    public String index(HttpServletRequest request, @PathVariable String productId, HttpServletResponse response) {
        Long id = null;
        try {
            id = Long.parseLong(productId);
        } catch (Exception e) {
            return null;
        }
        GdSKU gdSKU = gdSKUService.queryFirstByProductId(id);
        if (gdSKU == null)
            return "web/gd/no_product";
        String location = "/gd/sku/" + gdSKU.getNumber() + ".html";
        response.addHeader("location", location);
        response.setStatus(302);
        return null;
    }
}
