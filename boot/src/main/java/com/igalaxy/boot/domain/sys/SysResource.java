package com.igalaxy.boot.domain.sys;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;

import java.util.List;

/**
 * Created by fuguolei
 */
public class SysResource extends BaseDomain {
    private String iconCls;
    private String name;
    private String description;
    private String url;
    private Integer parentId;
    private String urlPattern;

    private WhetherEnum hasPermission;
    private List<SysPermission> permissions;

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public WhetherEnum getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(WhetherEnum hasPermission) {
        this.hasPermission = hasPermission;
    }
}
