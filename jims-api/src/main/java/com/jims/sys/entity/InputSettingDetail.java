package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 输入法字段设置
 * Created by yangruidong on 2016/5/18.
 */
public class InputSettingDetail extends DataEntity<InputSettingDetail>{
    private static final long serialVersionUID = 1L;
    private String dataCol;        //列名称
    private String dataTitle;      //显示表头
    private String flagShow;       //是否显示
    private String showSort;       //显示顺序
    private String flagIsname;     //是否字段名称
    private String resultSort;     //结果顺序
    private Integer showWidth;      //显示宽度
    private String inputSettingMasterId;     //输入码主记录的id   外键
    private String inputCode;      //输入码类型

    public InputSettingDetail() {
        super();
    }

    public InputSettingDetail(String id) {
        super(id);
    }

    public String getDataCol() {
        return dataCol;
    }

    public void setDataCol(String dataCol) {
        this.dataCol = dataCol;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getFlagShow() {
        return flagShow;
    }

    public void setFlagShow(String flagShow) {
        this.flagShow = flagShow;
    }

    public String getShowSort() {
        return showSort;
    }

    public void setShowSort(String showSort) {
        this.showSort = showSort;
    }

    public String getFlagIsname() {
        return flagIsname;
    }

    public void setFlagIsname(String flagIsname) {
        this.flagIsname = flagIsname;
    }

    public String getResultSort() {
        return resultSort;
    }

    public void setResultSort(String resultSort) {
        this.resultSort = resultSort;
    }

    public Integer getShowWidth() {
        return showWidth;
    }

    public void setShowWidth(Integer showWidth) {
        this.showWidth = showWidth;
    }

    public String getInputSettingMasterId() {
        return inputSettingMasterId;
    }

    public void setInputSettingMasterId(String inputSettingMasterId) {
        this.inputSettingMasterId = inputSettingMasterId;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }
}
