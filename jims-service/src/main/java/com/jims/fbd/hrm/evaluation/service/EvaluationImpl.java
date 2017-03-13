package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.EvaluationApi;
import com.jims.fbd.hrm.evaluation.bo.EvaluationBo;
import com.jims.fbd.hrm.evaluation.dao.EvaluationDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class EvaluationImpl extends CrudImplService<EvaluationDao, EvaluationPlan> implements EvaluationApi {

    @Autowired
    private EvaluationBo evaluationBo;
    /**
     * 查询评分列表
     *
     * @return
     */
    @Override
    public Page<EvaluationPlan> gradeList(Page<EvaluationPlan> page, EvaluationPlan evaluationPlan) {
        return evaluationBo.gradeList(page,evaluationPlan);
    }
    /**
     * 开始评分
     *
     * @return
     */
    @Override
    public Evaluation startGrade(EvaluationPlan evaluationPlan, String personId) {
        return evaluationBo.startGrade(personId,evaluationPlan);
    }
    /**
     * 获取考评标准
     *
     * @return
     */
    @Override
    public Page<EvaluationScoreDetailVo> getDetail(Page<EvaluationScoreDetailVo> page,EvaluationScoreDetailVo es) {
        return evaluationBo.getDetail(page,es);
    }
    /**
     * 保存评分
     * @return
     */
    @Override
    public String saveScore(EvaluationScoreDetailVo evaluationScoreDetailVo) {
        return evaluationBo.saveScore(evaluationScoreDetailVo);
    }
    /**
     * 查看是否有未评分项
     *
     * @return
     */
    @Override
    public Evaluation checkBeforeSubmit(String tid, String planId, String personId) {
        return evaluationBo.checkBeforeSubmit(tid,planId,personId);
    }
    /**
     * 提交考评
     *
     * @return
     */
    @Override
    public Evaluation submitGrade(String tid, String planId, String personId) {
        return evaluationBo.submitGrade(tid,planId,personId);
    }
    /**
     * 获取考评头表
     *
     * @return
     */
    @Override
    public List<EvaluationScoreVo> getObj(String planId, String templetId, String persionId, String type) {
        return evaluationBo.getObj(planId,templetId,persionId,type);
    }
    /**
     * 获取成绩头表的总分和当前分值
     *
     * @return
     */
    @Override
    public EvaluationScoreVo getScoreById(String tid, String state) {
        return evaluationBo.getScoreById(tid,state);
    }
}
