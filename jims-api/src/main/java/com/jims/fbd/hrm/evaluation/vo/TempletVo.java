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
public class TempletVo extends DataEntity<TempletVo> {
    public TempletVo() {
    }

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private String name;               //模板名称
    private String typeId;             //考评类型ID
    private String modeId;             //考评方式ID（自评、考评他人、考评科室）
    private String score;              //考评总分
    private String s_state;            //标准分配状态（0未分配、1已分配）
    private String u_state;            //评价人分配状态（0未分配、1已分配）
    private String startType;          //启动类型（1手动，1自动）
    private String expiryDate;         //成绩有效期
    private String startDay;           //启动日期(本周期第几天启动)
    private String period;             //考核周期（如值为1且考核周期为月，则实际考核月份为上月）
    private String periodType;         //考核周期（0月，1季度，2年）
    private Date lastStartDate;        //最后启动日期
    private String orgId;              //机构ID
    private String state;              //启用类型（0停用，1启动）
    private String type;                //操作类型(del,ok,no)
    private String self;                //是否自评（0否，1是）
    private String obj;                //考评对象类型（1部门，2人员）

    private List<PersonVo> personVos;
    private List<PersonVo> dept;
    private List<PersonVo> user;
    private List<PersonVo> grade;
    private List<StandardVo> standard;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getS_state() {
        return s_state;
    }

    public void setS_state(String s_state) {
        this.s_state = s_state;
    }

    public String getU_state() {
        return u_state;
    }

    public void setU_state(String u_state) {
        this.u_state = u_state;
    }

    public String getStartType() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType = startType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getLastStartDate() {
        return lastStartDate;
    }

    public void setLastStartDate(Date lastStartDate) {
        this.lastStartDate = lastStartDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PersonVo> getPersonVos() {
        return personVos;
    }

    public void setPersonVos(List<PersonVo> personVos) {
        this.personVos = personVos;
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

    public List<PersonVo> getDept() {
        return dept;
    }

    public void setDept(List<PersonVo> dept) {
        this.dept = dept;
    }

    public List<PersonVo> getUser() {
        return user;
    }

    public void setUser(List<PersonVo> user) {
        this.user = user;
    }

    public List<PersonVo> getGrade() {
        return grade;
    }

    public void setGrade(List<PersonVo> grade) {
        this.grade = grade;
    }

    public List<StandardVo> getStandard() {
        return standard;
    }

    public void setStandard(List<StandardVo> standard) {
        this.standard = standard;
    }
}