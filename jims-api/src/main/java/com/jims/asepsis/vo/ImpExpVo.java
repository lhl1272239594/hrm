package com.jims.asepsis.vo;

import java.util.Date;

/**
 * Created by chenxy on 2016/7/12.
 *  用于入出库记账数据展示
 */
public class ImpExpVo {

    private String id;
    private String date;
    private String documentNo;
    private String importExportClass;
    private Integer accountIndicator;
    private Integer flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAccountIndicator() {
        return accountIndicator;
    }

    public void setAccountIndicator(Integer accountIndicator) {
        this.accountIndicator = accountIndicator;
    }

    public String getImportExportClass() {
        return importExportClass;
    }

    public void setImportExportClass(String importExportClass) {
        this.importExportClass = importExportClass;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }



    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


}
