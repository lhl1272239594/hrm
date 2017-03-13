/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import com.jims.fbd.hrm.attendance.entity.Festival;

import java.io.Serializable;
import java.util.List;

/**
 *法定节日管理vo
 * @author
 * @version 2016-09-21
 */
public class FestivalVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public FestivalVo() {
    }




    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

    private String sum_days;//总天数
    private String festival_id;        //id
    private String festival_date_id;
    private String year_id;     //年份
    private String festival_description_id;            //节日描述id
    private String festival_date;              //节日日期
    private String festival_type;              //节日类型：工作、休息
    private String create_by;              //
    private String create_date;              //
    private String update_by;              //
    private String update_date;              //
    private String del_flag;              //
    private String create_dept;            //
    private String create_org;            //
    private String status_type;
    private List<Festival> festivals;


    private String sumDays;//总天数
    private String fesId;        //id
    private String yearId;     //年份
    private String fesDesId;            //节日描述id
    private String fesDate;              //节日日期
    private String fesDateType;              //节日类型：工作、休息
    private String delFlag;              //
    private String createDept;            //
    private String createOrg;            //
    private String statusType;
    private String orgId;
    private String fesDateId;
    private String flag;

    private String startDate;
    private String endDate;


    public FestivalVo(List<T> inserted, List<T> updated, List<T> deleted, String sum_days, String festival_id, String festival_date_id, String year_id, String festival_description_id, String festival_date, String festival_type, String create_by, String create_date, String update_by, String update_date, String del_flag, String create_dept, String create_org, String status_type, String sumDays, String fesId, String yearId, String fesDesId, String fesDate, String fesDateType, String delFlag, String createDept, String createOrg, String statusType, String orgId, String fesDateId, String flag, String startDate, String endDate) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.sum_days = sum_days;
        this.festival_id = festival_id;
        this.festival_date_id = festival_date_id;
        this.year_id = year_id;
        this.festival_description_id = festival_description_id;
        this.festival_date = festival_date;
        this.festival_type = festival_type;
        this.create_by = create_by;
        this.create_date = create_date;
        this.update_by = update_by;
        this.update_date = update_date;
        this.del_flag = del_flag;
        this.create_dept = create_dept;
        this.create_org = create_org;
        this.status_type = status_type;
        this.sumDays = sumDays;
        this.fesId = fesId;
        this.yearId = yearId;
        this.fesDesId = fesDesId;
        this.fesDate = fesDate;
        this.fesDateType = fesDateType;
        this.delFlag = delFlag;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.statusType = statusType;
        this.orgId = orgId;
        this.fesDateId = fesDateId;
        this.flag = flag;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getSum_days() {
        return sum_days;
    }

    public void setSum_days(String sum_days) {
        this.sum_days = sum_days;
    }

    public String getFestival_id() {
        return festival_id;
    }

    public void setFestival_id(String festival_id) {
        this.festival_id = festival_id;
    }

    public String getFestival_date_id() {
        return festival_date_id;
    }

    public void setFestival_date_id(String festival_date_id) {
        this.festival_date_id = festival_date_id;
    }

    public String getYear_id() {
        return year_id;
    }

    public void setYear_id(String year_id) {
        this.year_id = year_id;
    }

    public String getFestival_description_id() {
        return festival_description_id;
    }

    public void setFestival_description_id(String festival_description_id) {
        this.festival_description_id = festival_description_id;
    }

    public String getFestival_date() {
        return festival_date;
    }

    public void setFestival_date(String festival_date) {
        this.festival_date = festival_date;
    }

    public String getFestival_type() {
        return festival_type;
    }

    public void setFestival_type(String festival_type) {
        this.festival_type = festival_type;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getCreate_dept() {
        return create_dept;
    }

    public void setCreate_dept(String create_dept) {
        this.create_dept = create_dept;
    }

    public String getCreate_org() {
        return create_org;
    }

    public void setCreate_org(String create_org) {
        this.create_org = create_org;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    public String getSumDays() {
        return sumDays;
    }

    public void setSumDays(String sumDays) {
        this.sumDays = sumDays;
    }

    public String getFesId() {
        return fesId;
    }

    public void setFesId(String fesId) {
        this.fesId = fesId;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getFesDesId() {
        return fesDesId;
    }

    public void setFesDesId(String fesDesId) {
        this.fesDesId = fesDesId;
    }

    public String getFesDate() {
        return fesDate;
    }

    public void setFesDate(String fesDate) {
        this.fesDate = fesDate;
    }

    public String getFesDateType() {
        return fesDateType;
    }

    public void setFesDateType(String fesDateType) {
        this.fesDateType = fesDateType;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getFesDateId() {
        return fesDateId;
    }

    public void setFesDateId(String fesDateId) {
        this.fesDateId = fesDateId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public List<Festival> getFestivals() {
        return festivals;
    }

    public void setFestivals(List<Festival> festivals) {
        this.festivals = festivals;
    }
}