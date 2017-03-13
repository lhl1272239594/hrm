package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * Created by zq on 2016/5/14.
 */
public class DrugTypeDict extends DataEntity<DrugTypeDict> {

    private static final long serialVersionUID = 1L;
    private String typeName;		// 类型名称
    private String typeCode;		// 类型编码

    public DrugTypeDict() {
        super();
    }

    public DrugTypeDict(String id){
        super(id);
    }

    @Length(min=0, max=32, message="类型名称长度必须介于 0 和 32 之间")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Length(min=0, max=16, message="类型编码长度必须介于 0 和 16 之间")
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

}
