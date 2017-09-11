package com.igalaxy.boot.service.auth.impl;


import com.igalaxy.boot.domain.auth.AuthRoleResource;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.auth.AuthRoleResourceMapper;
import com.igalaxy.boot.service.auth.AuthRoleResourceService;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Service
public class AuthRoleResourceServiceImpl extends BaseServiceImpl<AuthRoleResource> implements AuthRoleResourceService {

    @Autowired
    AuthRoleResourceMapper authRoleResourceMapper;


    @Override
    public BaseResult deleteByRoleIdResourceId(long roleId, long resourceId) {
        Map<String, Object> params = getParamsMap();
        params.put("roleId", roleId);
        params.put("resourceId", resourceId);
        authRoleResourceMapper.deleteByRoleIdResourceId(params);
        return BaseResult.ok();
    }

    @Override
    public BaseMapper<AuthRoleResource> getMapper() {
        return authRoleResourceMapper;
    }
}
