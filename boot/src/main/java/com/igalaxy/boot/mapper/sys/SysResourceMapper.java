package com.igalaxy.boot.mapper.sys;


import com.igalaxy.boot.domain.sys.SysResource;
import com.igalaxy.boot.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by fuguolei
 */
@Mapper
public interface SysResourceMapper extends BaseMapper<SysResource> {
    List<SysResource> getAll();

    List<SysResource> selectAllResourceByUserId(long userId);

    List<SysResource> selectAllResourceByRoleId(long roleId);
}
