/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.train.vo;

import java.io.Serializable;
import java.util.List;


/**
 *培训计划管理vo
 * @author
 * @version 2016-09-21
 */
public class TrainPlanVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public TrainPlanVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目
    private String trainPlanId;
    private String trainPlanTittle;
    private byte[]  trainPlanContent;
    private String trainPlanType;
    private String userId;//
    private String deptId;
    private String orgId;
    private String num;
    private String flag;
    private String userName;
    private String trainTeacher;


    public TrainPlanVo(List<T> inserted, List<T> updated, List<T> deleted, String trainPlanId, String trainPlanTittle, byte[] trainPlanContent, String trainPlanType, String userId, String deptId, String orgId, String num, String flag, String userName, String trainTeacher) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.trainPlanId = trainPlanId;
        this.trainPlanTittle = trainPlanTittle;
        this.trainPlanContent = trainPlanContent;
        this.trainPlanType = trainPlanType;
        this.userId = userId;
        this.deptId = deptId;
        this.orgId = orgId;
        this.num = num;
        this.flag = flag;
        this.userName = userName;
        this.trainTeacher = trainTeacher;
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

    public String getTrainTeacher() {
        return trainTeacher;
    }

    public void setTrainTeacher(String trainTeacher) {
        this.trainTeacher = trainTeacher;
    }
}