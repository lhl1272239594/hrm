/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.contract.entity;

import com.jims.common.persistence.DataEntity;


/**
 *合同类型管理Entity
 * @author
 * @version 2016-09-21
 */
public class ContractType extends DataEntity<ContractType> {

    private static final long serialVersionUID = 1L;


    private String contractTypeId;
    private String contractTypeDes;
    private String contractRemindTime;
    private String num;
    private String flag;
    private String value;
    private String label;



    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getContractRemindTime() {
        return contractRemindTime;
    }

    public void setContractRemindTime(String contractRemindTime) {
        this.contractRemindTime = contractRemindTime;
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
}