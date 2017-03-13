/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.contract.vo;

import java.io.Serializable;
import java.util.List;

/**
 *合同类型管理vo
 * @author
 * @version 2016-09-21
 */
public class ContractTypeVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContractTypeVo() {
    }

    private List<T> inserted;//新增的项目
    private List<T> updated;//更新的项目
    private List<T> deleted;//更新的项目
    private String contractTypeId;
    private String contractTypeDes;
    private String contractRemindTime;


    public ContractTypeVo(List<T> inserted, List<T> updated, List<T> deleted, String contractTypeId, String contractTypeDes, String contractRemindTime) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.contractTypeId = contractTypeId;
        this.contractTypeDes = contractTypeDes;
        this.contractRemindTime = contractRemindTime;
    }

    public String getContractRemindTime() {
        return contractRemindTime;
    }

    public void setContractRemindTime(String contractRemindTime) {
        this.contractRemindTime = contractRemindTime;
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

    public String getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(String contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getContractTypeDes() {
        return contractTypeDes;
    }

    public void setContractTypeDes(String contractTypeDes) {
        this.contractTypeDes = contractTypeDes;
    }
}