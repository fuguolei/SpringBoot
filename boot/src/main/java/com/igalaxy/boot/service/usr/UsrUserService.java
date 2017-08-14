package com.igalaxy.boot.service.usr;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface UsrUserService extends BaseService<UsrUser> {

    UsrUser getUserByAccount(String account);

    BaseResult resetMyPassword(String oldPassword, String newPassword);

    BaseResult<UsrUser> setPassword(UsrUser usrUser);

    UsrUser queryByOpenId(String openId);

}
