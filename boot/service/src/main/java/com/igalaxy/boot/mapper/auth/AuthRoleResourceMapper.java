package com.igalaxy.boot.mapper.auth;

import com.igalaxy.boot.domain.auth.AuthRoleResource;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by fuguolei
 */
@Mapper
public interface AuthRoleResourceMapper extends BaseMapper<AuthRoleResource> {
    void deleteByRoleIdResourceId(Map<String, Object> params);
}
