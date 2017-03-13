/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import java.io.Serializable;
import java.util.List;

/**
 *公出管理vo
 * @author
 * @version 2016-09-21
 */
public class TripWorkVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public TripWorkVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目
    private String tripWorkId;
    private String startDate;
    private String endDate;
    private String userId;//
    private String sumDays;              //
    private String approveStatus;            //
    private String approvePersonId;            //
    private String tripWorkDestination;
    private String tripWorkPlace;
    private String tripWorkReason;
    private String num;
    private String flag;
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String userName;
    private String deptId;

    public TripWorkVo(List<T> inserted, List<T> updated, List<T> deleted, String tripWorkId, String startDate, String endDate, String userId, String sumDays, String approveStatus, String approvePersonId, String tripWorkDestination, String tripWorkPlace, String tripWorkReason, String num, String flag, String createDept, String createOrg, String orgId, String createRole, String statusType, String userName, String deptId) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.tripWorkId = tripWorkId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.sumDays = sumDays;
        this.approveStatus = approveStatus;
        this.approvePersonId = approvePersonId;
        this.tripWorkDestination = tripWorkDestination;
        this.tripWorkPlace = tripWorkPlace;
        this.tripWorkReason = tripWorkReason;
        this.num = num;
        this.flag = flag;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.orgId = orgId;
        this.createRole = createRole;
        this.statusType = statusType;
        this.userName = userName;
        this.deptId = deptId;
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

    public String getTripWorkId() {
        return tripWorkId;
    }

    public void setTripWorkId(String tripWorkId) {
        this.tripWorkId = tripWorkId;
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

    public String getTripWorkDestination() {
        return tripWorkDestination;
    }

    public void setTripWorkDestination(String tripWorkDestination) {
        this.tripWorkDestination = tripWorkDestination;
    }

    public String getTripWorkPlace() {
        return tripWorkPlace;
    }

    public void setTripWorkPlace(String tripWorkPlace) {
        this.tripWorkPlace = tripWorkPlace;
    }

    public String getTripWorkReason() {
        return tripWorkReason;
    }

    public void setTripWorkReason(String tripWorkReason) {
        this.tripWorkReason = tripWorkReason;
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
}