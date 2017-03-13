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
public class ExamQuestionType<T> extends DataEntity<ExamQuestionType> {
    private static final long serialVersionUID = 1L;
    public ExamQuestionType() {
    }

    private String examQueId;         //考试题型ID
    private String orgId;             //组织机构ID
    private String examId;            //试卷ID
    private String typeId;            //题型ID
    private String score;             //每题分数
    private String num;               //题目数量
    private String typeName;          //题型名称
    private String checkedNum;        //已选题目数量
    private String methodId;          //出题方式ID
    private String itemId;            //出题方式ID
    private String totalScore;        //试卷总分

    public String getQueId() {
        return queId;
    }

    public void setQueId(String queId) {
        this.queId = queId;
    }

    private String queId;            //出题方式ID

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getExamQueId() {
        return examQueId;
    }

    public void setExamQueId(String examQueId) {
        this.examQueId = examQueId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(String checkedNum) {
        this.checkedNum = checkedNum;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    private String sort;              //排序

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}