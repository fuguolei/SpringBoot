package com.igalaxy.boot.controller.web.pay;

import com.igalaxy.boot.controller.admin.base.AdminController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.service.pay.PayWeChatService;
import com.igalaxy.boot.util.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuguolei on 2017/8/11.
 */
@Controller
@RequestMapping("/pay")
public class PayController extends AdminController {

    @Autowired
    PayWeChatService payWeChatService;

    @RequestMapping("/wechat.json")
    public String getWeChat(Long orderId, HttpServletRequest request, HttpServletResponse response) {
        BaseResult result = payWeChatService.unifiedOrder(orderId, Ip.getIp(request));
        return writeResult(response, result);
    }

    @RequestMapping("/wechat-notify")
    @ResponseBody
    public String wechatNotify(@RequestBody String requestBody) {
        BaseResult result = payWeChatService.orderNotify(requestBody);
        if (result.isSuccess())
            return "<xml>\n<return_code>SUCCESS</return_code>\n<return_msg>OK</return_msg>\n</xml>";
        else
            return "<xml>\n<return_code>FAIL</return_code>\n</xml>";
    }
}
