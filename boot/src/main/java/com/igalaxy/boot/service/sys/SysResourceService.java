package com.igalaxy.boot.service.sys;


import com.igalaxy.boot.domain.sys.SysResource;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;

/**
 * Created by fuguolei
 */
public interface SysResourceService extends BaseService<SysResource> {
    List<SysResource> getAll();

    List<SysResource> selectAllResourceByUserId(long userId);

    List<SysResource> selectAllResourceByRoleId(long roleId);
}
