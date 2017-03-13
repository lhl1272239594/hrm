package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.EvaluationDao;
import com.jims.fbd.hrm.evaluation.dao.EvaluationManageDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class EvaluationManageBo extends CrudImplService<EvaluationManageDao,EvaluationPlan> {

    /**
     * 查询考评计划
     *
     * @return
     */
    public Page<EvaluationPlan> planList(Page<EvaluationPlan> page,EvaluationPlan evaluationPlan) {
        evaluationPlan.setPage(page);
        page.setList(dao.planList(evaluationPlan));
        return page;
    }

    /**
     * 评分汇总
     * @param evaluationPlan
     * @return
     */
    public String summaryData(EvaluationPlan evaluationPlan) {
        String planId=evaluationPlan.getId();
        String self=evaluationPlan.getSelf();
        String obj=evaluationPlan.getObj();
        String type=evaluationPlan.getType();
        //如果为科室自评或考评科室
        if(type.equals("1")||type.equals("2")){
            dao.updatePlanState(planId);
        }
        else {
            //考评或科室自评
            if(self.equals("0")||(self.equals("1")&&obj.equals("1"))){
                List<EvaluationScoreVo> es=dao.getScoreByObj(planId,obj);
                for(EvaluationScoreVo e:es){
                    String score=null;
                    if(obj.equals("1")){
                        score=dao.getScoreByDept(planId,e.getDeptId());
                    }
                    if(obj.equals("2")){
                        score=dao.getScoreByUser(planId,e.getUserId());
                    }
                    dao.updateScore(e.getId(),score);
                }
                dao.updatePlanState(planId);
            }
            //人员自评
            if(self.equals("1")&&obj.equals("2")){
                dao.updatePlanState(planId);
            }
        }
        return "success";
    }
    /**
     * 发布成绩
     * @param evaluationPlan
     * @return
     */
    public String publish(EvaluationPlan evaluationPlan) {
        String planId=evaluationPlan.getId();
        dao.insertScore(planId);
        dao.insertScoreDetail(planId);
        dao.delScore(planId);
        dao.delScoreDetail(planId);
        dao.publish(planId);
        return "success";
    }
    /**
     * 查询成绩
     *
     * @return
     */
    public List<EvaluationScoreVo> getScore(String planId, String obj, String state) {
        if(state.equals("3")){
            return dao.getScore(planId,obj);
        }
        if(state.equals("4")){
            return dao.getScoreResult(planId,obj);
        }
        return null;
    }
    /**
     * 查看评分详情
     *
     * @return
     */
    public Evaluation getScoreList(EvaluationScoreVo evaluationScoreVo) {
        String self=evaluationScoreVo.getSelf();
        String obj=evaluationScoreVo.getObj();
        String planId=evaluationScoreVo.getPlanId();
        String state=evaluationScoreVo.getState();
        String tableName="";
        List<EvaluationScoreVo> es=null;
        if(state.equals("3")){
            tableName="EVALUATION_SOCRE";
        }
        if(state.equals("4")){
            tableName="EVALUATION_SOCRE_RESULT";
        }
        if(obj.equals("1")){
            String deptId=evaluationScoreVo.getDeptId();
            es=dao.getDeptScore(planId,deptId,tableName);
        }
        if(obj.equals("2")){
            String userId=evaluationScoreVo.getUserId();
            es=dao.getUserScore(planId,userId,tableName);
        }
        Evaluation evaluation = new Evaluation();
        evaluation.setEs(es);
        return evaluation;

    }
    /**
     * 获取考评标准
     *
     * @return
     */
    public Page<EvaluationScoreDetailVo> getDetail(Page<EvaluationScoreDetailVo> page,EvaluationScoreDetailVo es) {
        String tableName="";
        String state=es.getType();
        if(state.equals("4")){
            tableName="SOCRE_DETAIL_RESULT";
        }
        else{
            tableName="EVALUATION_SOCRE_DETAIL";
        }
        if (es != null) {
            es.setPage(page);
            //表名
            es.setFieldName(tableName);
            page.setList(dao.getDetail(es));
            return page;
        }
        return null;
    }
    /**
     * 查询已结束考评
     *
     * @return
     */
    public Page<EvaluationPlan> resultList(Page<EvaluationPlan> page, EvaluationPlan evaluationPlan) {
        evaluationPlan.setPage(page);
        page.setList(dao.resultList(evaluationPlan));
        return page;
    }
    /**
     * 按权限获取成绩头表
     *
     * @return
     */
    public List<EvaluationScoreVo> getResultScore(String planId, String obj, String state, String deptIds) {
        return dao.getResultScore(planId,obj,deptIds);
    }
    /**
     * 删除考评计划
     * @return
     */
    public String del(EvaluationPlan evaluationPlan) {
        dao.delPlan(evaluationPlan);
        dao.delPlanScore(evaluationPlan);
        dao.delPlanScoreDetail(evaluationPlan);
        if(evaluationPlan.getType().equals("3")){
            dao.delPlanStandard(evaluationPlan);
        }
        return "success";
    }
}
