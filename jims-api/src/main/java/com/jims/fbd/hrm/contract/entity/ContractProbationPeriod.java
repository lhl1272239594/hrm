/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.contract.entity;

import com.jims.common.persistence.DataEntity;


/**
 *试用期管理Entity
 * @author
 * @version 2017-03-07
 */
public class ContractProbationPeriod extends DataEntity<ContractProbationPeriod> {

    private static final long serialVersionUID = 1L;


    private String probationPeriodId;
    private String probationPeriodTimes;
    private String remindTime;
    private String num;
    private String flag;
    private String value;
    private String label;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProbationPeriodId() {
        return probationPeriodId;
    }

    public void setProbationPeriodId(String probationPeriodId) {
        this.probationPeriodId = probationPeriodId;
    }

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