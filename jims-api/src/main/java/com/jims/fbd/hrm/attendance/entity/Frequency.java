/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *班次项目管理Entity
 * @author
 * @version 2016-09-21
 */
public class Frequency extends DataEntity<Frequency> {

    private static final long serialVersionUID = 1L;


    private String freItemId;        //id
    private String freItemDes;
    private String freTypeId;        //id
    private String freTypeDes;
    private String freTimeId;
    private String freTimeDes;
    private String startTime;              //日期
    private String startCheckType;
    private String endTime;//
    private String endCheckType;
    private String firstLeaveTime;
    private String lastComeTime;  //
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String yearDes;
    private String yearId;
    private String statusType;
    private String num;
    private String flag;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFreItemId() {
        return freItemId;
    }

    public void setFreItemId(String freItemId) {
        this.freItemId = freItemId;
    }

    public String getFreItemDes() {
        return freItemDes;
    }

    public void setFreItemDes(String freItemDes) {
        this.freItemDes = freItemDes;
    }

    public String getFreTypeId() {
        return freTypeId;
    }

    public void setFreTypeId(String freTypeId) {
        this.freTypeId = freTypeId;
    }

    public String getFreTypeDes() {
        return freTypeDes;
    }

    public void setFreTypeDes(String freTypeDes) {
        this.freTypeDes = freTypeDes;
    }

    public String getFreTimeId() {
        return freTimeId;
    }

    public void setFreTimeId(String freTimeId) {
        this.freTimeId = freTimeId;
    }

    public String getFreTimeDes() {
        return freTimeDes;
    }

    public void setFreTimeDes(String freTimeDes) {
        this.freTimeDes = freTimeDes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartCheckType() {
        return startCheckType;
    }

    public void setStartCheckType(String startCheckType) {
        this.startCheckType = startCheckType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndCheckType() {
        return endCheckType;
    }

    public void setEndCheckType(String endCheckType) {
        this.endCheckType = endCheckType;
    }

    public String getFirstLeaveTime() {
        return firstLeaveTime;
    }

    public void setFirstLeaveTime(String firstLeaveTime) {
        this.firstLeaveTime = firstLeaveTime;
    }

    public String getLastComeTime() {
        return lastComeTime;
    }

    public void setLastComeTime(String lastComeTime) {
        this.lastComeTime = lastComeTime;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}