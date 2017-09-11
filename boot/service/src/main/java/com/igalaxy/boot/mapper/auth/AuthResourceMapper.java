package com.igalaxy.boot.mapper.auth;


import com.igalaxy.boot.domain.auth.AuthResource;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fuguolei
 */
@Mapper
public interface AuthResourceMapper extends BaseMapper<AuthResource> {
    List<AuthResource> getAll();

    List<AuthResource> selectAllResourceByUserId(long userId);

    List<AuthResource> selectAllResourceByRoleId(long roleId);
}
