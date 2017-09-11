package com.igalaxy.boot.service.auth.impl;


import com.igalaxy.boot.domain.auth.AuthRolePermission;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.auth.AuthRolePermissionMapper;
import com.igalaxy.boot.service.auth.AuthRolePermissionService;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class AuthRolePermissionServiceImpl extends BaseServiceImpl<AuthRolePermission> implements AuthRolePermissionService {

    @Autowired
    AuthRolePermissionMapper authRolePermissionMapper;

    @Override
    public AuthRolePermission queryByRoleIdPermissionId(long roleId, long permissionId) {
        Map<String, Object> params = getParamsMap();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);
        return authRolePermissionMapper.queryByRoleIdPermissionId(params);
    }

    @Override
    public BaseResult deleteByRoleIdPermissionId(long roleId, long permissionId) {
        Map<String, Object> params = getParamsMap();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);
        authRolePermissionMapper.deleteByRoleIdPermissionId(params);
        return BaseResult.ok();
    }

    @Override
    public BaseMapper<AuthRolePermission> getMapper() {
        return authRolePermissionMapper;
    }
}
