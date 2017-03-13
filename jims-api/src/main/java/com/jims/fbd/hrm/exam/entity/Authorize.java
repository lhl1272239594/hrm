/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 考试计划授权
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class Authorize extends DataEntity<Authorize> {

    private static final long serialVersionUID = 1L;
    private String authId;              //考试计划授权ID
    private String orgId;               //组织机构ID
    private String planId;              //计划ID
    private String userId;              //人员ID


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Authorize(String id, String authId, String orgId, String planId, String userId) {

        super(id);
        this.authId = authId;
        this.orgId = orgId;
        this.planId = planId;
        this.userId = userId;
    }

    public Authorize() {
    }
}