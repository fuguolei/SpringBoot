package com.igalaxy.boot.service.sys.impl;


import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysRoleResource;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.sys.SysRoleResourceMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.sys.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResource> implements SysRoleResourceService {

    @Autowired
    SysRoleResourceMapper sysRoleResourceMapper;


    @Override
    public BaseResult deleteByRoleIdResourceId(long roleId, long resourceId) {
        Map<String, Object> params = getParamsMap();
        params.put("roleId", roleId);
        params.put("resourceId", resourceId);
        sysRoleResourceMapper.deleteByRoleIdResourceId(params);
        return new BaseResult(true, "成功");
    }

    @Override
    public BaseMapper<SysRoleResource> getMapper() {
        return sysRoleResourceMapper;
    }
}
