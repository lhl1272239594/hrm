package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 输入法数据设置主要信息
 * Created by yangruidong on 2016/5/18.
 */
public class InputSettingMaster extends DataEntity<InputSettingMaster> {
    private static final long serialVersionUID = 1L;
    private String  dictName;      //字典名称
    private String dictType;       //字典的表或者视图
    private String orgId;         //组织机构id

    public InputSettingMaster() {
        super();
    }

    public InputSettingMaster(String id) {
        super(id);
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
