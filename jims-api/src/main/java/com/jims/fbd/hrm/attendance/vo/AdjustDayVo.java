/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @version 2016-08-18
 */
public class AdjustDayVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public AdjustDayVo() {
    }

    private List<T> inserted;//新增的项目
    private List<T> updated;//更新的项目
    private List<T> deleted;//更新的项目
    private String adjustDayId;
    private String startDate;
    private String endDate;
    private String userId;//
    private String sumDays;              //
    private String approveStatus;            //
    private String approvePersonId;            //
    private String num;
    private String flag;
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String userName;
    private String deptId;
    private String adjustDayType;
    private String holidayType;


    public AdjustDayVo(List<T> inserted, List<T> updated, List<T> deleted, String adjustDayId, String startDate, String endDate, String userId, String sumDays, String approveStatus, String approvePersonId, String num, String flag, String createDept, String createOrg, String orgId, String createRole, String statusType, String userName, String deptId, String adjustDayType, String holidayType) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.adjustDayId = adjustDayId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.sumDays = sumDays;
        this.approveStatus = approveStatus;
        this.approvePersonId = approvePersonId;
        this.num = num;
        this.flag = flag;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.orgId = orgId;
        this.createRole = createRole;
        this.statusType = statusType;
        this.userName = userName;
        this.deptId = deptId;
        this.adjustDayType = adjustDayType;
        this.holidayType = holidayType;
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

    public String getAdjustDayId() {
        return adjustDayId;
    }

    public void setAdjustDayId(String adjustDayId) {
        this.adjustDayId = adjustDayId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApprovePersonId() {
        return approvePersonId;
    }

    public void setApprovePersonId(String approvePersonId) {
        this.approvePersonId = approvePersonId;
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

    public String getCreateRole() {
        return createRole;
    }

    public void setCreateRole(String createRole) {
        this.createRole = createRole;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getAdjustDayType() {
        return adjustDayType;
    }

    public void setAdjustDayType(String adjustDayType) {
        this.adjustDayType = adjustDayType;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }
}