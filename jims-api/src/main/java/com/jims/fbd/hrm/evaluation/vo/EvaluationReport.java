/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;

public class EvaluationReport extends DataEntity<EvaluationReport> {

    private String name;                //模板名称
    private String reportDate;          //报表日期
    private String avg;                 //考评均值
    private String selfAvg;             //自评均值
    private String state;             //1未发布，2已发布

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}