package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.EvaluationDao;
import com.jims.fbd.hrm.evaluation.dao.EvaluationQueryDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class EvaluationQueryBo extends CrudImplService<EvaluationQueryDao,EvaluationScoreVo> {


    /**
     * 查询我的考核
     *
     * @return
     */
    public Page<EvaluationScoreVo> myEvaluation(Page<EvaluationScoreVo> page, EvaluationScoreVo evaluationScoreVo) {
        evaluationScoreVo.setPage(page);
        page.setList(dao.myEvaluation(evaluationScoreVo));
        return page;
    }
    /**
     * 查询评分列表
     *
     * @return
     */
    public Page<EvaluationPlan> myGrade(Page<EvaluationPlan> page, EvaluationPlan evaluationPlan) {
        evaluationPlan.setPage(page);
        page.setList(dao.myGrade(evaluationPlan));
        return page;
    }
    /**
     * 查看评分
     *
     * @return
     */
    public Evaluation getScore(EvaluationPlan evaluationPlan, String persionId) {
        String state=evaluationPlan.getState();
        String planId=evaluationPlan.getId();
        String templetId=evaluationPlan.getTempletId();
        String tableName="";
        String tableName1="";
        if(state.equals("4")){
            tableName="EVALUATION_SOCRE_RESULT";
        }
        else {
            tableName="EVALUATION_SOCRE";
        }
        if(state.equals("4")){
            tableName1="SOCRE_DETAIL_RESULT";
        }
        else {
            tableName1="EVALUATION_SOCRE_DETAIL";
        }
        List<EvaluationScoreVo> es=dao.getScore(persionId,planId,tableName,tableName1);
        Evaluation evaluation=new Evaluation();
        evaluation.setEs(es);
        return evaluation;
    }
    /**
     * 获取考评标准
     *
     * @return
     */
    public List<EvaluationScoreDetailVo> getDetail(String id, String state) {
        String tableName="";
        if(state.equals("4")){
            tableName="SOCRE_DETAIL_RESULT";
        }
        else {
            tableName="EVALUATION_SOCRE_DETAIL";
        }
        return dao.getDetail(id,tableName);
    }
}
