/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.vo;

import com.jims.common.persistence.DataEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 工作组字典Entity
 *
 * @author yangruidong
 * @version 2016-05-23
 */
public class StaffVsGroupVo<T> implements Serializable {

    private List<T> inserted ;//新增的项目
    private List<T> deleted ;//删除的项目
    private List<T> updated ;//更新的项目

    private String groupClass;   //组类
    private String groupName;        //组名
    private String groupId;     //组id
    private String groupClassId;      //组类id
    private String id;
    private String orgId;            //组织机构id
    private String pid;              //父id
    private String name;            //姓名
    private String deptName;     //科室名称
    private String  staffId;       //组织机构人员id
    private String groupCode;    //组代码
    private String staffVsId;    //staff_vs_group的id
    private String inputCode;   //拼音码

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StaffVsGroupVo() {
    }

    public String getName() {
        return name;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffVsId() {
        return staffVsId;
    }

    public void setStaffVsId(String staffVsId) {
        this.staffVsId = staffVsId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public StaffVsGroupVo(List<T> inserted, List<T> deleted, List<T> updated, String groupClass, String groupName, String id, String groupClassId, String orgId) {
        this.inserted = inserted;
        this.deleted = deleted;
        this.updated = updated;
        this.groupClass = groupClass;
        this.groupName = groupName;
        this.groupClassId = groupClassId;
        this.orgId = orgId;


    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(String groupClass) {
        this.groupClass = groupClass;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupClassId() {
        return groupClassId;
    }

    public void setGroupClassId(String groupClassId) {
        this.groupClassId = groupClassId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}