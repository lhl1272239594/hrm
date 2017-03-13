/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *排班记录管理Entity
 * @author
 * @version 2016-09-21
 */
public class FrequencyRecord extends DataEntity<FrequencyRecord> {

    private static final long serialVersionUID = 1L;


    private String freRecordId;        //id
    private String freRecordHeadId;    //头表ID

    public String getFreRecordHeadId() {
        return freRecordHeadId;
    }

    public void setFreRecordHeadId(String freRecordHeadId) {
        this.freRecordHeadId = freRecordHeadId;
    }

    private String yearId;
    private String deptId;
    private String freDate;
    private String freRuleId;
    private String freRuleDes;
    private String freItemId;
    private String freItemDes;
    private String freItemOrder;
    private String userId;
    private String userName;
    private String userCode;
    private String week;
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String num;
    private String flag;
    private String startDate;
    private String endDate;
    private String sumDays;
    private String id;
    private String freFirstItemOrder;
    private String freTimeMonth;
    private String freTimeDay;
    private String freTimeType;
    private String createDeptname;
    private String personName;

    public String getCreateDeptname() {
        return createDeptname;
    }

    public void setCreateDeptname(String createDeptname) {
        this.createDeptname = createDeptname;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getFreTimeMonth() {
        return freTimeMonth;
    }

    public void setFreTimeMonth(String freTimeMonth) {
        this.freTimeMonth = freTimeMonth;
    }

    public String getFreTimeDay() {
        return freTimeDay;
    }

    public void setFreTimeDay(String freTimeDay) {
        this.freTimeDay = freTimeDay;
    }

    public String getFreTimeType() {
        return freTimeType;
    }

    public void setFreTimeType(String freTimeType) {
        this.freTimeType = freTimeType;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getFreFirstItemOrder() {
        return freFirstItemOrder;
    }

    public void setFreFirstItemOrder(String freFirstItemOrder) {
        this.freFirstItemOrder = freFirstItemOrder;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFreRecordId() {
        return freRecordId;
    }

    public void setFreRecordId(String freRecordId) {
        this.freRecordId = freRecordId;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getFreDate() {
        return freDate;
    }

    public void setFreDate(String freDate) {
        this.freDate = freDate;
    }

    public String getFreRuleId() {
        return freRuleId;
    }

    public void setFreRuleId(String freRuleId) {
        this.freRuleId = freRuleId;
    }

    public String getFreItemId() {
        return freItemId;
    }

    public void setFreItemId(String freItemId) {
        this.freItemId = freItemId;
    }

    public String getFreItemOrder() {
        return freItemOrder;
    }

    public void setFreItemOrder(String freItemOrder) {
        this.freItemOrder = freItemOrder;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    public String getSumDays() {
        return sumDays;
    }

    public void setSumDays(String sumDays) {
        this.sumDays = sumDays;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}