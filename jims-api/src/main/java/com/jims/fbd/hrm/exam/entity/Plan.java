/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.Date;
import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class Plan extends DataEntity<Plan> {

    private static final long serialVersionUID = 1L;
    private String planName;            //计划名称
    private String planId;              //计划ID
    private String examId;              //试卷ID
    private String typeId;              //计划类型ID
    private String orgId;               //组织机构ID
    private Date start;              //开始时间
    private Date end;                //结束时间
    private String state;               //状态（编辑0，发布1）
    private String info;               //考试说明
    private String limitStart;         //考试截止时间
    private String limitSubmit;        //交卷限制时间
    private List<PersonVo> personVos;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

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

    public List<PersonVo> getPersonVos() {
        return personVos;
    }

    public void setPersonVos(List<PersonVo> personVos) {
        this.personVos = personVos;
    }

    public Plan() {
    }
}