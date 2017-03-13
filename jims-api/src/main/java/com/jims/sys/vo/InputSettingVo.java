package com.jims.sys.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * InputSettingMaster的Vo
 * createdBy  yangruidong
 * @param <T>
 */
@XmlRootElement
public class InputSettingVo<T> implements Serializable {

    private List<T> inserted ;//新增的项目
    private List<T> deleted ;//删除的项目
    private List<T> updated ;//更新的项目

    private String orgId;   //组织机构id
    private String input_setting_master_id;   //输入法字典主记录的id

    private String columnName;         //字段名
    private String resultSort;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getResultSort() {
        return resultSort;
    }

    public void setResultSort(String resultSort) {
        this.resultSort = resultSort;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getInput_setting_master_id() {
        return input_setting_master_id;
    }

    public void setInput_setting_master_id(String input_setting_master_id) {
        this.input_setting_master_id = input_setting_master_id;
    }

    public InputSettingVo() {
    }

    public InputSettingVo(List<T> inserted, List<T> deleted, List<T> updated) {
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
