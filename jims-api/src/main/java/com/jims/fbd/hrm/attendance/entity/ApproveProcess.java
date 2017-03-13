/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 考勤审批流程管理Entity
 * @author
 * @version 2016-09-21
 */
public class ApproveProcess extends DataEntity<ApproveProcess> {

    private static final long serialVersionUID = 1L;


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
    private String approveDeptId;
    private String approvePerson;
    private String approvePersonDept;
    private String attFunId;
    private String statusType;
    private String condition;
    private String treeId;
    private String parentId;
    private String treeDes;
    private String treeType;
    private String  stepName;

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTreeDes() {
        return treeDes;
    }

    public void setTreeDes(String treeDes) {
        this.treeDes = treeDes;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getApproveDeptId() {
        return approveDeptId;
    }

    public void setApproveDeptId(String approveDeptId) {
        this.approveDeptId = approveDeptId;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }

    public String getApprovePersonDept() {
        return approvePersonDept;
    }

    public void setApprovePersonDept(String approvePersonDept) {
        this.approvePersonDept = approvePersonDept;
    }

    public String getAttFunId() {
        return attFunId;
    }

    public void setAttFunId(String attFunId) {
        this.attFunId = attFunId;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}