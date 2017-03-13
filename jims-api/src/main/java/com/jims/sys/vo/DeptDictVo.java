package com.jims.sys.vo;

/**
 * Created by Administrator on 2016/4/27 0027.
 */
public class DeptDictVo {

    private String id;
    private String deptCode;
    private String deptName;
    private String parentId;
    private String orgId;
    private String inputCode;
    private Integer clinicAttr;
    private Integer outpOrInp;
    private Integer internalOrSergery;

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

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    private String array[];

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
