package com.igalaxy.boot.domain.sys;

import com.igalaxy.boot.domain.BaseDomain;

import java.io.Serializable;

/**
 * Created by fuguolei
 */
public class SysRolePermission extends BaseDomain {

    private Long roleId;
    private Long permissionId;
    //
    private String permissionName;
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
