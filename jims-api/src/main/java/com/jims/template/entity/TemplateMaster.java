package com.jims.template.entity;

import com.jims.common.persistence.DataEntity;


/**
* 模板信息Entity
* @author lgx
* @version 2016-08-09
*/
public class TemplateMaster extends DataEntity<TemplateMaster> {

    private static final long serialVersionUID = 1L;
    private String tableName;  // 模板表名称
    private String area;  // 模板区域
    private String areaName;  // 模板区域名称
    private String details;  // 描述
    private String templateName;  //模板名称



    public TemplateMaster() {
        super();
    }

    public TemplateMaster(String id) {
        super(id);
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public  String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public  String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public  String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}