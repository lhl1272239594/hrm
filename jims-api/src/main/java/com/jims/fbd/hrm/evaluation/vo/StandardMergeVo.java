/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.OrgStaff;

import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class StandardMergeVo extends DataEntity<StandardMergeVo> {
    public StandardMergeVo() {
    }

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private String type;                //操作类型
    private List<StandardVo> standardVos;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StandardVo> getStandardVos() {
        return standardVos;
    }

    public void setStandardVos(List<StandardVo> standardVos) {
        this.standardVos = standardVos;
    }
}