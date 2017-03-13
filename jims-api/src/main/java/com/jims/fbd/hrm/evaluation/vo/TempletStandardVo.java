/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;

import java.util.List;

/**
 * 试题分类Entity
 *
 * @author wangzhiming
 * @version 2016-08-18
 */
public class TempletStandardVo extends DataEntity<TempletStandardVo> {
    public TempletStandardVo() {
    }

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private String templetId;              //模板ID
    private List<StandardVo> standardVos;  //考评标准对象

    public String getTempletId() {
        return templetId;
    }

    public void setTempletId(String templetId) {
        this.templetId = templetId;
    }

    public List<StandardVo> getStandardVos() {
        return standardVos;
    }

    public void setStandardVos(List<StandardVo> standardVos) {
        this.standardVos = standardVos;
    }
}