package com.igalaxy.boot.controller.web.gd;

import com.igalaxy.boot.controller.web.WeChatController;
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
@RequestMapping("/gd/sku")
public class GdSKUController extends WeChatController {

    @Autowired
    GdSKUService gdSKUService;


    @RequestMapping("/{number}.html")
    public String index(HttpServletRequest request, @PathVariable String number, HttpServletResponse response) {
        GdSKU gdSKU = gdSKUService.queryByNumber(number);
        request.setAttribute("sku", gdSKU);
        return "wechat/gd/sku";
    }
}
