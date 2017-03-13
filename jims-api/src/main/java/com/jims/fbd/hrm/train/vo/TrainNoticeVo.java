/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.train.vo;

import com.jims.fbd.hrm.train.entity.TrainNoticePerson;

import java.io.Serializable;
import java.util.List;

/**
 *培训通知管理vo
 * @author
 * @version 2016-09-21
 */
public class TrainNoticeVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public TrainNoticeVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目
    private String trainNoticeId;
    private String trainPlanId;
    private String trainReceivePerson;
    private String trainPlanType;
    private byte[] trainPlanContent;
    private String trainPlanTittle;
    private String trainPlace;
    private String startDate;
    private String endDate;
    private String userId;//
    private String deptId;
    private String orgId;
    private String num;
    private String flag;
    private String userName;
    private String trainTeacher;
    private String name;
    private String type;



    private List<TrainNoticePerson> trainNoticePerson;


    public TrainNoticeVo(List<T> inserted, List<T> updated, List<T> deleted, String trainNoticeId, String trainPlanId, String trainReceivePerson, String trainPlanType, byte[] trainPlanContent, String trainPlanTittle, String trainPlace, String startDate, String endDate, String userId, String deptId, String orgId, String num, String flag, String userName, String trainTeacher, List<TrainNoticePerson> trainNoticePerson) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.trainNoticeId = trainNoticeId;
        this.trainPlanId = trainPlanId;
        this.trainReceivePerson = trainReceivePerson;
        this.trainPlanType = trainPlanType;
        this.trainPlanContent = trainPlanContent;
        this.trainPlanTittle = trainPlanTittle;
        this.trainPlace = trainPlace;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.deptId = deptId;
        this.orgId = orgId;
        this.num = num;
        this.flag = flag;
        this.userName = userName;
        this.trainTeacher = trainTeacher;
        this.trainNoticePerson = trainNoticePerson;
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

    public String getTrainNoticeId() {
        return trainNoticeId;
    }

    public void setTrainNoticeId(String trainNoticeId) {
        this.trainNoticeId = trainNoticeId;
    }

    public String getTrainPlanId() {
        return trainPlanId;
    }

    public void setTrainPlanId(String trainPlanId) {
        this.trainPlanId = trainPlanId;
    }

    public String getTrainReceivePerson() {
        return trainReceivePerson;
    }

    public void setTrainReceivePerson(String trainReceivePerson) {
        this.trainReceivePerson = trainReceivePerson;
    }

    public String getTrainPlanType() {
        return trainPlanType;
    }

    public void setTrainPlanType(String trainPlanType) {
        this.trainPlanType = trainPlanType;
    }

    public byte[] getTrainPlanContent() {
        return trainPlanContent;
    }

    public void setTrainPlanContent(byte[] trainPlanContent) {
        this.trainPlanContent = trainPlanContent;
    }

    public String getTrainPlanTittle() {
        return trainPlanTittle;
    }

    public void setTrainPlanTittle(String trainPlanTittle) {
        this.trainPlanTittle = trainPlanTittle;
    }

    public String getTrainPlace() {
        return trainPlace;
    }

    public void setTrainPlace(String trainPlace) {
        this.trainPlace = trainPlace;
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

    public List<TrainNoticePerson> getTrainNoticePerson() {
        return trainNoticePerson;
    }

    public void setTrainNoticePerson(List<TrainNoticePerson> trainNoticePerson) {
        this.trainNoticePerson = trainNoticePerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}