package com.jims.sys.vo;

import java.io.Serializable;

/**
 * 前台传递擦数
 * Created by heren on 2016/5/23.
 */
public class InputParamVo implements Serializable{

    private String colName ;//列名称
    private String colValue ;//列值
    private String operateMethod ;//操作方法= > < like

    public InputParamVo() {
    }

    public InputParamVo(String colName, String colValue, String operateMethod) {
        this.colName = colName;
        this.colValue = colValue;
        this.operateMethod = operateMethod;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColValue() {
        return colValue;
    }

    public void setColValue(String colValue) {
        this.colValue = colValue;
    }

    public String getOperateMethod() {
        return operateMethod;
    }

    public void setOperateMethod(String operateMethod) {
        this.operateMethod = operateMethod;
    }
}
