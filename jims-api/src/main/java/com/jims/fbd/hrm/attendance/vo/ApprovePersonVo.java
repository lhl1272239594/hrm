/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import com.jims.fbd.hrm.attendance.entity.ApprovePersonUser;

import java.io.Serializable;
import java.util.List;

/**
 * 考勤审批管理人员vo
 * @author
 * @version 2016-09-21
 */
public class ApprovePersonVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public ApprovePersonVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

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


    public ApprovePersonVo(List<T> inserted, List<T> updated, List<T> deleted, String appPersonId, String attFunId, String orgId, String userId, String deptId, String num, String flag, String value, String label, List<ApprovePersonUser> approvePersonUser) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.appPersonId = appPersonId;
        this.attFunId = attFunId;
        this.orgId = orgId;
        this.userId = userId;
        this.deptId = deptId;
        this.num = num;
        this.flag = flag;
        this.value = value;
        this.label = label;
        this.approvePersonUser = approvePersonUser;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<T> getInserted() {
        return inserted;
    }

    public void setInserted(List<T> inserted) {
        this.inserted = inserted;
    }

    public List<T> getUpdated() {
        return updated;
    }

    public void setUpdated(List<T> updated) {
        this.updated = updated;
    }

    public List<T> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<T> deleted) {
        this.deleted = deleted;
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