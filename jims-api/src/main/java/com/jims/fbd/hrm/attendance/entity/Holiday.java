/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *假日管理Entity
 * @author
 * @version 2016-09-21
 */
public class Holiday extends DataEntity<Holiday> {

    private static final long serialVersionUID = 1L;


    private String holId;        //id
    private String holDes;
    private String holDesId;
    private String holDate;              //日期
    private String holPayTypeId;
    private String holDateType;//     假日类型 带薪、扣薪水
    private String sumDays;              //
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String yearDes;
    private String yearId;
    private String statusType;
    private String num;
    private String holPayType;
    private String flag;
    private String value;
    private String label;


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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHolPayType() {
        return holPayType;
    }

    public void setHolPayType(String holPayType) {
        this.holPayType = holPayType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHolId() {
        return holId;
    }

    public void setHolId(String holId) {
        this.holId = holId;
    }

    public String getHolDes() {
        return holDes;
    }

    public void setHolDes(String holDes) {
        this.holDes = holDes;
    }

    public String getHolDesId() {
        return holDesId;
    }

    public void setHolDesId(String holDesId) {
        this.holDesId = holDesId;
    }

    public String getHolDate() {
        return holDate;
    }

    public void setHolDate(String holDate) {
        this.holDate = holDate;
    }

    public String getHolPayTypeId() {
        return holPayTypeId;
    }

    public void setHolPayTypeId(String holPayTypeId) {
        this.holPayTypeId = holPayTypeId;
    }

    public String getHolDateType() {
        return holDateType;
    }

    public void setHolDateType(String holDateType) {
        this.holDateType = holDateType;
    }

    public String getSumDays() {
        return sumDays;
    }

    public void setSumDays(String sumDays) {
        this.sumDays = sumDays;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateOrg() {
        return createOrg;
    }

    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getYearDes() {
        return yearDes;
    }

    public void setYearDes(String yearDes) {
        this.yearDes = yearDes;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}