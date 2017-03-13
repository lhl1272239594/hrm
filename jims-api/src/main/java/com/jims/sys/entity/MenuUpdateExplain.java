package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

import java.sql.Blob;
import java.util.Date;

/**
* 服务菜单更新说明Entity
* @author lgx
* @version 2016-07-19
*/
public class MenuUpdateExplain extends DataEntity<MenuUpdateExplain> {

    private static final long serialVersionUID = 1L;
    private String serviceId;  // 服务ID
    private String title;  // 标题
    private byte[] explain;  // 说明

    private String explainStr;

    public MenuUpdateExplain() {
        super();
    }

    public MenuUpdateExplain(String id) {
        super(id);
    }

    public  String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public  byte[] getExplain() {
        return this.explain;
    }

    public void setExplain(byte[] explain) {
        this.explain = explain;
    }

    public String getExplainStr() {
        if(explainStr == null && explain != null){
            return new String(explain);
        }
        return explainStr;
    }

    public void setExplainStr(String explainStr) {
        this.explainStr = explainStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}