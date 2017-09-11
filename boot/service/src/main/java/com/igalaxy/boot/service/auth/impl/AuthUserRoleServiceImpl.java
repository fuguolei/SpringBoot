package com.igalaxy.boot.service.auth.impl;


import com.igalaxy.boot.domain.auth.AuthUserRole;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.auth.AuthUserRoleMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.auth.AuthUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuguolei
 */
@Service
public class AuthUserRoleServiceImpl extends BaseServiceImpl<AuthUserRole> implements AuthUserRoleService {

    @Autowired
    AuthUserRoleMapper authUserRoleMapper;

    @Override
    public List<AuthUserRole> selectNoUserInRole(long roleId) {
        return authUserRoleMapper.selectNoUserInRole(roleId);
    }

    @Override
    public BaseMapper<AuthUserRole> getMapper() {
        return authUserRoleMapper;
    }
}
