package com.jims.sys.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 多条数据的增删改Vo类
 * Created by fyg on 2016/5/6.
 */
@XmlRootElement
public class BeanChangeVo<T> implements Serializable{
    private List<T> inserted;//新增的数据
    private List<T> deleted;//删除的数据
    private List<T> updated;//更新的数据

    public BeanChangeVo() {
    }

    public BeanChangeVo(List<T> inserted, List<T> deleted, List<T> updated) {
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
