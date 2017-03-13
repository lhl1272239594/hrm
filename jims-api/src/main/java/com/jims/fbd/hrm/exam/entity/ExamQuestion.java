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
public class ExamQuestion extends DataEntity<ExamQuestion> {

    private static final long serialVersionUID = 1L;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    private String examQueId;        //试卷试题ID
    private String queName;         //试题名称
    private String queId;               //试题ID
    private String examId;               //试卷ID
    private String orgId;            //组织机构ID
    private String itemId;              //分类ID
    private String typeId;              //题型ID
    private String queNum;              //试题选项个数
    private String queContent;              //试题内容
    private String queAnswer;              //试题答案
    private String state;            //启用状态（停用0，启用1）
    private String examQueTypeId;            //试卷题型ID
    private String score;            //分数

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

    public String getQueAnswer() {
        return queAnswer;
    }

    public void setQueAnswer(String queAnswer) {
        this.queAnswer = queAnswer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}