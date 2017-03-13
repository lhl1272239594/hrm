/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 考勤综合查询vo
 * @author
 * @version 2016-09-21
 */
public class AttendanceDataVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public AttendanceDataVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

    private String attDataId;        //id
    private String attDate;
    private String checkTime;        //id
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


    public AttendanceDataVo(List<T> inserted, List<T> updated, List<T> deleted, String attDataId, String attDate, String checkTime, String userId, String userName, String deptId, String deptName, String userCode, String orgId, String statusType, String num, String flag, String deptCode, String checkDate, String fileName, String fileUrl) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.attDataId = attDataId;
        this.attDate = attDate;
        this.checkTime = checkTime;
        this.userId = userId;
        this.userName = userName;
        this.deptId = deptId;
        this.deptName = deptName;
        this.userCode = userCode;
        this.orgId = orgId;
        this.statusType = statusType;
        this.num = num;
        this.flag = flag;
        this.deptCode = deptCode;
        this.checkDate = checkDate;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
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

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
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
}