/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 考勤综合查询Entity
 * @author
 * @version 2016-09-21
 */
public class AttendanceData extends DataEntity<AttendanceData> {

    private static final long serialVersionUID = 1L;


    private String attDataId;        //id
    private String attDate;
    private String userId;
    private String userName;
    private String deptId;
    private String deptName;
    private String userCode;
    private String orgId;
    private String statusType;
    private String num;
    private String flag;
    private String deptCode;
    private String checkDate;
    private String fileName;
    private String fileUrl;
    private String checkInTime;
    private String checkOutTime;
    private String rowId;
    private String startTime;
    private String endTime;
    private String lateTime;
    private String earlyTime;
    private String freDate;
    private String freRuleDes;
    private String freItemDes;
    private String ruleTime;
    private String checkTime;
    private String nums1;
    private String nums2;
    private String nums3;
    private String nums4;
    private String nums5;
    private String nums6;
    private String nums7;
    private String nums8;
    private String attMonth;

    public String getNums7() {
        return nums7;
    }

    public void setNums7(String nums7) {
        this.nums7 = nums7;
    }

    public String getNums8() {
        return nums8;
    }

    public void setNums8(String nums8) {
        this.nums8 = nums8;
    }

    public String getNums6() {
        return nums6;
    }

    public void setNums6(String nums6) {
        this.nums6 = nums6;
    }

    public String getAttMonth() {
        return attMonth;
    }

    public void setAttMonth(String attMonth) {
        this.attMonth = attMonth;
    }

    public String getNums1() {
        return nums1;
    }

    public void setNums1(String nums1) {
        this.nums1 = nums1;
    }

    public String getNums2() {
        return nums2;
    }

    public void setNums2(String nums2) {
        this.nums2 = nums2;
    }

    public String getNums3() {
        return nums3;
    }

    public void setNums3(String nums3) {
        this.nums3 = nums3;
    }

    public String getNums4() {
        return nums4;
    }

    public void setNums4(String nums4) {
        this.nums4 = nums4;
    }

    public String getNums5() {
        return nums5;
    }

    public void setNums5(String nums5) {
        this.nums5 = nums5;
    }

    public String getRuleTime() {
        return ruleTime;
    }

    public void setRuleTime(String ruleTime) {
        this.ruleTime = ruleTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getFreRuleDes() {
        return freRuleDes;
    }

    public void setFreRuleDes(String freRuleDes) {
        this.freRuleDes = freRuleDes;
    }

    public String getFreItemDes() {
        return freItemDes;
    }

    public void setFreItemDes(String freItemDes) {
        this.freItemDes = freItemDes;
    }

    public String getFreDate() {
        return freDate;
    }

    public void setFreDate(String freDate) {
        this.freDate = freDate;
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

    public String getLateTime() {
        return lateTime;
    }

    public void setLateTime(String lateTime) {
        this.lateTime = lateTime;
    }

    public String getEarlyTime() {
        return earlyTime;
    }

    public void setEarlyTime(String earlyTime) {
        this.earlyTime = earlyTime;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAttDataId() {
        return attDataId;
    }

    public void setAttDataId(String attDataId) {
        this.attDataId = attDataId;
    }

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}