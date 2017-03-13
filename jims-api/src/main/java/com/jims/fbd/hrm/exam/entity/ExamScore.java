/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.entity;

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
public class ExamScore extends DataEntity<ExamScore> {

    private static final long serialVersionUID = 1L;
    private String examId;              //试卷ID
    private String examName;            //试卷名称
    private String orgId;               //组织机构ID
    private String planId;              //考试计划ID
    private String userId;              //考生ID
    private String userName;            //考生姓名
    private String time;                //考试时间
    private String startTime;           //开始考试时间
    private Date endTime;             //结束考试时间
    private String score;               //得分
    private String state;               //状态（0未交卷，1等待手工判卷，2合格，3不合格）
    private Date gradeTime;           //评卷时间
    private String gradeBy;           //评卷人ID
    private String gradeName;           //评卷人
    private String socreId;           //评卷人
    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getGradeTime() {
        return gradeTime;
    }

    public void setGradeTime(Date gradeTime) {
        this.gradeTime = gradeTime;
    }

    public String getGradeBy() {
        return gradeBy;
    }

    public void setGradeBy(String gradeBy) {
        this.gradeBy = gradeBy;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getSocreId() {
        return socreId;
    }

    public void setSocreId(String socreId) {
        this.socreId = socreId;
    }

    public ExamScore() {
    }
}