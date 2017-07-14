package com.igalaxy.boot.domain.dto;

/**
 * Created by jinlong
 */
public class ListViewPage {
    private Integer total;
    private Object rows;

    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
