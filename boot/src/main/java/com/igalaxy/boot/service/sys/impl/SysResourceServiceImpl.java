package com.igalaxy.boot.service.sys.impl;


import com.igalaxy.boot.domain.sys.SysPermission;
import com.igalaxy.boot.domain.sys.SysResource;
import com.igalaxy.boot.domain.sys.SysRolePermission;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.sys.SysResourceMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.sys.SysResourceService;
import com.igalaxy.boot.service.sys.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuguolei
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    SysResourceMapper sysResourceMapper;

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Override
    public List<SysResource> getAll() {
        return sysResourceMapper.getAll();
    }

    @Override
    public List<SysResource> selectAllResourceByUserId(long userId) {
        return sysResourceMapper.selectAllResourceByUserId(userId);
    }

    @Override
    public List<SysResource> selectAllResourceByRoleId(long roleId) {
        List<SysResource> list = sysResourceMapper.selectAllResourceByRoleId(roleId);
        if (list != null)
            for (SysResource resource : list) {
                List<SysPermission> permission = resource.getPermissions();
                if (permission != null)
                    for (SysPermission p : permission) {
                        SysRolePermission sysRolePermission = sysRolePermissionService.queryByRoleIdPermissionId(roleId, p.getId());
                        p.setHasPermission(sysRolePermission != null ? WhetherEnum.Yes : WhetherEnum.No);
                    }
            }
        return list;
    }

    @Override
    public BaseMapper<SysResource> getMapper() {
        return sysResourceMapper;
    }
}
