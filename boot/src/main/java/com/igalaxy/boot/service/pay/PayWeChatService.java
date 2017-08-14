package com.igalaxy.boot.service.pay;

import com.igalaxy.boot.domain.dto.BaseResult;

/**
 * Created by fuguolei on 2016/9/28.
 */
public interface PayWeChatService {
    BaseResult unifiedOrder(Long orderId, String ip);

    BaseResult orderNotify(String requestBody);
}