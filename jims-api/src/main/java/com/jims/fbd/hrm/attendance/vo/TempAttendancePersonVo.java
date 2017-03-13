/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import com.jims.fbd.hrm.attendance.entity.TempAttendancePerson;

import java.io.Serializable;
import java.util.List;

/**
 *临时考勤管理vo
 * @author
 * @version 2016-09-21
 */
public class TempAttendancePersonVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public TempAttendancePersonVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

    private String tempAttId;
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
    private String tempAttPlace;
    private String adjustStartTime;
    private String adjustEndTime;


    private List<TempAttendancePerson> tempAttendancePerson;


    public TempAttendancePersonVo(List<T> inserted, List<T> updated, List<T> deleted, String tempAttId, String tempAttName, String userId, String userName, String deptId, String deptName, String userCode, String orgId, String statusType, String num, String flag, String checkInStartTime, String checkInEndTime, String checkOutStartTime, String checkOutEndTime, String startTime, String endTime, String tempAttDate, String tempAttPlace, String adjustStartTime, String adjustEndTime, List<TempAttendancePerson> tempAttendancePerson) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.tempAttId = tempAttId;
        this.tempAttName = tempAttName;
        this.userId = userId;
        this.userName = userName;
        this.deptId = deptId;
        this.deptName = deptName;
        this.userCode = userCode;
        this.orgId = orgId;
        this.statusType = statusType;
        this.num = num;
        this.flag = flag;
        this.checkInStartTime = checkInStartTime;
        this.checkInEndTime = checkInEndTime;
        this.checkOutStartTime = checkOutStartTime;
        this.checkOutEndTime = checkOutEndTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tempAttDate = tempAttDate;
        this.tempAttPlace = tempAttPlace;
        this.adjustStartTime = adjustStartTime;
        this.adjustEndTime = adjustEndTime;
        this.tempAttendancePerson = tempAttendancePerson;
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

    public String getTempAttId() {
        return tempAttId;
    }

    public void setTempAttId(String tempAttId) {
        this.tempAttId = tempAttId;
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

    public String getTempAttPlace() {
        return tempAttPlace;
    }

    public void setTempAttPlace(String tempAttPlace) {
        this.tempAttPlace = tempAttPlace;
    }

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

    public List<TempAttendancePerson> getTempAttendancePerson() {
        return tempAttendancePerson;
    }

    public void setTempAttendancePerson(List<TempAttendancePerson> tempAttendancePerson) {
        this.tempAttendancePerson = tempAttendancePerson;
    }
}