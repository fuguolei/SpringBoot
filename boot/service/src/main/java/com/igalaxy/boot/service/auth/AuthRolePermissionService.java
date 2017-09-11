package com.igalaxy.boot.service.auth;


import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.BaseResult1;
import com.igalaxy.boot.domain.auth.AuthRolePermission;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface AuthRolePermissionService extends BaseService<AuthRolePermission> {

    AuthRolePermission queryByRoleIdPermissionId(long roleId, long permissionId);
    BaseResult deleteByRoleIdPermissionId(long roleId, long permissionId);
}
