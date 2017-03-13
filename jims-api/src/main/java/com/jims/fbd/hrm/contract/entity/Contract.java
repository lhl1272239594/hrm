/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.contract.entity;

import com.jims.common.persistence.DataEntity;


/**
 *合同管理Entity
 * @author
 * @version 2016-09-21
 */
public class Contract extends DataEntity<Contract> {

    private static final long serialVersionUID = 1L;


    private String contractId;
    private String contractCode;
    private String contractName;
    private String contractType;
    private String startDate;
    private String endDate;
    private String endStartDate;
    private String endEndDate;
    private String url;              //
    private String num;
    private String flag;
    private String userId;//
    private String deptId;
    private String orgId;
    private String contractRemindTime;
    private String statusType;
    private String contractTypeDes;
    private String probationPeriodId;
    private String remainingTime;
    private String  probationPeriodTimes;
    private String  remindTime;

    public String getProbationPeriodTimes() {
        return probationPeriodTimes;
    }

    public void setProbationPeriodTimes(String probationPeriodTimes) {
        this.probationPeriodTimes = probationPeriodTimes;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getProbationPeriodId() {
        return probationPeriodId;
    }

    public void setProbationPeriodId(String probationPeriodId) {
        this.probationPeriodId = probationPeriodId;
    }

    public String getContractTypeDes() {
        return contractTypeDes;
    }

    public void setContractTypeDes(String contractTypeDes) {
        this.contractTypeDes = contractTypeDes;
    }

    public String getEndStartDate() {
        return endStartDate;
    }

    public void setEndStartDate(String endStartDate) {
        this.endStartDate = endStartDate;
    }

    public String getEndEndDate() {
        return endEndDate;
    }

    public void setEndEndDate(String endEndDate) {
        this.endEndDate = endEndDate;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getContractRemindTime() {
        return contractRemindTime;
    }

    public void setContractRemindTime(String contractRemindTime) {
        this.contractRemindTime = contractRemindTime;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}