package com.igalaxy.boot.service.sys.impl;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysUser;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.sys.SysUserMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.sys.SysUserService;
import com.igalaxy.boot.util.CommonUtils;
import com.igalaxy.boot.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public BaseResult resetMyPassword(String oldPassword, String newPassword) {
        SysUser user = getUser();
        if (StringUtils.isNotEquals(oldPassword, user.getPassword())) {
            return new BaseResult(false, "原始密码错误");
        }
        Map<String, Object> params = getParamsMap();
        params.put("id", getUserId());
        params.put("password", newPassword);
        sysUserMapper.resetPassword(params);
        SysUser newUser = queryById(user.getId());
        CommonUtils.setCurrentUser(newUser);
        return new BaseResult(true, "密码设置成功");
    }

    @Override
    public BaseResult<SysUser> setPassword(SysUser sysUser) {
        sysUser.setUpdateUser(getUserId());
        sysUserMapper.setPassword(sysUser);
        return new BaseResult<SysUser>(true, "更新成功");
    }

    @Override
    public SysUser getUserByAccount(String account) {
        return sysUserMapper.getUserByAccount(account);
    }


    @Override
    public BaseMapper<SysUser> getMapper() {
        return sysUserMapper;
    }
}
