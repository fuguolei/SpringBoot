package com.igalaxy.boot.service.auth.impl;


import com.igalaxy.boot.domain.auth.AuthPermission;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.auth.AuthPermissionMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.auth.AuthPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class AuthPermissionServiceImpl extends BaseServiceImpl<AuthPermission> implements AuthPermissionService {

    @Autowired
    AuthPermissionMapper authPermissionMapper;

    @Override
    public BaseMapper<AuthPermission> getMapper() {
        return authPermissionMapper;
    }
}
