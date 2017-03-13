/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import com.jims.fbd.hrm.attendance.entity.ApproveProcess;

import java.io.Serializable;
import java.util.List;

/**
 * 考勤审批流程管理vo
 * @author
 * @version 2016-09-21
 */
public class ApproveProcessVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public ApproveProcessVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

    private String processId;
    private String processDes;
    private String processStepNum;
    private String userId;
    private String deptId;
    private String num;
    private String flag;
    private String value;
    private String label;
    private String startCondition;
    private String endCondition;
    private String stepId;
    private String stepOrder;
    private String approvePersonId;
    private String attFunId;
    private String  stepName;

    private List<ApproveProcess> approveProcess;


    public ApproveProcessVo(List<T> inserted, List<T> updated, List<T> deleted, String processId, String processDes, String processStepNum, String userId, String deptId, String num, String flag, String value, String label, String startCondition, String endCondition, String stepId, String stepOrder, String approvePersonId, String attFunId, String stepName, List<ApproveProcess> approveProcess) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.processId = processId;
        this.processDes = processDes;
        this.processStepNum = processStepNum;
        this.userId = userId;
        this.deptId = deptId;
        this.num = num;
        this.flag = flag;
        this.value = value;
        this.label = label;
        this.startCondition = startCondition;
        this.endCondition = endCondition;
        this.stepId = stepId;
        this.stepOrder = stepOrder;
        this.approvePersonId = approvePersonId;
        this.attFunId = attFunId;
        this.stepName = stepName;
        this.approveProcess = approveProcess;
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

    public String getProcessStepNum() {
        return processStepNum;
    }

    public void setProcessStepNum(String processStepNum) {
        this.processStepNum = processStepNum;
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

    public String getStartCondition() {
        return startCondition;
    }

    public void setStartCondition(String startCondition) {
        this.startCondition = startCondition;
    }

    public String getEndCondition() {
        return endCondition;
    }

    public void setEndCondition(String endCondition) {
        this.endCondition = endCondition;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(String stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getApprovePersonId() {
        return approvePersonId;
    }

    public void setApprovePersonId(String approvePersonId) {
        this.approvePersonId = approvePersonId;
    }

    public String getAttFunId() {
        return attFunId;
    }

    public void setAttFunId(String attFunId) {
        this.attFunId = attFunId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public List<ApproveProcess> getApproveProcess() {
        return approveProcess;
    }

    public void setApproveProcess(List<ApproveProcess> approveProcess) {
        this.approveProcess = approveProcess;
    }
}