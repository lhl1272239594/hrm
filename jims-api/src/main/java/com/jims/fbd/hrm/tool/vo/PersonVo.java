/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.tool.vo;

import com.jims.common.persistence.DataEntity;

/**
 *
 *

 * @version 2016-08-18
 */
public class PersonVo extends DataEntity<PersonVo> {
    public PersonVo() {
    }

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private String dataId;              //业务ID
    private String name;               //业务名称
    private String depId;             //部门ID
    private String depName;             //部门名称
    private String orgId;              //组织机构ID
    private String userId;              //人员ID
    private String type;              //考核对象类型（1部门2人员3考评人员）
    private String grade;              //（0不是评分人员，1评分人员）

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}