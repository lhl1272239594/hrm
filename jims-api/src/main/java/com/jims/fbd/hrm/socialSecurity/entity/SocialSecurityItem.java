/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.socialSecurity.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class SocialSecurityItem extends DataEntity<SocialSecurityItem> {

    private static final long serialVersionUID = 1L;


    private String ssItemId;
    private String ssItemDes;
    private String ssItemTypeId;
    private String userId;//
    private String num;
    private String flag;

    public String getCreateDeptname() {
        return createDeptname;
    }

    public void setCreateDeptname(String createDeptname) {
        this.createDeptname = createDeptname;
    }

    private String createDeptname;            //
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String userName;
    private String deptId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSsItemId() {
        return ssItemId;
    }

    public void setSsItemId(String ssItemId) {
        this.ssItemId = ssItemId;
    }

    public String getSsItemDes() {
        return ssItemDes;
    }

    public void setSsItemDes(String ssItemDes) {
        this.ssItemDes = ssItemDes;
    }

    public String getSsItemTypeId() {
        return ssItemTypeId;
    }

    public void setSsItemTypeId(String ssItemTypeId) {
        this.ssItemTypeId = ssItemTypeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getCreateRole() {
        return createRole;
    }

    public void setCreateRole(String createRole) {
        this.createRole = createRole;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}