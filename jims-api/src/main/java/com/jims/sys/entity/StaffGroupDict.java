/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 工作组字典Entity
 * @author yangruidong
 * @version 2016-05-23
 */
public class StaffGroupDict extends DataEntity<StaffGroupDict> {

    private static final long serialVersionUID = 1L;
    private String groupCode;    //组代码
    private String groupName;     //组名称
    private String inputCode;
    private String deptCode;
    private String deptName;
    private String groupClassId;   //工作组类的id   外键
    private String groupManager;
    private String deptId;
    public StaffGroupDict(){}

    public StaffGroupDict(String groupCode, String groupName, String inputCode, String deptCode, String deptName, String groupClassId, String groupManager) {
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.inputCode = inputCode;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.groupClassId = groupClassId;
        this.groupManager = groupManager;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public StaffGroupDict(String id, String groupCode, String groupName, String inputCode, String deptCode, String deptName, String groupClassId, String groupManager) {
        super(id);
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.inputCode = inputCode;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.groupClassId = groupClassId;
        this.groupManager = groupManager;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getGroupClassId() {
        return groupClassId;
    }

    public void setGroupClassId(String groupClassId) {
        this.groupClassId = groupClassId;
    }

    public String getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(String groupManager) {
        this.groupManager = groupManager;
    }
}