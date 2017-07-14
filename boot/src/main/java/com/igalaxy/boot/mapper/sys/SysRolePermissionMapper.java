package com.igalaxy.boot.mapper.sys;


import com.igalaxy.boot.domain.sys.SysRolePermission;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    SysRolePermission queryByRoleIdPermissionId(Map<String, Object> params);

    void deleteByRoleIdPermissionId(Map<String, Object> params);
}
