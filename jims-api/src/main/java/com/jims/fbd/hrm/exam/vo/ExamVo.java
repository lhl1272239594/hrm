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
public class ExamVo<T> extends DataEntity<ExamVo> {
    private static final long serialVersionUID = 1L;
    public ExamVo() {
    }

    private String examName;            //试卷名称
    private String examId;              //试卷ID
    private String orgId;               //组织机构ID
    private String itemId;              //试题分类ID
    private String methodId;            //出题方式ID
    private String methodName;          //出题方式名称
    private String typeId;              //试卷类型ID
    private String typeName;            //试卷类型名称
    private String state;               //启用状态（停用0，启用1）
    private String byHand;              //是否手工评卷（0否，1是）
    private String score;               //总分
    private String checkScore;          //及格分数
    private String selectScore;          //已选分数
    private String time;                //考试时长
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
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

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSelectScore() {
        return selectScore;
    }

    public void setSelectScore(String selectScore) {
        this.selectScore = selectScore;
    }
}