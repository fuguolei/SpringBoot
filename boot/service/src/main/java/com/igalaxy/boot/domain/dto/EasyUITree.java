package com.igalaxy.boot.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinlong
 */
public class EasyUITree {
    private Long id;

    private String text;

    private List<EasyUITree> children = new ArrayList<>();

    private String state;

    private boolean checked;

    private Object attributes;

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<EasyUITree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUITree> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
