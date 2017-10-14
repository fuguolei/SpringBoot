package com.igalaxy.boot.domain.dto;

import java.util.List;

/**
 * Created by fuguolei
 */
public class TreeViewNode {

    private Long id;
    private String text;
    private StateData state;
    private Integer type;
    private List<TreeViewNode> nodes;

    public void setChecked(boolean checked) {
        if (state == null)
            state = new StateData();
        state.setChecked(checked);
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

    public StateData getState() {
        return state;
    }

    public void setState(StateData state) {
        this.state = state;
    }

    public List<TreeViewNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeViewNode> nodes) {
        this.nodes = nodes;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    static class StateData {
        private boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
