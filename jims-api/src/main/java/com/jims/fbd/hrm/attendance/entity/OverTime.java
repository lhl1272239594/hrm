/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *加班管理Entity
 * @author
 * @version 2016-09-21
 */
public class OverTime extends DataEntity<OverTime> {

    private static final long serialVersionUID = 1L;


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
    private String approveDate;
    private String  processId;
    private String  processDes;
    private String  stepName;
    private String  approvePerson;
    private String approveId;

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessDes() {
        return processDes;
    }

    public void setProcessDes(String processDes) {
        this.processDes = processDes;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }
}