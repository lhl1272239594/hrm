/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;

public class Mould extends DataEntity<Mould> {

    private String name;                //模板名称
    private String type;                //(1为分类，2为模板)
    private String pid;                 //父节点
    private String pname;               //类型名称
    private List<PersonVo> dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<PersonVo> getDept() {
        return dept;
    }

    public void setDept(List<PersonVo> dept) {
        this.dept = dept;
    }
}