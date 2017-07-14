package com.igalaxy.boot.service.sys.impl;


import com.igalaxy.boot.domain.sys.SysUserRole;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.sys.SysUserRoleMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.sys.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuguolei
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> selectNoUserInRole(long roleId) {
        return sysUserRoleMapper.selectNoUserInRole(roleId);
    }

    @Override
    public BaseMapper<SysUserRole> getMapper() {
        return sysUserRoleMapper;
    }
}
