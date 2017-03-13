/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.train.entity;

import com.jims.common.persistence.DataEntity;

/**
 *培训计划管理Entity
 * @author
 * @version 2016-09-21
 */
public class TrainPlan extends DataEntity<TrainPlan> {

    private static final long serialVersionUID = 1L;


    private String trainPlanId;
    private String trainPlanTittle;
    private byte[] trainPlanContent;
    private String trainPlanType;
    private String userId;//
    private String deptId;
    private String orgId;
    private String num;
    private String flag;
    private String userName;
    private String statusType;
    private String trainTeacher;
    private String value;
    private String label;


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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTrainPlanId() {
        return trainPlanId;
    }

    public void setTrainPlanId(String trainPlanId) {
        this.trainPlanId = trainPlanId;
    }

    public String getTrainPlanTittle() {
        return trainPlanTittle;
    }

    public void setTrainPlanTittle(String trainPlanTittle) {
        this.trainPlanTittle = trainPlanTittle;
    }

    public byte[] getTrainPlanContent() {
        return trainPlanContent;
    }

    public void setTrainPlanContent(byte[] trainPlanContent) {
        this.trainPlanContent = trainPlanContent;
    }

    public String getTrainPlanType() {
        return trainPlanType;
    }

    public void setTrainPlanType(String trainPlanType) {
        this.trainPlanType = trainPlanType;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getTrainTeacher() {
        return trainTeacher;
    }

    public void setTrainTeacher(String trainTeacher) {
        this.trainTeacher = trainTeacher;
    }
}