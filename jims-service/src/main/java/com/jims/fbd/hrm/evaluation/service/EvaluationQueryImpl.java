package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.EvaluationApi;
import com.jims.fbd.hrm.evaluation.api.EvaluationQueryApi;
import com.jims.fbd.hrm.evaluation.bo.EvaluationBo;
import com.jims.fbd.hrm.evaluation.bo.EvaluationQueryBo;
import com.jims.fbd.hrm.evaluation.dao.EvaluationDao;
import com.jims.fbd.hrm.evaluation.dao.EvaluationQueryDao;
import com.jims.fbd.hrm.evaluation.vo.Evaluation;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreDetailVo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class EvaluationQueryImpl extends CrudImplService<EvaluationQueryDao, EvaluationScoreVo> implements EvaluationQueryApi {

    @Autowired
    private EvaluationQueryBo evaluationQueryBo;

    /**
     * 查询我的考核
     *
     * @return
     */
    @Override
    public Page<EvaluationScoreVo> myEvaluation(Page<EvaluationScoreVo> page, EvaluationScoreVo evaluationScoreVo) {
        return evaluationQueryBo.myEvaluation(page,evaluationScoreVo);
    }
    /**
     * 查询评分列表
     *
     * @return
     */
    @Override
    public Page<EvaluationPlan> myGrade(Page<EvaluationPlan> page, EvaluationPlan evaluationPlan) {
        return evaluationQueryBo.myGrade(page,evaluationPlan);
    }
    /**
     * 查看评分
     *
     * @return
     */
    @Override
    public Evaluation getScore(EvaluationPlan evaluationPlan, String persionId) {
        return evaluationQueryBo.getScore(evaluationPlan,persionId);
    }
    /**
     * 获取考评标准
     *
     * @return
     */
    @Override
    public List<EvaluationScoreDetailVo> getDetail(String id, String state) {
        return evaluationQueryBo.getDetail(id,state);
    }
}
