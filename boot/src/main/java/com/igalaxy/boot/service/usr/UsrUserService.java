package com.igalaxy.boot.service.usr;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface UsrUserService extends BaseService<UsrUser> {

    public UsrUser getUserByAccount(String account);

    public BaseResult resetMyPassword(String oldPassword, String newPassword);

    public BaseResult<UsrUser> setPassword(UsrUser usrUser);


}
