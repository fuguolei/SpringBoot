package com.igalaxy.boot.service.sys;


import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysRolePermission;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {

    SysRolePermission queryByRoleIdPermissionId(long roleId, long permissionId);
    BaseResult deleteByRoleIdPermissionId(long roleId, long permissionId);
}
