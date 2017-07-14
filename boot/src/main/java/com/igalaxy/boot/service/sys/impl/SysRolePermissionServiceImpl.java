package com.igalaxy.boot.service.sys.impl;


import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysRolePermission;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.sys.SysRolePermissionMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.sys.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements SysRolePermissionService {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public SysRolePermission queryByRoleIdPermissionId(long roleId, long permissionId) {
        Map<String, Object> params = getParamsMap();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);
        return sysRolePermissionMapper.queryByRoleIdPermissionId(params);
    }

    @Override
    public BaseResult deleteByRoleIdPermissionId(long roleId, long permissionId) {
        Map<String, Object> params = getParamsMap();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);
        sysRolePermissionMapper.deleteByRoleIdPermissionId(params);
        return new BaseResult(true, "成功");
    }

    @Override
    public BaseMapper<SysRolePermission> getMapper() {
        return sysRolePermissionMapper;
    }
}
