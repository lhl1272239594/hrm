package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.EvaluationDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class EvaluationBo extends CrudImplService<EvaluationDao,EvaluationPlan> {

    /**
     * 查询评分列表
     *
     * @return
     */
    public Page<EvaluationPlan> gradeList(Page<EvaluationPlan> page, EvaluationPlan evaluationPlan) {
        evaluationPlan.setPage(page);
        page.setList(dao.gradeList(evaluationPlan));
        return page;
    }

    /**
     * 开始评分
     *
     * @return
     */
    public Evaluation startGrade(String personId, EvaluationPlan evaluationPlan) {
        String self = evaluationPlan.getSelf();
        String obj = evaluationPlan.getObj();
        String type = evaluationPlan.getType();
        String planId = evaluationPlan.getId();
        String templetId = evaluationPlan.getTempletId();
        String orgId = evaluationPlan.getOrgId();
        //科室自评
        if (type.equals("1")) {
            //获取科室自评头表
            List<EvaluationScoreVo> es = dao.getScore(personId, planId, templetId, type);
            if (es.size() == 1) {
                String state = es.get(0).getState();
                //未开始考评，导入考评标准
                if (state.equals("0")) {
                    /*List<StandardVo> standardVos = dao.deptStandard(es.get(0).getDeptId());
                    int sort = 1;
                    for (StandardVo s : standardVos) {
                        EvaluationScoreDetailVo esd = new EvaluationScoreDetailVo();
                        esd.preInsert();
                        esd.setTid(es.get(0).getId());
                        esd.setPlanId(planId);
                        esd.setCode(s.getCode());
                        esd.setName(s.getName());
                        esd.setPcode(s.getPcode());
                        esd.setPname(s.getPname());
                        esd.setScode(s.getScode());
                        esd.setSname(s.getSname());
                        esd.setKpi(s.getKpi());
                        esd.setSort(String.valueOf(sort));
                        esd.setScore(s.getScore());
                        dao.insertDetail(esd);
                        sort++;
                    }*/
                    dao.insertDeptDetail(orgId,es.get(0).getId(),es.get(0).getDeptId(),planId);
                    dao.updateScoreState(es.get(0).getId());
                    Evaluation evaluation = new Evaluation();
                    evaluation.setEs(es);
                    return evaluation;
                } else {
                    Evaluation evaluation = new Evaluation();
                    evaluation.setEs(es);
                    return evaluation;
                }
            }
        }
        //考评科室
        if (type.equals("2")) {
            List<EvaluationScoreVo> es = dao.getScore(personId, planId, templetId, type);
            if (es.size() > 0) {
                for (EvaluationScoreVo e : es) {
                    String state = e.getState();
                    if (state.equals("0") && (!e.getObj().equals("4"))) {
                        /*List<StandardVo> standardVos = dao.deptStandard(e.getDeptId());
                        int sort = 1;
                        for (StandardVo s : standardVos) {
                            EvaluationScoreDetailVo esd = new EvaluationScoreDetailVo();
                            esd.preInsert();
                            esd.setTid(e.getId());
                            esd.setPlanId(e.getPlanId());
                            esd.setCode(s.getCode());
                            esd.setName(s.getName());
                            esd.setPcode(s.getPcode());
                            esd.setPname(s.getPname());
                            esd.setScode(s.getScode());
                            esd.setSname(s.getSname());
                            esd.setKpi(s.getKpi());
                            esd.setSort(String.valueOf(sort));
                            esd.setScore(s.getScore());
                            dao.insertDetail(esd);
                            sort++;
                        }*/
                        dao.insertDeptDetail(orgId,e.getId(),e.getDeptId(),planId);
                        dao.updateScoreState(e.getId());
                    }
                }
                Evaluation evaluation = new Evaluation();
                evaluation.setEs(es);
                return evaluation;
            }
        }
        //普通考评
        if (type.equals("3")) {
            List<EvaluationScoreVo> es = dao.getScore(personId, planId, templetId, "");
            //自评
            if (self.equals("1")) {
                if (es.size() == 1) {
                    String state = es.get(0).getState();
                    if (state.equals("0")) {
                        /*List<StandardVo> standardVos = dao.templetStandard(orgId, planId);
                        int sort = 1;
                        for (StandardVo s : standardVos) {
                            EvaluationScoreDetailVo esd = new EvaluationScoreDetailVo();
                            esd.preInsert();
                            esd.setTid(es.get(0).getId());
                            esd.setPlanId(es.get(0).getPlanId());
                            esd.setCode(s.getCode());
                            esd.setName(s.getName());
                            esd.setPcode(s.getPcode());
                            esd.setPname(s.getPname());
                            esd.setScode(s.getScode());
                            esd.setSname(s.getSname());
                            esd.setKpi(s.getKpi());
                            esd.setSort(String.valueOf(sort));
                            esd.setScore(s.getScore());
                            dao.insertDetail(esd);
                            sort++;
                        }*/
                        dao.insertDetail1(orgId,es.get(0).getId(),planId);
                        dao.updateScoreState(es.get(0).getId());
                        Evaluation evaluation = new Evaluation();
                        evaluation.setEs(es);
                        return evaluation;
                    } else {
                        Evaluation evaluation = new Evaluation();
                        evaluation.setEs(es);
                        return evaluation;
                    }
                }
            }
            //考评
            if (self.equals("0")) {
                if (es.size() > 0) {
                    for (EvaluationScoreVo e : es) {
                        String state = e.getState();
                        if (state.equals("0")) {
                            /*List<StandardVo> standardVos = dao.templetStandard(orgId, planId);
                            int sort = 1;
                            for (StandardVo s : standardVos) {
                                EvaluationScoreDetailVo esd = new EvaluationScoreDetailVo();
                                esd.preInsert();
                                esd.setTid(e.getId());
                                esd.setPlanId(e.getPlanId());
                                esd.setCode(s.getCode());
                                esd.setName(s.getName());
                                esd.setPcode(s.getPcode());
                                esd.setPname(s.getPname());
                                esd.setScode(s.getScode());
                                esd.setSname(s.getSname());
                                esd.setKpi(s.getKpi());
                                esd.setSort(String.valueOf(sort));
                                esd.setScore(s.getScore());
                                dao.insertDetail(esd);
                                sort++;
                            }*/
                            dao.insertDetail1(orgId,e.getId(),e.getPlanId());
                            dao.updateScoreState(e.getId());

                        }
                    }
                    Evaluation evaluation = new Evaluation();
                    evaluation.setEs(es);
                    return evaluation;
                }
            }
        }
        return null;
    }

    /**
     * 获取考评标准
     *
     * @return
     */
    public Page<EvaluationScoreDetailVo> getDetail(Page<EvaluationScoreDetailVo> page,EvaluationScoreDetailVo es) {
        if (es != null) {
            es.setPage(page);
            page.setList(dao.getDetail(es));
            return page;
        }
        return null;
    }

    /**
     * 保存评分
     *
     * @return
     */
    public String saveScore(EvaluationScoreDetailVo e) {
        String id = e.getId();
        String score = e.getValue();
        String resultScore = e.getResultScore();
        String tid = e.getTid();
        dao.updateScoreDetail(id, resultScore, e.getUpdateBy(), e.getFieldName());
        String type = e.getType();
        //如果为考评科室类型，查看头表下行表是否评分完成
        if (type.equals("2")) {
            int num = dao.checkSubmit(tid);
            dao.submitGrade(tid, "");
        }
        dao.updateScore(tid, resultScore);
        return "success";
    }

    /**
     * 查看是否有未评分项
     *
     * @return
     */
    public Evaluation checkBeforeSubmit(String tid, String planId, String personId) {
        Evaluation evaluation = new Evaluation();
        if (tid.equals("all")) {
            List<EvaluationScoreVo> es = dao.checkScore(planId, personId);
            if (es.size() > 0) {
                evaluation.setCode("all");
                evaluation.setEs(es);
            } else {
                List<EvaluationScoreVo> es1 = dao.checkAllSubmit(planId, personId);
                if (es1.size() > 0) {
                    evaluation.setCode("all");
                    evaluation.setEs(es1);
                } else {
                    evaluation.setCode("none");
                }
            }
            return evaluation;
        } else {
            int num = dao.checkSubmit(tid);
            if (num > 0) {
                evaluation.setCode("one");
            } else {
                evaluation.setCode("none");
            }
            return evaluation;
        }
    }

    /**
     * 提交考评
     *
     * @return
     */
    public Evaluation submitGrade(String tid, String planId, String personId) {
        Evaluation evaluation = new Evaluation();
        if (tid.equals("all")) {
            dao.submitAllGrade(planId, personId);
            evaluation.setCode("success");
            return evaluation;
        } else {
            dao.submitGrade(tid, personId);
            evaluation.setCode("success");
            return evaluation;
        }
    }

    /**
     * 获取考评头表
     *
     * @return
     */
    public List<EvaluationScoreVo> getObj(String planId, String templetId, String persionId, String type) {
        return dao.getObj(persionId, planId, templetId, type);
    }
    /**
     * 获取成绩头表的总分和当前分值
     *
     * @return
     */
    public EvaluationScoreVo getScoreById(String tid, String state) {
        String tableName=null;
        if(state!=null){
            if(state.equals("3")){
                tableName="EVALUATION_SOCRE";
            }
            if(state.equals("4")){
                tableName="EVALUATION_SOCRE_RESULT";
            }
        }
        if(state==null){
            tableName="EVALUATION_SOCRE";
        }
        return dao.getScoreById(tid,tableName);
    }
}