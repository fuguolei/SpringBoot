package com.igalaxy.boot.mapper.auth;


import com.igalaxy.boot.domain.auth.AuthUserRole;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fuguolei
 */
@Mapper
public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {
    public List<AuthUserRole> selectNoUserInRole(long roleId);
}
