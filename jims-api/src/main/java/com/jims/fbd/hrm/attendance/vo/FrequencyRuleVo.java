/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.vo;

import java.io.Serializable;
import java.util.List;

/**
 *排班规则管理vo
 * @author
 * @version 2016-09-21
 */
public class FrequencyRuleVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public FrequencyRuleVo() {
    }

    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private List<T> deleted ;//更新的项目

    private String freRuleId;        //id
    private String freRuleDes;
    private String freLoopDays;        //id
    private String freRuleDataId;
    private String freItemId;
    private String freItemDes;
    private String freItemOrder;
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String num;
    private String flag;
    private String id;

    public FrequencyRuleVo(List<T> inserted, List<T> updated, List<T> deleted, String freRuleId, String freRuleDes, String freLoopDays, String freRuleDataId, String freItemId, String freItemDes, String freItemOrder, String createDept, String createOrg, String orgId, String createRole, String statusType, String num, String flag, String id) {
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
        this.freRuleId = freRuleId;
        this.freRuleDes = freRuleDes;
        this.freLoopDays = freLoopDays;
        this.freRuleDataId = freRuleDataId;
        this.freItemId = freItemId;
        this.freItemDes = freItemDes;
        this.freItemOrder = freItemOrder;
        this.createDept = createDept;
        this.createOrg = createOrg;
        this.orgId = orgId;
        this.createRole = createRole;
        this.statusType = statusType;
        this.num = num;
        this.flag = flag;
        this.id = id;
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

    public String getFreRuleId() {
        return freRuleId;
    }

    public void setFreRuleId(String freRuleId) {
        this.freRuleId = freRuleId;
    }

    public String getFreRuleDes() {
        return freRuleDes;
    }

    public void setFreRuleDes(String freRuleDes) {
        this.freRuleDes = freRuleDes;
    }

    public String getFreLoopDays() {
        return freLoopDays;
    }

    public void setFreLoopDays(String freLoopDays) {
        this.freLoopDays = freLoopDays;
    }

    public String getFreRuleDataId() {
        return freRuleDataId;
    }

    public void setFreRuleDataId(String freRuleDataId) {
        this.freRuleDataId = freRuleDataId;
    }

    public String getFreItemId() {
        return freItemId;
    }

    public void setFreItemId(String freItemId) {
        this.freItemId = freItemId;
    }

    public String getFreItemDes() {
        return freItemDes;
    }

    public void setFreItemDes(String freItemDes) {
        this.freItemDes = freItemDes;
    }

    public String getFreItemOrder() {
        return freItemOrder;
    }

    public void setFreItemOrder(String freItemOrder) {
        this.freItemOrder = freItemOrder;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}