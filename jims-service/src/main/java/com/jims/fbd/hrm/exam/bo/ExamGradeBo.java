package com.jims.fbd.hrm.exam.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.exam.dao.ExamGradeDao;
import com.jims.fbd.hrm.exam.dao.ExamResultDao;
import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.ExamScore;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.ExamGradeVo;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class ExamGradeBo extends CrudImplService<ExamGradeDao,ExamGradeVo> {

    /**
     * 查询试卷
     * @param page
     * @param examGradeVo
     * @return
     */
    public Page<ExamGradeVo> gradeList(Page<ExamGradeVo> page, ExamGradeVo examGradeVo) {
        examGradeVo.setPage(page);
        page.setList(dao.gradeList(examGradeVo));
        return page;
    }
    /**
     * 查询试卷评分详细列表
     * @param page
     * @param examScore
     * @return
     */
    public Page<ExamScore> gradeByEachList(Page<ExamScore> page, ExamScore examScore) {
        examScore.setPage(page);
        page.setList(dao.gradeByEachList(examScore));
        return page;
    }
    /**
     * 获取主观题
     * @param scoreId
     * @return
     */
    public Test getQuestion(String scoreId) {
        Test test=new Test();
        List<ExamDetail> examDetails =dao.getQuestion(scoreId);
        test.setExamDetails(examDetails);
        return test;
    }
    /**
     * 保存评分
     * @return
     */
    public String saveGrade(Test test) {
        String planId=test.getPlanId();
        String examId=test.getExamId();
        String scoreId=test.getScoreId();
        int checkScore=test.getScore();
        for(ExamDetail e:test.getExamDetails()){
            dao.saveGrade(e);
        }
        String state=dao.getExamScoreState(scoreId);
        if(state.equals("2")){
            int score=dao.getScore(scoreId);
            if(score>=Integer.valueOf(checkScore)){
                state="3";
            }
            else {
                state="4";
            }
            dao.updateExamScore(state,scoreId,String.valueOf(score),test.getUserId());
            ExamScore examScore=dao.getExamScore(scoreId);
            dao.insertExamResult(examScore);
            dao.insertExamDetailResult(examScore);
            int num=dao.getPlanIsComplete(planId);
            if(num==0){
                dao.finishPlan(planId);
                //dao.delExamScore(planId);
                //dao.delExamDetail(planId);
            }
            return "success";
        }
        return "error";
    }
}
