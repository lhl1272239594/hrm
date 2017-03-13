/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 科室组成员字典Entity
 *
 * @author yangruidong
 * @version 2016-05-23
 */
public class StaffVsGroup extends DataEntity<StaffVsGroup> {

    private static final long serialVersionUID = 1L;
    private String groupClass;   //组类
    private String groupCode;    //组代码
    private String staffId;      //人员id
    private String groupId;      //组id

    public StaffVsGroup() {
    }

    public StaffVsGroup(String id, String groupClass, String groupCode, String staffId, String groupId) {
        super(id);
        this.groupClass = groupClass;
        this.groupCode = groupCode;
        this.staffId = staffId;
        this.groupId = groupId;
    }

    public String getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(String groupClass) {
        this.groupClass = groupClass;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}