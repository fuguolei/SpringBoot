package com.igalaxy.boot.service.auth;


import com.igalaxy.boot.domain.auth.AuthResource;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;

/**
 * Created by fuguolei
 */
public interface AuthResourceService extends BaseService<AuthResource> {
    List<AuthResource> getAll();

    List<AuthResource> selectAllResourceByUserId(long userId);

    List<AuthResource> selectAllResourceByRoleId(long roleId);
}
