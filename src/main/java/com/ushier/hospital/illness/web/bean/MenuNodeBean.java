package com.ushier.hospital.illness.web.bean;

import java.util.List;

public class MenuNodeBean {
    private Integer id;
    private String text;
    private String icon;
    private State state;
    private List<MenuNodeBean> nodes;

    public List<MenuNodeBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuNodeBean> nodes) {
        this.nodes = nodes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static class State{
        private Boolean checked;// 指示一个节点是否处于checked状态
        private Boolean disabled;// 指示一个节点是否处于disabled状态
        private Boolean expanded;// 指示一个节点是否处于展开状态
        private Boolean selected;// 指示一个节点是否可以被选择

        public Boolean getChecked() {
            return checked;
        }

        public void setChecked(Boolean checked) {
            this.checked = checked;
        }

        public Boolean getDisabled() {
            return disabled;
        }

        public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
        }

        public Boolean getExpanded() {
            return expanded;
        }

        public void setExpanded(Boolean expanded) {
            this.expanded = expanded;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }
    }
}
