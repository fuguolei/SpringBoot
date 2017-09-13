package com.igalaxy.boot.domain.gd;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.SysProperty.SysSaleEnum;

/**
 * Created by fuguolei on 2017/8/31.
 */
public class GdProduct extends BaseDomain {
    private Long type;
    private Long number;
    private String name;
    private SysSaleEnum sale;
    private String title;//小分类title
    private Integer sort;

    //
    private String typeName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SysSaleEnum getSale() {
        return sale;
    }

    public void setSale(SysSaleEnum sale) {
        this.sale = sale;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
