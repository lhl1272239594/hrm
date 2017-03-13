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
public class SocialSecurityInsurePerson extends DataEntity<SocialSecurityInsurePerson> {

    private static final long serialVersionUID = 1L;


    private String ssInsureId;
    private String ssPlanId;
    private String ssInsureTypeId;
    private String userId;//
    private String deptId;
    private String orgId;
    private String num;
    private String flag;
    private String userName;
    private String createDeptname;

    public String getCreateDeptname() {
        return createDeptname;
    }

    public void setCreateDeptname(String createDeptname) {
        this.createDeptname = createDeptname;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSsInsureId() {
        return ssInsureId;
    }

    public void setSsInsureId(String ssInsureId) {
        this.ssInsureId = ssInsureId;
    }

    public String getSsPlanId() {
        return ssPlanId;
    }

    public void setSsPlanId(String ssPlanId) {
        this.ssPlanId = ssPlanId;
    }

    public String getSsInsureTypeId() {
        return ssInsureTypeId;
    }

    public void setSsInsureTypeId(String ssInsureTypeId) {
        this.ssInsureTypeId = ssInsureTypeId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}