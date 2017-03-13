/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

import java.util.List;

/**
 *
 * @version 2016-08-18
 */
public class HolidayPerson extends DataEntity<HolidayPerson> {

    private static final long serialVersionUID = 1L;


    private String holPersonId;
    private String holId;
    private String holDes;
    private String holPayTypeId;
    private String holDateType;//
    private String sumDays;
    private String sumHours; //
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String userId;
    private String deptId;
    private String statusType;
    private String num;
    private String flag;
    private String value;
    private String label;
    private String yearId;
    private List<HolidayPersonUser> holidayPersonUser;

    public List<HolidayPersonUser> getHolidayPersonUser() {
        return holidayPersonUser;
    }

    public void setHolidayPersonUser(List<HolidayPersonUser> holidayPersonUser) {
        this.holidayPersonUser = holidayPersonUser;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getHolDes() {
        return holDes;
    }

    public void setHolDes(String holDes) {
        this.holDes = holDes;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHolPersonId() {
        return holPersonId;
    }

    public void setHolPersonId(String holPersonId) {
        this.holPersonId = holPersonId;
    }

    public String getHolId() {
        return holId;
    }

    public void setHolId(String holId) {
        this.holId = holId;
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

    public String getSumHours() {
        return sumHours;
    }

    public void setSumHours(String sumHours) {
        this.sumHours = sumHours;
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