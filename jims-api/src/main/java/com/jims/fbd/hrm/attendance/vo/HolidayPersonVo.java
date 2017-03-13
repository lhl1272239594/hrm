/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import com.jims.fbd.hrm.attendance.entity.HolidayPersonUser;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @version 2016-08-18
 */
public class HolidayPersonVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public HolidayPersonVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

    private String holPersonId;
    private String holId;
    private String holDes;
    private String holPayTypeId;
    private String holDateType;//
    private String sumDays;
    private String yearId;
    private String sumHours; //
    private String createDept;            //
    private String createOrg;
    private String createBy;     //
    private String orgId;
    private String userId;
    private String deptId;
    private String statusType;
    private String num;
    private String flag;
    private String value;
    private String label;

    private List<HolidayPersonUser> holidayPersonUser;

    public HolidayPersonVo(List<T> inserted, List<T> updated, List<T> deleted, String holPersonId, String holId, String holDes, String holPayTypeId, String holDateType, String sumDays, String yearId, String sumHours, String createDept, String createOrg, String createBy, String orgId, String userId, String deptId, String statusType, String num, String flag, String value, String label, List<HolidayPersonUser> holidayPersonUser) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.holPersonId = holPersonId;
        this.holId = holId;
        this.holDes = holDes;
        this.holPayTypeId = holPayTypeId;
        this.holDateType = holDateType;
        this.sumDays = sumDays;
        this.yearId = yearId;
        this.sumHours = sumHours;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.createBy = createBy;
        this.orgId = orgId;
        this.userId = userId;
        this.deptId = deptId;
        this.statusType = statusType;
        this.num = num;
        this.flag = flag;
        this.value = value;
        this.label = label;
        this.holidayPersonUser = holidayPersonUser;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getHolDes() {
        return holDes;
    }

    public void setHolDes(String holDes) {
        this.holDes = holDes;
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

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public List<HolidayPersonUser> getHolidayPersonUser() {
        return holidayPersonUser;
    }

    public void setHolidayPersonUser(List<HolidayPersonUser> holidayPersonUserVo) {
        this.holidayPersonUser = holidayPersonUserVo;
    }
}