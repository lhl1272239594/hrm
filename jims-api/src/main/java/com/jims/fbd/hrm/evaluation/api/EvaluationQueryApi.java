package com.jims.fbd.hrm.evaluation.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.vo.Evaluation;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreDetailVo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;

import java.util.List;

public interface EvaluationQueryApi {
    /**
     * 查询我的考核
     *
     * @return
     */
    public  Page<EvaluationScoreVo> myEvaluation(Page<EvaluationScoreVo> page, EvaluationScoreVo evaluationScoreVo);
    /**
     * 查询评分列表
     *
     * @return
     */
    public Page<EvaluationPlan> myGrade(Page<EvaluationPlan> evaluationPlanPage, EvaluationPlan evaluationPlan);
    /**
     * 查看评分
     *
     * @return
     */
    public Evaluation getScore(EvaluationPlan evaluationPlan, String persionId);
    /**
     * 获取考评标准
     *
     * @return
     */
    public List<EvaluationScoreDetailVo> getDetail(String id, String state);
}
