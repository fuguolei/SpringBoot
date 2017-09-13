package com.igalaxy.boot.domain.ep;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.SysProperty.SysSaleEnum;

/**
 * Created by fuguolei on 2017/8/31.
 */
public class EpExpress extends BaseDomain {
    private String name;
    private SysSaleEnum sale;
    private Integer sort;

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
}
