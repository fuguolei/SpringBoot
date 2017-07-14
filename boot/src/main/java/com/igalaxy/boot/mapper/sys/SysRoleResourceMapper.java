package com.igalaxy.boot.mapper.sys;

import com.igalaxy.boot.domain.sys.SysRoleResource;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Mapper
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {
    void deleteByRoleIdResourceId(Map<String, Object> params);
}
