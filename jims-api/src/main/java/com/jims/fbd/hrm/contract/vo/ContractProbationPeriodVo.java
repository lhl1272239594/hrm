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
public class ContractProbationPeriodVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContractProbationPeriodVo() {
    }

    private List<T> inserted;//新增的项目
    private List<T> updated;//更新的项目
    private List<T> deleted;//更新的项目
    private String probationPeriodId;
    private String probationPeriodTimes;
    private String remindTime;
    private String num;
    private String flag;
    private String value;
    private String label;

    public ContractProbationPeriodVo(List<T> inserted, List<T> updated, List<T> deleted, String probationPeriodId, String probationPeriodTimes, String remindTime, String num, String flag, String value, String label) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.probationPeriodId = probationPeriodId;
        this.probationPeriodTimes = probationPeriodTimes;
        this.remindTime = remindTime;
        this.num = num;
        this.flag = flag;
        this.value = value;
        this.label = label;
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