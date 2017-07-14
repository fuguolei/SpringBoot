package com.igalaxy.boot.service.sys;


import com.igalaxy.boot.domain.sys.SysUserRole;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;

/**
 * Created by fuguolei
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {
    public List<SysUserRole> selectNoUserInRole(long roleId);
}
