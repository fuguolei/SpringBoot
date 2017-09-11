package com.igalaxy.boot.service.auth.impl;


import com.igalaxy.boot.domain.auth.AuthPermission;
import com.igalaxy.boot.domain.auth.AuthResource;
import com.igalaxy.boot.domain.auth.AuthRolePermission;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.auth.AuthResourceMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.auth.AuthResourceService;
import com.igalaxy.boot.service.auth.AuthRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuguolei
 */
@Service
public class AuthResourceServiceImpl extends BaseServiceImpl<AuthResource> implements AuthResourceService {

    @Autowired
    AuthResourceMapper authResourceMapper;

    @Autowired
    AuthRolePermissionService authRolePermissionService;

    @Override
    public List<AuthResource> getAll() {
        return authResourceMapper.getAll();
    }

    @Override
    public List<AuthResource> selectAllResourceByUserId(long userId) {
        return authResourceMapper.selectAllResourceByUserId(userId);
    }

    @Override
    public List<AuthResource> selectAllResourceByRoleId(long roleId) {
        List<AuthResource> list = authResourceMapper.selectAllResourceByRoleId(roleId);
        if (list != null)
            for (AuthResource resource : list) {
                List<AuthPermission> permission = resource.getPermissions();
                if (permission != null)
                    for (AuthPermission p : permission) {
                        AuthRolePermission authRolePermission = authRolePermissionService.queryByRoleIdPermissionId(roleId, p.getId());
                        p.setHasPermission(authRolePermission != null ? WhetherEnum.Yes : WhetherEnum.No);
                    }
            }
        return list;
    }

    @Override
    public BaseMapper<AuthResource> getMapper() {
        return authResourceMapper;
    }
}
