/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.train.entity;

import com.jims.common.persistence.DataEntity;

import java.util.List;

/**
 *培训通知管理Entity
 * @author
 * @version 2016-09-21
 */
public class TrainNotice extends DataEntity<TrainNotice> {

    private static final long serialVersionUID = 1L;


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
    private String ids;
    private String trainDate;
    private String trainNoticeToPersonId;
    private String trainTeacher;
    private String state;
    private String name;

    public String getTrainTeacher() {
        return trainTeacher;
    }

    public void setTrainTeacher(String trainTeacher) {
        this.trainTeacher = trainTeacher;
    }

    private List<TrainNoticePerson> trainNoticePerson;

    public String getTrainNoticeToPersonId() {
        return trainNoticeToPersonId;
    }

    public void setTrainNoticeToPersonId(String trainNoticeToPersonId) {
        this.trainNoticeToPersonId = trainNoticeToPersonId;
    }




    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
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

    public String getTrainPlanTittle() {
        return trainPlanTittle;
    }

    public void setTrainPlanTittle(String trainPlanTittle) {
        this.trainPlanTittle = trainPlanTittle;
    }

    public List<TrainNoticePerson> getTrainNoticePerson() {
        return trainNoticePerson;
    }

    public void setTrainNoticePerson(List<TrainNoticePerson> trainNoticePerson) {
        this.trainNoticePerson = trainNoticePerson;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}