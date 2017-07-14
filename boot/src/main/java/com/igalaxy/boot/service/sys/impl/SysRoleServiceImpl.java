package com.igalaxy.boot.service.sys.impl;


import com.igalaxy.boot.domain.sys.SysRole;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.sys.SysRoleMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;


    @Override
    public BaseMapper<SysRole> getMapper() {
        return sysRoleMapper;
    }
}