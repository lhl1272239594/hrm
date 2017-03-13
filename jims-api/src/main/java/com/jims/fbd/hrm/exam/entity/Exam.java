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
public class Exam extends DataEntity<Exam> {

    private static final long serialVersionUID = 1L;
    private String examName;            //试卷名称
    private String examId;              //试卷ID
    private String orgId;               //组织机构ID
    private String itemId;               //试题分类ID
    private String methodId;            //出题方式ID
    private String typeId;              //试卷类型ID
    private String info;                //试题说明
    private String state;               //启用状态（停用0，启用1）
    private String byHand;              //是否手工评卷（0否，1是）
    private String score;               //总分
    private String checkScore;          //及格分数
    private String time;                //考试时长

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public Exam(String examName, String examId, String orgId, String methodId, String typeId, String info, String state, String byHand, String score, String checkScore, String time) {
        this.examName = examName;
        this.examId = examId;
        this.orgId = orgId;
        this.methodId = methodId;
        this.typeId = typeId;
        this.info = info;
        this.state = state;
        this.byHand = byHand;
        this.score = score;
        this.checkScore = checkScore;
        this.time = time;
    }

    public Exam() {
    }
}