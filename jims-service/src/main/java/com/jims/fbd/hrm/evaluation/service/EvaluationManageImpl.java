package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.EvaluationManageApi;
import com.jims.fbd.hrm.evaluation.bo.EvaluationManageBo;
import com.jims.fbd.hrm.evaluation.dao.EvaluationManageDao;
import com.jims.fbd.hrm.evaluation.vo.Evaluation;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreDetailVo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class EvaluationManageImpl extends CrudImplService<EvaluationManageDao, EvaluationPlan> implements EvaluationManageApi {

    @Autowired
    private EvaluationManageBo evaluationManageBo;
    /**
     * 查询考评计划
     *
     * @return
     */
    @Override
    public Page<EvaluationPlan> planList(Page<EvaluationPlan> page, EvaluationPlan evaluationPlan) {
        return evaluationManageBo.planList(page,evaluationPlan);
    }
    /**
     * 评分汇总
     * @param evaluationPlan
     * @return
     */
    @Override
    public String summaryData(EvaluationPlan evaluationPlan) {
        return evaluationManageBo.summaryData(evaluationPlan);
    }
    /**
     * 发布成绩
     * @param evaluationPlan
     * @return
     */
    @Override
    public String publish(EvaluationPlan evaluationPlan) {
        return evaluationManageBo.publish(evaluationPlan);
    }
    /**
     * 查询成绩
     *
     * @return
     */
    @Override
    public List<EvaluationScoreVo> getScore(String planId, String obj, String state) {
        return evaluationManageBo.getScore(planId,obj,state);
    }
    /**
     * 查看评分详情
     *
     * @return
     */
    @Override
    public Evaluation getScoreList(EvaluationScoreVo evaluationScoreVo) {
        return evaluationManageBo.getScoreList(evaluationScoreVo);
    }
    /**
     * 获取考评标准
     *
     * @return
     */
    @Override
    public Page<EvaluationScoreDetailVo> getDetail(Page<EvaluationScoreDetailVo> page,EvaluationScoreDetailVo es) {
        return evaluationManageBo.getDetail(page,es);
    }
    /**
     * 查询已结束考评
     *
     * @return
     */
    @Override
    public Page<EvaluationPlan> resultList(Page<EvaluationPlan> page, EvaluationPlan evaluationPlan) {
        return evaluationManageBo.resultList(page,evaluationPlan);
    }
    /**
     * 按权限获取成绩头表
     *
     * @return
     */
    @Override
    public List<EvaluationScoreVo> getResultScore(String planId, String obj, String state, String deptIds) {
        return evaluationManageBo.getResultScore(planId,obj,state,deptIds);
    }
    /**
     * 删除考评计划
     * @return
     */
    @Override
    public String del(EvaluationPlan evaluationPlan) {
        return evaluationManageBo.del(evaluationPlan);
    }
}
