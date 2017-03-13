package com.jims.fbd.hrm.exam.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.exam.entity.ExamScore;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.ExamGradeVo;

public interface ExamGradeApi {
    /**
     * 查询评分列表
     *
     * @return
     */
    public Page<ExamGradeVo> gradeList(Page<ExamGradeVo> page, ExamGradeVo examGradeVo);
    /**
     * 查询试卷评分详细列表
     * @return
     */
    public Page<ExamScore> gradeByEachList(Page<ExamScore> page, ExamScore examScore);
    /**
     * 获取主观题
     * @return
     */
    public Test getQuestion(String scoreId);
    /**
     * 保存评分
     * @return
     * @param examDetailList
     */
    public String saveGrade(Test examDetailList);
}
