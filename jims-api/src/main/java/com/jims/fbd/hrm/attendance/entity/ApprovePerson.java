/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

import java.util.List;

/**
 * 考勤审批管理Entity
 * @author
 * @version 2016-09-21
 */
public class ApprovePerson extends DataEntity<ApprovePerson> {

    private static final long serialVersionUID = 1L;


    private String appPersonId;
    private String attFunId;
    private String orgId;
    private String userId;
    private String deptId;
    private String num;
    private String flag;
    private String value;
    private String label;

    private List<ApprovePersonUser> approvePersonUser;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAppPersonId() {
        return appPersonId;
    }

    public void setAppPersonId(String appPersonId) {
        this.appPersonId = appPersonId;
    }

    public String getAttFunId() {
        return attFunId;
    }

    public void setAttFunId(String attFunId) {
        this.attFunId = attFunId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ApprovePersonUser> getApprovePersonUser() {
        return approvePersonUser;
    }

    public void setApprovePersonUser(List<ApprovePersonUser> approvePersonUser) {
        this.approvePersonUser = approvePersonUser;
    }
}