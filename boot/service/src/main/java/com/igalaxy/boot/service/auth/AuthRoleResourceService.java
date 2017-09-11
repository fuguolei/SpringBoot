package com.igalaxy.boot.service.auth;


import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.BaseResult1;
import com.igalaxy.boot.domain.auth.AuthRoleResource;
import com.igalaxy.boot.service.base.BaseService;

/**
 * Created by fuguolei
 */
public interface AuthRoleResourceService extends BaseService<AuthRoleResource> {

    BaseResult deleteByRoleIdResourceId(long roleId, long resourceId);
}
