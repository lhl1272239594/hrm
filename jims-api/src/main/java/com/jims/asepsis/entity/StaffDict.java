/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 员工(用户)表Entity
 * @author yangruidong
 * @version 2016-05-23
 */
public class StaffDict extends DataEntity<StaffDict> {

    private static final long serialVersionUID = 1L;
    private String empNo;    //工号
    private String name;     //姓名
    private String userName;     //用户名
    private String inputCode;//拼音码
    private String inputCodeWb;//五笔码

    public StaffDict(){}

    public StaffDict( String name, String inputCode, String userName, String inputCodeWb) {
        this.name = name;
        this.inputCode = inputCode;
        this.userName = userName;
        this.inputCodeWb = inputCodeWb;
    }

    public StaffDict(String empNo, String name, String userName, String inputCode, String inputCodeWb) {
        super(empNo);
        this.name = name;
        this.userName = userName;
        this.inputCode = inputCode;
        this.inputCodeWb = inputCodeWb;
    }


    public String getInputCode() {
        return inputCode;
    }

    public String getEmpNo() {
        return empNo;
    }

    public String getInputCodeWb() {
        return inputCodeWb;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }


    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public void setInputCodeWb(String inputCodeWb) {
        this.inputCodeWb = inputCodeWb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}