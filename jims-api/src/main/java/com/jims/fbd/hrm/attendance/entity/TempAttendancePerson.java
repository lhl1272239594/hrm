/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *临时考勤管理Entity
 * @author
 * @version 2016-09-21
 */
public class TempAttendancePerson extends DataEntity<TempAttendancePerson> {

    private static final long serialVersionUID = 1L;


    private String tempAttId;
    private String tempAttPersonId;
    private String tempAttName;        //id//id
    private String userId;
    private String userName;
    private String deptId;
    private String deptName;
    private String userCode;
    private String orgId;
    private String statusType;
    private String num;
    private String flag;
    private String checkInStartTime;
    private String checkInEndTime;
    private String checkOutStartTime;
    private String checkOutEndTime;
    private String startTime;
    private String endTime;
    private String tempAttDate;
    private String realCheckInTime;
    private String realCheckOutTime;
    private String effectiveCheckInTime;
    private String effectiveCheckOutTime;
    private String tempAttPlace;
    private String adjustStartTime;
    private String adjustEndTime;


    public String getAdjustStartTime() {
        return adjustStartTime;
    }

    public void setAdjustStartTime(String adjustStartTime) {
        this.adjustStartTime = adjustStartTime;
    }

    public String getAdjustEndTime() {
        return adjustEndTime;
    }

    public void setAdjustEndTime(String adjustEndTime) {
        this.adjustEndTime = adjustEndTime;
    }

    public String getTempAttPlace() {
        return tempAttPlace;
    }

    public void setTempAttPlace(String tempAttPlace) {
        this.tempAttPlace = tempAttPlace;
    }

    public String getRealCheckInTime() {
        return realCheckInTime;
    }

    public void setRealCheckInTime(String realCheckInTime) {
        this.realCheckInTime = realCheckInTime;
    }

    public String getRealCheckOutTime() {
        return realCheckOutTime;
    }

    public void setRealCheckOutTime(String realCheckOutTime) {
        this.realCheckOutTime = realCheckOutTime;
    }

    public String getEffectiveCheckInTime() {
        return effectiveCheckInTime;
    }

    public void setEffectiveCheckInTime(String effectiveCheckInTime) {
        this.effectiveCheckInTime = effectiveCheckInTime;
    }

    public String getEffectiveCheckOutTime() {
        return effectiveCheckOutTime;
    }

    public void setEffectiveCheckOutTime(String effectiveCheckOutTime) {
        this.effectiveCheckOutTime = effectiveCheckOutTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTempAttId() {
        return tempAttId;
    }

    public void setTempAttId(String tempAttId) {
        this.tempAttId = tempAttId;
    }

    public String getTempAttPersonId() {
        return tempAttPersonId;
    }

    public void setTempAttPersonId(String tempAttPersonId) {
        this.tempAttPersonId = tempAttPersonId;
    }

    public String getTempAttName() {
        return tempAttName;
    }

    public void setTempAttName(String tempAttName) {
        this.tempAttName = tempAttName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public String getCheckInStartTime() {
        return checkInStartTime;
    }

    public void setCheckInStartTime(String checkInStartTime) {
        this.checkInStartTime = checkInStartTime;
    }

    public String getCheckInEndTime() {
        return checkInEndTime;
    }

    public void setCheckInEndTime(String checkInEndTime) {
        this.checkInEndTime = checkInEndTime;
    }

    public String getCheckOutStartTime() {
        return checkOutStartTime;
    }

    public void setCheckOutStartTime(String checkOutStartTime) {
        this.checkOutStartTime = checkOutStartTime;
    }

    public String getCheckOutEndTime() {
        return checkOutEndTime;
    }

    public void setCheckOutEndTime(String checkOutEndTime) {
        this.checkOutEndTime = checkOutEndTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTempAttDate() {
        return tempAttDate;
    }

    public void setTempAttDate(String tempAttDate) {
        this.tempAttDate = tempAttDate;
    }
}