package com.igalaxy.boot.controller.web.od;

import com.igalaxy.boot.controller.web.WeChatController;
import com.igalaxy.boot.service.od.OdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by fuguolei on 2017/8/5.
 */
@Controller
@RequestMapping("/od")
public class OdOrderController extends WeChatController {
    @Autowired
    OdOrderService odOrderService;

    @RequestMapping("/order.html")
    public ModelAndView order(Long orderId, HttpServletRequest request) {
        Map<String, Object> wchatParams = getWchatParams(request);
        wchatParams.put("orderId", orderId);
        return new ModelAndView("od/order", wchatParams);
    }
}
