package com.igalaxy.boot.service.sys;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysUser;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface SysUserService extends BaseService<SysUser> {

    public SysUser getUserByAccount(String account);

    public BaseResult resetMyPassword(String oldPassword, String newPassword);

    public BaseResult<SysUser> setPassword(SysUser sysUser);


}
