package com.igalaxy.boot.mapper.auth;


import com.igalaxy.boot.domain.auth.AuthRolePermission;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Mapper
public interface AuthRolePermissionMapper extends BaseMapper<AuthRolePermission> {
    AuthRolePermission queryByRoleIdPermissionId(Map<String, Object> params);

    void deleteByRoleIdPermissionId(Map<String, Object> params);
}
