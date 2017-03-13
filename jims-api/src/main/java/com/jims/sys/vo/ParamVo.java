package com.jims.sys.vo;

import java.io.Serializable;

/**
 * 用于定义服务参数的显示和值
 * Created by heren on 2016/7/12.
 */
public class ParamVo implements Serializable{

    private String label ;
    private String value ;

    public ParamVo(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public ParamVo() {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
