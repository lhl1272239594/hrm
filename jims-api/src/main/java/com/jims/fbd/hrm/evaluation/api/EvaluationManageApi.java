package com.jims.fbd.hrm.evaluation.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.vo.Evaluation;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreDetailVo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;

import java.util.List;

public interface EvaluationManageApi {
    /**
     * 查询考评计划
     *
     * @return
     */
    public  Page<EvaluationPlan> planList(Page<EvaluationPlan> evaluationPlanPage, EvaluationPlan evaluationPlan);
    /**
     * 评分汇总
     * @param evaluationPlan
     * @return
     */
    public String summaryData(EvaluationPlan evaluationPlan);
    /**
     * 发布成绩
     * @param evaluationPlan
     * @return
     */
    public String publish(EvaluationPlan evaluationPlan);
    /**
     * 查询成绩
     *
     * @return
     */
    public List<EvaluationScoreVo> getScore(String planId, String obj, String state);
    /**
     * 查看评分详情
     *
     * @return
     */
    public Evaluation getScoreList(EvaluationScoreVo evaluationScoreVo);
    /**
     * 获取考评标准
     *
     * @return
     */
    public Page<EvaluationScoreDetailVo> getDetail(Page<EvaluationScoreDetailVo> page,EvaluationScoreDetailVo es);
    /**
     * 查询已结束考评
     *
     * @return
     */
    public Page<EvaluationPlan> resultList(Page<EvaluationPlan> evaluationPlanPage, EvaluationPlan evaluationPlan);
    /**
     * 按权限获取成绩头表
     *
     * @return
     */
    public List<EvaluationScoreVo> getResultScore(String planId, String obj, String state, String deptIds);
    /**
     * 删除考评计划
     * @return
     */
    public String del(EvaluationPlan evaluationPlan);
}
