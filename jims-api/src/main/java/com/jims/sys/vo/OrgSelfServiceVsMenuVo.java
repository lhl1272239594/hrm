package com.jims.sys.vo;

import com.jims.sys.entity.MenuDict;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class OrgSelfServiceVsMenuVo implements Serializable {

    private String id;        // 权限范围id
    private String text;                  //服务名称
    private List<MenuDictVo> children;

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

    public List<MenuDictVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDictVo> children) {
        this.children = children;
    }
}
