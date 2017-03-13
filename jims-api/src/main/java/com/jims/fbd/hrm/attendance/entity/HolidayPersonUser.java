/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *
 * @version 2016-08-18
 */
public class HolidayPersonUser extends DataEntity<HolidayPersonUser> {

    private static final long serialVersionUID = 1L;



    private String holPersonId;
    private String dataId;         //D
    private String text;               //
    private String deptId;             //部门ID
    private String orgId;              //组织机构ID
    private String userId;              //组织机构ID
    private String sumDays;
    private String yearId;
    private String sumHours;
    private String holId;


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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSumDays() {
        return sumDays;
    }

    public void setSumDays(String sumDays) {
        this.sumDays = sumDays;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getSumHours() {
        return sumHours;
    }

    public void setSumHours(String sumHours) {
        this.sumHours = sumHours;
    }


}