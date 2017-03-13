/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import com.jims.fbd.hrm.attendance.entity.Approve;

import java.io.Serializable;
import java.util.List;

/**
 * 考勤审批管理vo
 * @author
 * @version 2016-09-21
 */
public class ApproveVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public ApproveVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

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
    private String approveFlag;
    private List<Approve> approve;

    public ApproveVo(List<T> inserted, List<T> updated, List<T> deleted, String attFunId, String attendanceId, String approveId, String orgId, String userId, String deptId, String num, String flag, String value, String label, String dataId, String startDate, String endDate, String approveStatus, String approvePersonId, String approveDate, String appRecordId, String appOpinions, String stepId, String processId, String stepOrder, String statusType, String approvePerson, String processDes, String userName, String stepName, String lastStepOrder, String approveRecordId, String approveFlag, List<Approve> approve) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.attFunId = attFunId;
        this.attendanceId = attendanceId;
        this.approveId = approveId;
        this.orgId = orgId;
        this.userId = userId;
        this.deptId = deptId;
        this.num = num;
        this.flag = flag;
        this.value = value;
        this.label = label;
        this.dataId = dataId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approveStatus = approveStatus;
        this.approvePersonId = approvePersonId;
        this.approveDate = approveDate;
        this.appRecordId = appRecordId;
        this.appOpinions = appOpinions;
        this.stepId = stepId;
        this.processId = processId;
        this.stepOrder = stepOrder;
        this.statusType = statusType;
        this.approvePerson = approvePerson;
        this.processDes = processDes;
        this.userName = userName;
        this.stepName = stepName;
        this.lastStepOrder = lastStepOrder;
        this.approveRecordId = approveRecordId;
        this.approveFlag = approveFlag;
        this.approve = approve;
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

    public String getAttFunId() {
        return attFunId;
    }

    public void setAttFunId(String attFunId) {
        this.attFunId = attFunId;
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

    public String getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(String stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }

    public String getProcessDes() {
        return processDes;
    }

    public void setProcessDes(String processDes) {
        this.processDes = processDes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getLastStepOrder() {
        return lastStepOrder;
    }

    public void setLastStepOrder(String lastStepOrder) {
        this.lastStepOrder = lastStepOrder;
    }

    public String getApproveRecordId() {
        return approveRecordId;
    }

    public void setApproveRecordId(String approveRecordId) {
        this.approveRecordId = approveRecordId;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public List<Approve> getApprove() {
        return approve;
    }

    public void setApprove(List<Approve> approve) {
        this.approve = approve;
    }
}