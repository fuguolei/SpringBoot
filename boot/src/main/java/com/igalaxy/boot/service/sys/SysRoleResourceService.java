package com.igalaxy.boot.service.sys;


import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysRoleResource;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface SysRoleResourceService extends BaseService<SysRoleResource> {

    BaseResult deleteByRoleIdResourceId(long roleId, long resourceId);
}
