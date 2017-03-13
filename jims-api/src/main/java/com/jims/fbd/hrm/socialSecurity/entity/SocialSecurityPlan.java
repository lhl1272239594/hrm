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
public class SocialSecurityPlan extends DataEntity<SocialSecurityPlan> {

    private static final long serialVersionUID = 1L;


    private String ssPlanId;
    private String ssDetailId;
    private String ssPlanDes;
    private String ssItemDes;
    private String companyPay;
    private String personPay;
    private String ssItemId;
    private String userId;//
    private String num;
    private String flag;     //
    private String orgId;
    private String statusType;
    private String userName;
    private String deptId;
    private String value;
    private String label;
    private String createDeptname;
    private String createOrg;

    public String getCreateDeptname() {
        return createDeptname;
    }

    public void setCreateDeptname(String createDeptname) {
        this.createDeptname = createDeptname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSsItemDes() {
        return ssItemDes;
    }

    public void setSsItemDes(String ssItemDes) {
        this.ssItemDes = ssItemDes;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSsPlanId() {
        return ssPlanId;
    }

    public void setSsPlanId(String ssPlanId) {
        this.ssPlanId = ssPlanId;
    }

    public String getSsDetailId() {
        return ssDetailId;
    }

    public void setSsDetailId(String ssDetailId) {
        this.ssDetailId = ssDetailId;
    }

    public String getSsPlanDes() {
        return ssPlanDes;
    }

    public void setSsPlanDes(String ssPlanDes) {
        this.ssPlanDes = ssPlanDes;
    }

    public String getCompanyPay() {
        return companyPay;
    }

    public void setCompanyPay(String companyPay) {
        this.companyPay = companyPay;
    }

    public String getPersonPay() {
        return personPay;
    }

    public void setPersonPay(String personPay) {
        this.personPay = personPay;
    }

    public String getSsItemId() {
        return ssItemId;
    }

    public void setSsItemId(String ssItemId) {
        this.ssItemId = ssItemId;
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