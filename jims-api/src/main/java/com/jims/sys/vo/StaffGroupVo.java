package com.jims.sys.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * StaffGroupDict的Vo
 * createdBy  yangruidong
 * @param <T>
 */
@XmlRootElement
public class StaffGroupVo<T> implements Serializable {

    private List<T> inserted ;//新增的项目
    private List<T> deleted ;//删除的项目
    private List<T> updated ;//更新的项目

    private String orgId;   //组织机构id
    private String staff_group_class__id;   //工作组类的id


    public String getStaff_group_class__id() {
        return staff_group_class__id;
    }

    public void setStaff_group_class__id(String staff_group_class__id) {
        this.staff_group_class__id = staff_group_class__id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public StaffGroupVo() {
    }

    public StaffGroupVo(List<T> inserted, List<T> deleted, List<T> updated) {
        this.inserted = inserted;
        this.deleted = deleted;
        this.updated = updated;
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
