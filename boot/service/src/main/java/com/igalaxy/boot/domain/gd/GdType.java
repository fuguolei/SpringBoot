package com.igalaxy.boot.domain.gd;

import com.igalaxy.boot.domain.BaseDomain;

/**
 * Created by fuguolei on 2017/8/31.
 */
public class GdType extends BaseDomain {
    private Long parentId;
    private String name;
    private String description;
    private Integer sort;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
