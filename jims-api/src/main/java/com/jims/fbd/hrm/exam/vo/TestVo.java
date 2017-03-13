/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class TestVo<T> extends DataEntity<TestVo> {
    public TestVo() {
    }

    private static final long serialVersionUID = 1L;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private String planName;           //计划名称
    private String planId;             //计划ID
    private String examId;             //试卷ID
    private String examName;           //试卷名称
    private String typeId;             //考试类型ID
    private String typeName;           //考试类型ID
    private String userId;             //考生ID
    private String userName;           //考试姓名
    private String orgId;              //组织机构ID
    private Date start;              //开始时间
    private Date end;                //结束时间
    private String state;              //状态（编辑0，发布1）
    private String info;              //说明
    private String byHand;            //是否人工判卷（0否，1是）
    private String score;              //总分
    private String checkScore;         //及格分数
    private Date startTime;          //开始考试时间
    private Date endTime;            //结束考试时间
    private String time;                //考试时间
    private String auth;               //授权状态（未授权0，已授权1）
    private String limitStart;         //考试截止时间
    private String limitSubmit;        //交卷限制时间
    private String scoreId;        //考试成绩头表ID

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getByHand() {
        return byHand;
    }

    public void setByHand(String byHand) {
        this.byHand = byHand;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCheckScore() {
        return checkScore;
    }

    public void setCheckScore(String checkScore) {
        this.checkScore = checkScore;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(String limitStart) {
        this.limitStart = limitStart;
    }

    public String getLimitSubmit() {
        return limitSubmit;
    }

    public void setLimitSubmit(String limitSubmit) {
        this.limitSubmit = limitSubmit;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }
}