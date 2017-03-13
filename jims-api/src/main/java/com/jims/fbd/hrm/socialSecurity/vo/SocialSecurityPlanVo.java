/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.socialSecurity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class SocialSecurityPlanVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public SocialSecurityPlanVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目
    private String ssPlanId;
    private String ssDetailId;
    private String ssPlanDes;
    private String companyPay;
    private String personPay;
    private String ssItemId;
    private String userId;//
    private String num;
    private String flag;
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String userName;
    private String deptId;

    public SocialSecurityPlanVo(List<T> inserted, List<T> updated, List<T> deleted, String ssPlanId, String ssDetailId, String ssPlanDes, String companyPay, String personPay, String ssItemId, String userId, String num, String flag, String createDept, String createOrg, String orgId, String createRole, String statusType, String userName, String deptId) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.ssPlanId = ssPlanId;
        this.ssDetailId = ssDetailId;
        this.ssPlanDes = ssPlanDes;
        this.companyPay = companyPay;
        this.personPay = personPay;
        this.ssItemId = ssItemId;
        this.userId = userId;
        this.num = num;
        this.flag = flag;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.orgId = orgId;
        this.createRole = createRole;
        this.statusType = statusType;
        this.userName = userName;
        this.deptId = deptId;
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