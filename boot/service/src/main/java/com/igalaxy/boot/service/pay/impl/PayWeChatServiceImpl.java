package com.igalaxy.boot.service.pay.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.igalaxy.boot.conf.MyWXPayConfig;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.od.OdOrder;
import com.igalaxy.boot.domain.usr.UsrWechat;
import com.igalaxy.boot.enums.OdProperty.OdOrderStatusEnum;
import com.igalaxy.boot.enums.OdProperty.OdPayWayEnum;
import com.igalaxy.boot.service.od.OdOrderService;
import com.igalaxy.boot.service.pay.PayWeChatService;
import com.igalaxy.boot.service.usr.UsrWechatService;
import com.igalaxy.boot.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuguolei on 2016/9/28.
 */
@Service
public class PayWeChatServiceImpl implements PayWeChatService {

    @Value("${pay.wechat.notify_url}")
    String NOTIFY_URL;


    private Logger logger = LoggerFactory.getLogger(getClass());

    MyWXPayConfig config = new MyWXPayConfig();
    WXPay wxpay = new WXPay(config);

    @Autowired
    OdOrderService odOrderService;

    @Autowired
    UsrWechatService usrWechatService;

    @Override
    public BaseResult unifiedOrder(Long orderId, String ip) {
        OdOrder odOrder = odOrderService.queryById(orderId);
        if (odOrder == null)
            return BaseResult.badRequest(-1, "无此订单");
        if (odOrder.getStatus() == OdOrderStatusEnum.Cancel || odOrder.getStatus() == OdOrderStatusEnum.TimeOut)
            return BaseResult.badRequest(-2, "订单已取消或超时");
        if (odOrder.getStatus() == OdOrderStatusEnum.Paid)
            return BaseResult.badRequest(-3, "订单已支付");

        UsrWechat usrWechat = usrWechatService.queryByUserId(SessionUtils.getUserId());
        if (usrWechat == null)
            return BaseResult.badRequest(-4, "获取不到openid");

        Map<String, String> data = new HashMap<>();
        data.put("body", odOrder.getDescription());
        data.put("out_trade_no", odOrder.getNumber());
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", String.valueOf(Math.round(odOrder.getSum() * 100)));
        data.put("spbill_create_ip", ip);
        data.put("notify_url", NOTIFY_URL);
        data.put("trade_type", "JSAPI");
        data.put("openid", usrWechat.getOpenId());
        Map<String, String> resp;
        try {
            resp = wxpay.unifiedOrder(data);
            logger.debug("resp:{}", resp);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.badRequest(-5, "统一下单失败");
        }
        if (!"SUCCESS".equals(resp.get("return_code")))
            return BaseResult.badRequest(-6, resp.get("return_msg"));
        if (!"SUCCESS".equals(resp.get("result_code")))
            return BaseResult.badRequest(-7, resp.get("err_code_des"));

        Map<String, String> jsPay = new HashMap<>();
        jsPay.put("orderNumber", odOrder.getNumber());
        jsPay.put("appId", resp.get("appid"));
        jsPay.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        jsPay.put("nonceStr", WXPayUtil.generateNonceStr());
        jsPay.put("package", "prepay_id=" + resp.get("prepay_id"));
        jsPay.put("signType", "MD5");
        try {
            jsPay.put("paySign", WXPayUtil.generateSignature(jsPay, config.getKey()));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.badRequest(-8, "签名失败，请重试");
        }
        return BaseResult.ok(jsPay);

    }

    @Override
    public BaseResult orderNotify(String requestBody) {
        BaseResult result;
        logger.debug("requestBody------------{}", requestBody);
        try {
            Map<String, String> body = WXPayUtil.xmlToMap(requestBody);
            if (body.get("return_code").equals("SUCCESS") && WXPayUtil.isSignatureValid(body, config.getKey())) {
                odOrderService.updateOdOrderByNumber(OdOrderStatusEnum.Paid, OdPayWayEnum.WeChat, body.get("out_trade_no"));
                result = BaseResult.ok();
            } else {
                result = BaseResult.badRequest("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = BaseResult.badRequest("服务器异常");
        }
        return result;
    }
}
