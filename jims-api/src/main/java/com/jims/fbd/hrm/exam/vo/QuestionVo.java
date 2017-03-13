/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.exam.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.vo.OrgStaffVo;

import java.io.Serializable;
import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class QuestionVo<T> extends DataEntity<QuestionVo> {
    private static final long serialVersionUID = 1L;
    public QuestionVo() {
    }
    private String queName;        //试题名称
    private String queId;     //试题ID
    private String orgId;            //组织机构ID
    private String itemId;              //分类ID
    private String itemName;              //分类名称
    private String typeId;              //题型ID
    private String typeName;              //题型名称
    private String queNum;              //试题选项个数
    private String queContent;              //试题内容
    private String queAnswer;              //试题答案
    private String state;            //启用状态（停用0，启用1）

    public String getQueName() {
        return queName;
    }

    public void setQueName(String queName) {
        this.queName = queName;
    }

    public String getQueId() {
        return queId;
    }

    public void setQueId(String queId) {
        this.queId = queId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getQueNum() {
        return queNum;
    }

    public void setQueNum(String queNum) {
        this.queNum = queNum;
    }

    public String getQueContent() {
        return queContent;
    }

    public void setQueContent(String queContent) {
        this.queContent = queContent;
    }

    public String getQueAnswer() {
        return queAnswer;
    }

    public void setQueAnswer(String queAnswer) {
        this.queAnswer = queAnswer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}