/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.vo;

import com.jims.common.persistence.DataEntity;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class ExamQuestionTypeVo<T> extends DataEntity<ExamQuestionTypeVo> {
    private static final long serialVersionUID = 1L;
    public ExamQuestionTypeVo() {
    }

    private String examQueId;         //考试题型ID
    private String orgId;             //组织机构ID
    private String examId;            //试卷ID
    private String typeId;            //题型ID
    private String typeName;          //题型名称
    private String score;             //每题分数
    private String num;               //题目数量
    private String checkedNum;        //已选题目数量
    private String methodId;            //出题方式ID
    private String methodName;          //出题方式名称
    private String totalScore;          //试卷总分

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
}