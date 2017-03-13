/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 工作组类字典Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class StaffGroupClassDict extends DataEntity<StaffGroupClassDict> {

    private static final long serialVersionUID = 1L;
    private String orgId;        //组织机构id
    private String groupClass;        // GROUP_CLASS  组名

    public StaffGroupClassDict() {
    }

    public StaffGroupClassDict(String orgId, String groupClass) {
        this.orgId = orgId;
        this.groupClass = groupClass;
    }

    public StaffGroupClassDict(String id, String orgId, String groupClass) {
        super(id);
        this.orgId = orgId;
        this.groupClass = groupClass;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(String groupClass) {
        this.groupClass = groupClass;
    }
}