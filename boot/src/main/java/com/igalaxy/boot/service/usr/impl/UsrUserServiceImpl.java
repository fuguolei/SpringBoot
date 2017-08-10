package com.igalaxy.boot.service.usr.impl;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.usr.UsrUserMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.usr.UsrUserService;
import com.igalaxy.boot.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class UsrUserServiceImpl extends BaseServiceImpl<UsrUser> implements UsrUserService {

    @Autowired
    UsrUserMapper usrUserMapper;

    @Override
    public BaseResult resetMyPassword(String oldPassword, String newPassword) {
        UsrUser user = queryById(getUserId());
        if (StringUtils.isNotEquals(oldPassword, user.getPassword())) {
            return new BaseResult(false, "原始密码错误");
        }
        Map<String, Object> params = getParamsMap();
        params.put("id", getUserId());
        params.put("password", newPassword);
        usrUserMapper.resetPassword(params);
        return new BaseResult(true, "密码设置成功");
    }

    @Override
    public BaseResult<UsrUser> setPassword(UsrUser usrUser) {
        usrUser.setUpdateUser(getUserId());
        usrUserMapper.setPassword(usrUser);
        return new BaseResult<UsrUser>(true, "更新成功");
    }

    @Override
    public UsrUser getUserByAccount(String account) {
        return usrUserMapper.getUserByAccount(account);
    }


    @Override
    public BaseMapper<UsrUser> getMapper() {
        return usrUserMapper;
    }
}
