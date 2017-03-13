package com.jims.fbd.hrm.evaluation.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.vo.*;

import java.util.List;

public interface EvaluationApi {
    /**
     * 查询评分列表
     *
     * @return
     */
    public  Page<EvaluationPlan> gradeList(Page<EvaluationPlan> evaluationPlanPage, EvaluationPlan evaluationPlan);
    /**
     * 开始评分
     *
     * @return
     */
    public Evaluation  startGrade(EvaluationPlan evaluationPlan, String persionId);
    /**
     * 获取考评标准
     *
     * @return
     */
    public Page<EvaluationScoreDetailVo> getDetail(Page<EvaluationScoreDetailVo> page,EvaluationScoreDetailVo es);
    /**
     * 保存评分
     * @return
     */
    public String saveScore(EvaluationScoreDetailVo evaluationScoreDetailVo);
    /**
     * 查看是否有未评分项
     *
     * @return
     */
    public Evaluation checkBeforeSubmit(String tid, String planId, String persionId);
    /**
     * 提交考评
     *
     * @return
     */
    public Evaluation submitGrade(String tid, String planId, String persionId);
    /**
     * 获取考评头表
     *
     * @return
     */
    public List<EvaluationScoreVo> getObj(String planId, String templetId, String persionId, String type);
    /**
     * 获取成绩头表的总分和当前分值
     *
     * @return
     */
    public EvaluationScoreVo getScoreById(String id, String state);
}
