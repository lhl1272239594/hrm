package com.jims.fbd.hrm.exam.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.exam.api.ExamTestApi;
import com.jims.fbd.hrm.exam.bo.ExamTestBo;
import com.jims.fbd.hrm.exam.dao.ExamTestDao;
import com.jims.fbd.hrm.exam.entity.Exam;
import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")

public class ExamTestImpl extends CrudImplService<ExamTestDao, Exam> implements ExamTestApi {

    @Autowired
    private ExamTestBo examTestBo;
    /**
     * 开始考试
     *
     * @param testVo
     * @return
     */
    @Override
    public Test startTest(TestVo testVo) {
        return examTestBo.startTest(testVo);
    }
    /**
     * 查看成绩行表
     *
     * @param sort
     * @param scoreId
     * @return
     */
    @Override
    public Test getQuestion(String sort, String scoreId) {
        return examTestBo.getQuestion(sort,scoreId);

    }
    /**
     * 保存考生答案
     *
     * @param examDetail
     * @return
     */
    @Override
    public String saveAnswer(ExamDetail examDetail) {
        return examTestBo.saveAnswer(examDetail);
    }
    /**
     * 交卷验证
     * @param scoreId
     * @return
     */
    @Override
    public Test submitValidate(String scoreId, String limitSubmit) {
        return examTestBo.submitValidate(scoreId,limitSubmit);
    }
    /**
     * 提交试卷
     *
     * @param testVo
     * @return
     */
    @Override
    public Test submitExam(TestVo testVo) {
        return examTestBo.submitExam(testVo);
    }


}
