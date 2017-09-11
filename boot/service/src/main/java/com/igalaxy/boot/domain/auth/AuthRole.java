package com.igalaxy.boot.domain.auth;

import com.igalaxy.boot.domain.BaseDomain;

import java.util.List;

/**
 * Created by fuguolei
 */
public class AuthRole extends BaseDomain {
    private String name;
    private String description;
    //
    private List<AuthResource> resources;
    private List<AuthPermission> permissions;

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

    public List<AuthResource> getResources() {
        return resources;
    }

    public void setResources(List<AuthResource> resources) {
        this.resources = resources;
    }

    public List<AuthPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<AuthPermission> permissions) {
        this.permissions = permissions;
    }
}
