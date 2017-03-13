package com.jims.sys.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * AdministrationDict的Vo
 * createdBy  yangruidong
 * @param <T>
 */
@XmlRootElement
public class AdministrationDictVo<T> implements Serializable {

    private List<T> inserted ;//新增的项目
    private List<T> deleted ;//删除的项目
    private List<T> updated ;//更新的项目


    public AdministrationDictVo() {
    }

    public AdministrationDictVo(List<T> inserted, List<T> deleted, List<T> updated) {
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
