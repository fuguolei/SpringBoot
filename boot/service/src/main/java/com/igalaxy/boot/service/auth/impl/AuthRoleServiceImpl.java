package com.igalaxy.boot.service.auth.impl;


import com.igalaxy.boot.domain.auth.AuthRole;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.auth.AuthRoleMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.auth.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class AuthRoleServiceImpl extends BaseServiceImpl<AuthRole> implements AuthRoleService {

    @Autowired
    AuthRoleMapper authRoleMapper;


    @Override
    public BaseMapper<AuthRole> getMapper() {
        return authRoleMapper;
    }
}