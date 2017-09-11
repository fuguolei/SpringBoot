package com.igalaxy.boot.domain.auth;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;

/**
 * Created by fuguolei
 */
public class AuthPermission extends BaseDomain {
    private Long resourceId;
    private String name;
    private String permission;
    private String iconCls;
    //
    private WhetherEnum hasPermission;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public WhetherEnum getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(WhetherEnum hasPermission) {
        this.hasPermission = hasPermission;
    }
}
