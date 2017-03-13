/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.contract.vo;

import java.io.Serializable;
import java.util.List;

/**
 *合同管理vo
 * @author
 * @version 2016-09-21
 */
public class ContractVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContractVo() {
    }

    private List<T> inserted;//新增的项目
    private List<T> updated;//更新的项目
    private List<T> deleted;//更新的项目
    private String contractId;
    private String contractCode;
    private String contractName;
    private String contractType;
    private String startDate;
    private String endDate;
    private String url;              //
    private String num;
    private String flag;
    private String userId;//
    private String deptId;
    private String orgId;
    private String probationPeriodId;

    public ContractVo(List<T> inserted, List<T> updated, List<T> deleted, String contractId, String contractCode, String contractName, String contractType, String startDate, String endDate, String url, String num, String flag, String userId, String deptId, String orgId, String probationPeriodId) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.contractId = contractId;
        this.contractCode = contractCode;
        this.contractName = contractName;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = url;
        this.num = num;
        this.flag = flag;
        this.userId = userId;
        this.deptId = deptId;
        this.orgId = orgId;
        this.probationPeriodId = probationPeriodId;
    }

    public String getProbationPeriodId() {
        return probationPeriodId;
    }

    public void setProbationPeriodId(String probationPeriodId) {
        this.probationPeriodId = probationPeriodId;
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