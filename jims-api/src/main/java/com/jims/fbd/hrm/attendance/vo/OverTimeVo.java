/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import java.io.Serializable;
import java.util.List;

/**
 *加班管理vo
 * @author
 * @version 2016-09-21
 */
public class OverTimeVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public OverTimeVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目
    private String overTimeId;
    private String startDate;
    private String endDate;
    private String userId;//
    private String sumDays;              //
    private String approveStatus;            //
    private String approvePersonId;            //
    private String overTimeType;
    private String overTimeReason;
    private String num;
    private String flag;
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String userName;
    private String userCode;
    private String holidayId;
    private String deptId;

    public OverTimeVo(List<T> inserted, List<T> updated, List<T> deleted, String overTimeId, String startDate, String endDate, String userId, String sumDays, String approveStatus, String approvePersonId, String overTimeType, String overTimeReason, String num, String flag, String createDept, String createOrg, String orgId, String createRole, String statusType, String userName, String userCode, String holidayId, String deptId) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.overTimeId = overTimeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.sumDays = sumDays;
        this.approveStatus = approveStatus;
        this.approvePersonId = approvePersonId;
        this.overTimeType = overTimeType;
        this.overTimeReason = overTimeReason;
        this.num = num;
        this.flag = flag;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.orgId = orgId;
        this.createRole = createRole;
        this.statusType = statusType;
        this.userName = userName;
        this.userCode = userCode;
        this.holidayId = holidayId;
        this.deptId = deptId;
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

    public String getOverTimeId() {
        return overTimeId;
    }

    public void setOverTimeId(String overTimeId) {
        this.overTimeId = overTimeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSumDays() {
        return sumDays;
    }

    public void setSumDays(String sumDays) {
        this.sumDays = sumDays;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApprovePersonId() {
        return approvePersonId;
    }

    public void setApprovePersonId(String approvePersonId) {
        this.approvePersonId = approvePersonId;
    }

    public String getOverTimeType() {
        return overTimeType;
    }

    public void setOverTimeType(String overTimeType) {
        this.overTimeType = overTimeType;
    }

    public String getOverTimeReason() {
        return overTimeReason;
    }

    public void setOverTimeReason(String overTimeReason) {
        this.overTimeReason = overTimeReason;
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

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateOrg() {
        return createOrg;
    }

    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateRole() {
        return createRole;
    }

    public void setCreateRole(String createRole) {
        this.createRole = createRole;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}