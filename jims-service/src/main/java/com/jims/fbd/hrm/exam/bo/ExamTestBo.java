package com.jims.fbd.hrm.exam.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.exam.dao.ExamResultDao;
import com.jims.fbd.hrm.exam.dao.ExamTestDao;
import com.jims.fbd.hrm.exam.entity.Exam;
import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.ExamScore;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import com.jims.fbd.hrm.exam.vo.TestVo;
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
public class ExamTestBo extends CrudImplService<ExamTestDao,Exam> {
    /**
     * 开始考试
     * @param testVo
     * @return
     */
    public Test startTest(TestVo testVo) {
        String examId = testVo.getExamId();
        String planId = testVo.getPlanId();
        String orgId = testVo.getOrgId();
        String userId=testVo.getUserId();
        Test test = new Test();
        //查询考生是否开始考试
        ExamScore es = dao.checkTest(examId, planId,userId);
        if(es!=null){
            String state=es.getState();
            if (state.equals("0")) {
                int limitStart = Integer.valueOf(testVo.getLimitStart());
                //判断是否过了考试限制的时间
                int time = dao.checkTime(planId);
                if (time > limitStart*60) {
                    test.setCode("overtime");
                    return test;
                } else {
                    List<ExamDetail> examDetails=dao.getExamQuestion(examId);
                    int sort=1;
                    for(ExamDetail e:examDetails){
                        e.preInsert();
                        e.setExamId(examId);
                        e.setPlanId(planId);
                        e.setScoreId(es.getId());
                        e.setSort(String.valueOf(sort));
                        dao.insertExamDetail(e);
                        sort++;
                    }
                    dao.updateScore(es.getId());
                    test.setCode("start");
                    test.setId(es.getId());
                    test.setState("0");
                    List<ExamDetail> examDetails1=dao.getExamDetail(es.getId());
                    test.setExamDetails(examDetails1);
                    return test;
                }
            } else {
                test.setCode("continue");
                test.setId(es.getId());
                String startTime=es.getStartTime();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=null;
                try {
                    date= sdf.parse(startTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int time = dao.LeftTime(sdf.format(date));
                List<ExamDetail> examDetails=dao.getExamDetail(es.getId());
                test.setTime(Integer.valueOf(testVo.getTime()) * 60 + time);
                test.setExamDetails(examDetails);
                test.setState(es.getState());
                return test;
            }
        }
        else {
            test.setCode("none");
            return test;
        }
    }

    /**
     * 开始考试
     * @param sort
     * @param scoreId
     * @return ExamDetail
     */
    public Test getQuestion(String sort, String scoreId) {
        Test test=new Test();
        List<ExamDetail> examDetails =dao.getQuestion(sort,scoreId);
        test.setExamDetails(examDetails);
        return test;
    }
    /**
     * 保存考生答案
     *
     * @param examDetail
     * @return
     */
    public String saveAnswer(ExamDetail examDetail) {
        String queAnswer=examDetail.getQueAnswer();
        String answer=examDetail.getAnswer();
        if(!examDetail.getTypeId().equals("4")){
            if(queAnswer.equals(answer)){
                String score=examDetail.getScore();
                examDetail.setResultScore(score);
            }
            else{
                examDetail.setResultScore("0");
            }
        }
        else{
            examDetail.setResultScore("0");
        }
        examDetail.preUpdate();
        dao.saveAnswer(examDetail);
        return "success";
    }
    /**
     * 交卷验证
     * @param scoreId
     * @param limitSubmit
     * @return
     */
    public Test submitValidate(String scoreId, String limitSubmit) {
        int time = dao.checkSubmitTime(scoreId);
        Test test=new Test();
        if (time < Integer.valueOf(limitSubmit)*60) {
            test.setCode("notEnoughTime");
            return test;
        } else {
            int num = dao.checkNotAnswerNum(scoreId);
            if(num>0){
                test.setCode("hasNotAnswer");
                test.setNum(num);
                return test;
            }
        }
        test.setCode("success");
        return test;
    }
    /**
     * 提交试卷
     *
     * @param testVo
     * @return
     */
    public Test submitExam(TestVo testVo) {
        Test test=new Test();
        String checkScore=testVo.getCheckScore();
        String scoreId=testVo.getScoreId();
        String stateName="";
        String state=dao.getExamScoreState(scoreId);
        if(state.equals("1")){
            String byHand=testVo.getByHand();
            //自动评分
            if(byHand.equals("0")){
                int score=dao.getScore(scoreId);
                if(score>=Integer.valueOf(checkScore)){
                    state="3";
                    stateName="及格";
                }
                else {
                    state="4";
                    stateName="不及格";
                }
                dao.updateExamScore(state,scoreId,String.valueOf(score));
                ExamScore examScore=dao.getExamScore(scoreId);
                dao.insertExamResult(examScore);
                dao.insertExamDetailResult(examScore);
                dao.delExamDetail(examScore);
                int num=dao.getPlanIsComplete(testVo.getPlanId());
                if(num==0){
                    dao.finishPlan(testVo.getPlanId());
                    //dao.delExamDetail(testVo.getPlanId(),testVo.getExamId());
                }
                test.setCode("success");
                test.setScore(score);
                test.setStateName(stateName);
                return test;
            }
            else if(byHand.equals("1")){
                int score=dao.getScore(scoreId);
                state="2";
                dao.updateExamScore(state,scoreId,String.valueOf(score));
                test.setCode("byHand");
                test.setScore(score);
                return test;
            }
            else{
                return null;
            }
        }
        else{
            test.setCode("hasSubmit");
            return test;
        }
    }
}
