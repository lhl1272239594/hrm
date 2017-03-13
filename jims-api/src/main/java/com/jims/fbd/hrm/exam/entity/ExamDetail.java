/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class ExamDetail extends DataEntity<ExamDetail> {

    private static final long serialVersionUID = 1L;
    private String scoreId;             //考试头表ID
    private String examQueId;        //试卷试题ID
    private String queName;         //试题名称
    private String queId;               //试题ID
    private String examId;               //试卷ID
    private String planId;               //考试计划ID
    private String orgId;            //组织机构ID
    private String itemId;              //分类ID
    private String typeId;              //题型ID
    private String typeName;              //题型名称
    private String queNum;              //试题选项个数
    private String queContent;              //试题内容
    private String answer;              //试题答案
    private String queAnswer;              //试题答案
    private String examQueTypeId;            //试卷题型ID
    private String score;            //分值
    private String resultScore;            //考生得分
    private String sort;            //排序

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getExamQueId() {
        return examQueId;
    }

    public void setExamQueId(String examQueId) {
        this.examQueId = examQueId;
    }

    public String getQueName() {
        return queName;
    }

    public void setQueName(String queName) {
        this.queName = queName;
    }

    public String getQueId() {
        return queId;
    }

    public void setQueId(String queId) {
        this.queId = queId;
    }

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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getQueNum() {
        return queNum;
    }

    public void setQueNum(String queNum) {
        this.queNum = queNum;
    }

    public String getQueContent() {
        return queContent;
    }

    public void setQueContent(String queContent) {
        this.queContent = queContent;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQueAnswer() {
        return queAnswer;
    }

    public void setQueAnswer(String queAnswer) {
        this.queAnswer = queAnswer;
    }


    public String getExamQueTypeId() {
        return examQueTypeId;
    }

    public void setExamQueTypeId(String examQueTypeId) {
        this.examQueTypeId = examQueTypeId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getResultScore() {
        return resultScore;
    }

    public void setResultScore(String resultScore) {
        this.resultScore = resultScore;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public ExamDetail() {
    }
}