package com.jims.util;

import java.util.List;

/**
 * Created by yangruidong on 2016/10/17 0017.
 */
public class CombotreeUntils {
    public String id;
    public String text;
    public List<CombotreeUntils> children ;
    public String  parentId ;
    public  String state;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<CombotreeUntils> getChildren() {
        return children;
    }

    public void setChildren(List<CombotreeUntils> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
