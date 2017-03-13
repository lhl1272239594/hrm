/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import java.io.Serializable;
import java.util.List;

/**
 *假日管理vo
 * @author
 * @version 2016-09-21
 */
public class HolidayVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public HolidayVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

    private String holId;        //id
    private String holDes;     //年份
    private String holDate;              //日期
    private String holPayType;              //节日类型：工作、休息
    private String sumDays;              //
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String yearDes;
    private String yearId;
    private String statusType;
    private String num;
    private String holDesId;
    private String holPayTypeId;
    private String flag;

    public HolidayVo(List<T> inserted, List<T> updated, List<T> deleted, String holId, String holDes, String holDate, String holPayType, String sumDays, String createDept, String createOrg, String orgId, String yearDes, String yearId, String statusType, String num, String holDesId, String holPayTypeId, String flag) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.holId = holId;
        this.holDes = holDes;
        this.holDate = holDate;
        this.holPayType = holPayType;
        this.sumDays = sumDays;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.orgId = orgId;
        this.yearDes = yearDes;
        this.yearId = yearId;
        this.statusType = statusType;
        this.num = num;
        this.holDesId = holDesId;
        this.holPayTypeId = holPayTypeId;
        this.flag = flag;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<T> getInserted() {
        return inserted;
    }

    public void setInserted(List<T> inserted) {
        this.inserted = inserted;
    }

    public List<T> getUpdated() {
        return updated;
    }

    public void setUpdated(List<T> updated) {
        this.updated = updated;
    }

    public List<T> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<T> deleted) {
        this.deleted = deleted;
    }

    public String getHolId() {
        return holId;
    }

    public void setHolId(String holId) {
        this.holId = holId;
    }

    public String getHolDes() {
        return holDes;
    }

    public void setHolDes(String holDes) {
        this.holDes = holDes;
    }

    public String getHolDate() {
        return holDate;
    }

    public void setHolDate(String holDate) {
        this.holDate = holDate;
    }

    public String getHolPayType() {
        return holPayType;
    }

    public void setHolPayType(String holPayType) {
        this.holPayType = holPayType;
    }

    public String getSumDays() {
        return sumDays;
    }

    public void setSumDays(String sumDays) {
        this.sumDays = sumDays;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateOrg() {
        return createOrg;
    }

    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getYearDes() {
        return yearDes;
    }

    public void setYearDes(String yearDes) {
        this.yearDes = yearDes;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getHolDesId() {
        return holDesId;
    }

    public void setHolDesId(String holDesId) {
        this.holDesId = holDesId;
    }

    public String getHolPayTypeId() {
        return holPayTypeId;
    }

    public void setHolPayTypeId(String holPayTypeId) {
        this.holPayTypeId = holPayTypeId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}