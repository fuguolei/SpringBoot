package com.igalaxy.boot.service.usr;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrWechat;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface UsrWechatService extends BaseService<UsrWechat> {

    BaseResult oauth(String code);
}
