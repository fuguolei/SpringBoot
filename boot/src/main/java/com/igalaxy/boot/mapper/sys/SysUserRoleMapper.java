package com.igalaxy.boot.mapper.sys;


import com.igalaxy.boot.domain.sys.SysUserRole;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fuguolei
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    public List<SysUserRole> selectNoUserInRole(long roleId);
}
