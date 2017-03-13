/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class EvaluationScoreVo extends DataEntity<EvaluationScoreVo> {
    public EvaluationScoreVo() {
    }

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private String planId;             //考评计划ID
    private String planName;           //考评计划名称
    private String typeId;             //考评类型ID
    private String typeName;           //考评类型名称
    private String templetId;          //考评模板ID
    private String userId;             //员工ID
    private String deptId;             //部门ID
    private String orgId;              //机构ID
    private String state;              //状态（0等待发布，1发布）
    private String score;              //得分
    private String self;               //是否自评（0否，1是）
    private String obj;                //考评对象类型（1部门，2人员，3评分人员）
    private Date   submitDate;         //提交时间
    private String gradeByName;        //评分人ID
    private String gradeBy;            //评分人姓名
    private String userName;            //员工姓名
    private String deptName;            //部门名称
    private String totalScore;          //总分
    private String hasGrade;            //已评分数
    private String num;                 //待评分总数
    private String hasGrade1;            //已评分数(标准授权人能看到的数量)
    private String num1;                 //待评分总数(标准授权人能看到的数量)
    private String month;               //考评月份
    private String startDate;            //开始时间
    private String endDate;               //结束时间


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getTempletId() {
        return templetId;
    }

    public void setTempletId(String templetId) {
        this.templetId = templetId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getGradeBy() {
        return gradeBy;
    }

    public void setGradeBy(String gradeBy) {
        this.gradeBy = gradeBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getGradeByName() {
        return gradeByName;
    }

    public void setGradeByName(String gradeByName) {
        this.gradeByName = gradeByName;
    }

    public String getHasGrade() {
        return hasGrade;
    }

    public void setHasGrade(String hasGrade) {
        this.hasGrade = hasGrade;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getHasGrade1() {
        return hasGrade1;
    }

    public void setHasGrade1(String hasGrade1) {
        this.hasGrade1 = hasGrade1;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
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
}