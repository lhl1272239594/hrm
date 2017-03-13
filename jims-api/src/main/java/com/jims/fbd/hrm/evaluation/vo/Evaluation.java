/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.evaluation.vo;

import com.jims.fbd.hrm.exam.entity.ExamDetail;

import java.io.Serializable;
import java.util.List;

public class Evaluation implements Serializable {

    private String id;                  //ID
    private String code;                //状态
    private String data;                //结果
    private List<EvaluationScoreVo> es;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<EvaluationScoreVo> getEs() {
        return es;
    }

    public void setEs(List<EvaluationScoreVo> es) {
        this.es = es;
    }
}