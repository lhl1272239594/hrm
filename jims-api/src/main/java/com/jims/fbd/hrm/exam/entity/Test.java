/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.entity;

import com.jims.fbd.hrm.exam.vo.ExamGradeVo;

import java.io.Serializable;
import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class Test implements Serializable {

    private String id;                  //考试成绩主表ID
    private String code;                //状态
    private String data;                //结果
    private int time;                   //剩余时间
    private int num;                   //未答题数量
    private int score;                   //考试成绩
    private String state;                //状态（0未交卷，1等待手工判卷，2合格，3不合格）
    private String stateName;            //结果（及格、不及格）
    private String scoreId;            //头表ID
    private String examId;            //试卷ID
    private String planId;            //计划ID
    private String userId;            //人员ID

    public List<ExamDetail> getExamDetails() {
        return examDetails;
    }

    public void setExamDetails(List<ExamDetail> examDetails) {
        this.examDetails = examDetails;
    }

    private List<ExamDetail> examDetails;                   //剩余时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
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

    public Test() {
    }
}