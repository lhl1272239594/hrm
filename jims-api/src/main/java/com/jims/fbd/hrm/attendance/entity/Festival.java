/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *法定节日管理Entity
 * @author
 * @version 2016-09-21
 */
public class Festival extends DataEntity<Festival> {

    private static final long serialVersionUID = 1L;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    private String fesId;        //id
    private String yearId;     //年份
    private String fesDesId;            //节日描述id
    private String fesDate;              //节日日期
    private String fesDateType;              //节日类型：工作、休息
    private String delFlag;              //
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String fesDateId;
    private String fesDes;
    private String yearDes;
    private String sumDays;
    private String statusType;
    private String num;

    public String getFesId() {
        return fesId;
    }

    public void setFesId(String fesId) {
        this.fesId = fesId;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getFesDesId() {
        return fesDesId;
    }

    public void setFesDesId(String fesDesId) {
        this.fesDesId = fesDesId;
    }

    public String getFesDate() {
        return fesDate;
    }

    public void setFesDate(String fesDate) {
        this.fesDate = fesDate;
    }

    public String getFesDateType() {
        return fesDateType;
    }

    public void setFesDateType(String fesDateType) {
        this.fesDateType = fesDateType;
    }

    @Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public String getFesDateId() {
        return fesDateId;
    }

    public void setFesDateId(String fesDateId) {
        this.fesDateId = fesDateId;
    }

    public String getFesDes() {
        return fesDes;
    }

    public void setFesDes(String fesDes) {
        this.fesDes = fesDes;
    }

    public String getYearDes() {
        return yearDes;
    }

    public void setYearDes(String yearDes) {
        this.yearDes = yearDes;
    }

    public String getSumDays() {
        return sumDays;
    }

    public void setSumDays(String sumDays) {
        this.sumDays = sumDays;
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