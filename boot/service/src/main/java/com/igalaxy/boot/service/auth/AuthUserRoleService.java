package com.igalaxy.boot.service.auth;


import com.igalaxy.boot.domain.auth.AuthUserRole;
import com.igalaxy.boot.service.base.BaseService;

import java.util.List;

/**
 * Created by fuguolei
 */
public interface AuthUserRoleService extends BaseService<AuthUserRole> {
    public List<AuthUserRole> selectNoUserInRole(long roleId);
}
