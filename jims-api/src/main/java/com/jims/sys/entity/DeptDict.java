/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 部门信息Entity
 *
 * @author yangruidong
 * @version 2016-04-13
 */
public class DeptDict extends DataEntity<DeptDict> {

    private static final long serialVersionUID = 1L;
    private String parentId;        // 父部门
    private String deptName;        // 部门名称
    private String deptCode;        // 部门代码
    private String deptPropertity;        // 科室属性
    private String orgId;       //组织机构ID
    private String inputCode;   //拼音码
    private Integer clinicAttr;     //临床属性
    private Integer outpOrInp;   //门诊住院属性
    private Integer internalOrSergery;  //内外科属性

    private String q; // 模糊检索参数
    private String deptPropertityName;        // 科室属性


    private String deptCodes;

    public String getDeptCodes() {
        return deptCodes;
    }

    public void setDeptCodes(String deptCodes) {
        this.deptCodes = deptCodes;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getInputCode() {
        return inputCode;
    }

    public Integer getClinicAttr() {
        return clinicAttr;
    }

    public void setClinicAttr(Integer clinicAttr) {
        this.clinicAttr = clinicAttr;
    }

    public Integer getOutpOrInp() {
        return outpOrInp;
    }

    public void setOutpOrInp(Integer outpOrInp) {
        this.outpOrInp = outpOrInp;
    }

    public Integer getInternalOrSergery() {
        return internalOrSergery;
    }

    public void setInternalOrSergery(Integer internalOrSergery) {
        this.internalOrSergery = internalOrSergery;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public DeptDict() {
        super();
    }

    public DeptDict(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "父部门长度必须介于 0 和 64 之间")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Length(min = 0, max = 100, message = "部门名称长度必须介于 0 和 100 之间")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Length(min = 0, max = 20, message = "部门代码长度必须介于 0 和 20 之间")
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Length(min = 0, max = 100, message = "科室属性长度必须介于 0 和 100 之间")
    public String getDeptPropertity() {
        return deptPropertity;
    }

    public void setDeptPropertity(String deptPropertity) {
        this.deptPropertity = deptPropertity;
    }

    public String getDeptPropertityName() {
        return deptPropertityName;
    }

    public void setDeptPropertityName(String deptPropertityName) {
        this.deptPropertityName = deptPropertityName;
    }
}