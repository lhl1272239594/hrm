/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.socialSecurity.vo;

import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsurePerson;

import java.io.Serializable;
import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class SocialSecurityInsureVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public SocialSecurityInsureVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目
    private String ssInsureId;
    private String ssPlanId;
    private String ssInsureTypeId;
    private String userId;//
    private String deptId;
    private String orgId;
    private String num;
    private String flag;
    private String userName;
    private List<SocialSecurityInsurePerson> socialSecurityInsurePerson;


    public SocialSecurityInsureVo(List<T> inserted, List<T> updated, List<T> deleted, String ssInsureId, String ssPlanId, String ssInsureTypeId, String userId, String deptId, String orgId, String num, String flag, String userName, List<SocialSecurityInsurePerson> socialSecurityInsurePerson) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.ssInsureId = ssInsureId;
        this.ssPlanId = ssPlanId;
        this.ssInsureTypeId = ssInsureTypeId;
        this.userId = userId;
        this.deptId = deptId;
        this.orgId = orgId;
        this.num = num;
        this.flag = flag;
        this.userName = userName;
        this.socialSecurityInsurePerson = socialSecurityInsurePerson;
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

    public List<SocialSecurityInsurePerson> getSocialSecurityInsurePerson() {
        return socialSecurityInsurePerson;
    }

    public void setSocialSecurityInsurePerson(List<SocialSecurityInsurePerson> socialSecurityInsurePerson) {
        this.socialSecurityInsurePerson = socialSecurityInsurePerson;
    }
}