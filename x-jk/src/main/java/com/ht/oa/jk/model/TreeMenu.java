package com.ht.oa.jk.model;

import java.io.Serializable;
import java.util.List;

public class TreeMenu implements Serializable {

    private long id;
    private long parentId;
    private String text;
    private String code;
    private String iconCls = "sysMenuIcon";
    private String hrefTarget;
    private String description;
    private boolean leaf;
    private int priority;
    private boolean checked;

    private List<TreeMenu> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHrefTarget() {
        return hrefTarget;
    }

    public void setHrefTarget(String hrefTarget) {
        this.hrefTarget = hrefTarget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<TreeMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TreeMenu> children) {
        this.children = children;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
