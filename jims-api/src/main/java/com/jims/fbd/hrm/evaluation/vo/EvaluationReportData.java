/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;

public class EvaluationReportData extends DataEntity<EvaluationReportData> {

    private String reportId;            //报表ID
    private String systemName;          //科系名称
    private String deptName;            //科室名称
    private String score;               //科室得分
    private String totalScore;          //总分
    private String rate;                //科室达标率
    private String rateAvg;             //科室平均达标率
    private String avg;                 //科系考评均值
    private String selfAvg;             //科系自评均值
    private String type;                //（1为科系头表，2为科室行表）
    private String state;               //（0无考评，1有考评）
    private String pid;                 //科系头ID
    private String obj;                 //1为该科室上月有自评，4为没有
    private String sort;                //排序

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateAvg() {
        return rateAvg;
    }

    public void setRateAvg(String rateAvg) {
        this.rateAvg = rateAvg;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getSelfAvg() {
        return selfAvg;
    }

    public void setSelfAvg(String selfAvg) {
        this.selfAvg = selfAvg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}