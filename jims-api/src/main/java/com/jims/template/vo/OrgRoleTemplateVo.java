package com.jims.template.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangruidong on 2016/8/10 0027.
 */
public class OrgRoleTemplateVo<T> implements Serializable{

    private String area;
    private String areaName;
    private List<T> inserted;//新增的项目
    private List<T> deleted;//删除的项目
    private List<T> updated;//更新的项目

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<T> getInserted() {
        return inserted;
    }

    public void setInserted(List<T> inserted) {
        this.inserted = inserted;
    }

    public List<T> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<T> deleted) {
        this.deleted = deleted;
    }

    public List<T> getUpdated() {
        return updated;
    }

    public void setUpdated(List<T> updated) {
        this.updated = updated;
    }
}
