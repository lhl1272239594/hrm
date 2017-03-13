/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 考勤审批管理Entity
 * @author
 * @version 2016-09-21
 */
public class Approve extends DataEntity<Approve> {

    private static final long serialVersionUID = 1L;


    private String attFunId;
    private String attendanceId;
    private String approveId;
    private String orgId;
    private String userId;
    private String deptId;
    private String num;
    private String flag;
    private String value;
    private String label;
    private String dataId;
    private String startDate;
    private String endDate;
    private String approveStatus;
    private String approvePersonId;
    private String approveDate;
    private String appRecordId;
    private String appOpinions;
    private String stepId;
    private String processId;
    private String stepOrder;
    private String statusType;
    private String approvePerson;
    private String processDes;
    private String userName;
    private String stepName;
    private String lastStepOrder;
    private String approveRecordId;
    private String processStepNum;
    private String approveOpinions;

    public String getApproveOpinions() {
        return approveOpinions;
    }

    public void setApproveOpinions(String approveOpinions) {
        this.approveOpinions = approveOpinions;
    }

    public String getProcessStepNum() {
        return processStepNum;
    }

    public void setProcessStepNum(String processStepNum) {
        this.processStepNum = processStepNum;
    }

    public String getApproveRecordId() {
        return approveRecordId;
    }

    public void setApproveRecordId(String approveRecordId) {
        this.approveRecordId = approveRecordId;
    }

    public String getLastStepOrder() {
        return lastStepOrder;
    }

    public void setLastStepOrder(String lastStepOrder) {
        this.lastStepOrder = lastStepOrder;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProcessDes() {
        return processDes;
    }

    public void setProcessDes(String processDes) {
        this.processDes = processDes;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(String stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
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

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getAppRecordId() {
        return appRecordId;
    }

    public void setAppRecordId(String appRecordId) {
        this.appRecordId = appRecordId;
    }

    public String getAppOpinions() {
        return appOpinions;
    }

    public void setAppOpinions(String appOpinions) {
        this.appOpinions = appOpinions;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}