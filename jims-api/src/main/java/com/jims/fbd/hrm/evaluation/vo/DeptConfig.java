/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.io.Serializable;
import java.util.List;

public class DeptConfig extends DataEntity<DeptConfig> {

    private String pid;                 //父ID
    private String name;                //科系名称
    private String deptName;            //科室名称
    private String type;                //(1为科系，2为科室)
    private List<PersonVo> dept;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PersonVo> getDept() {
        return dept;
    }

    public void setDept(List<PersonVo> dept) {
        this.dept = dept;
    }
}