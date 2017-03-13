package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * Created by yangruidong on 2016/5/10.
 */
public class AdministrationDict extends DataEntity<AdministrationDict> {
    private static final long serialVersionUID = 1L;
    private String administrationCode;   //给药途径代码
    private String administrationName;       //给药途径名称
    private String inputCode;                    //输入码
    private String inpOutpFlag;                //门诊住院标识

    public String getAdministrationCode() {
        return administrationCode;
    }

    public void setAdministrationCode(String administrationCode) {
        this.administrationCode = administrationCode;
    }

    public String getAdministrationName() {
        return administrationName;
    }

    public void setAdministrationName(String administrationName) {
        this.administrationName = administrationName;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getInpOutpFlag() {
        return inpOutpFlag;
    }

    public void setInpOutpFlag(String inpOutpFlag) {
        this.inpOutpFlag = inpOutpFlag;
    }
}
