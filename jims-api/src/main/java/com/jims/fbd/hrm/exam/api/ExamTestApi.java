package com.jims.fbd.hrm.exam.api;

import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.TestVo;

public interface ExamTestApi {
    /**
     * 开始考试
     *
     * @return
     */
    public Test startTest(TestVo TestVo);
    /**
     * 查看成绩行表
     *
     * @return
     */
    public Test getQuestion(String sort, String scoreId);
    /**
     * 保存考生答案
     *
     * @param examDetail
     * @return
     */
    public String saveAnswer(ExamDetail examDetail);
    /**
     * 交卷验证
     * @param scoreId
     * @return
     */
    public Test submitValidate(String scoreId,String limitSubmit);
    /**
     * 提交试卷
     *
     * @param testVo
     * @return
     */
    public Test submitExam(TestVo testVo);
}
