package com.igalaxy.boot.domain.sys;

import com.igalaxy.boot.domain.BaseDomain;

import java.util.List;

/**
 * Created by fuguolei
 */
public class SysRole extends BaseDomain {
    private String name;
    private String description;
    //
    private List<SysResource> resources;
    private List<SysPermission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysResource> getResources() {
        return resources;
    }

    public void setResources(List<SysResource> resources) {
        this.resources = resources;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
