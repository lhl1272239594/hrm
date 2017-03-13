package com.jims.fbd.hrm.exam.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.exam.api.ExamGradeApi;
import com.jims.fbd.hrm.exam.bo.ExamGradeBo;
import com.jims.fbd.hrm.exam.dao.ExamGradeDao;
import com.jims.fbd.hrm.exam.entity.ExamScore;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.ExamGradeVo;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")

public class ExamGradeImpl extends CrudImplService<ExamGradeDao, ExamGradeVo> implements ExamGradeApi {

    @Autowired
    private ExamGradeBo examGradeBo;


    /**
     * 查询评分列表
     * @param page
     * @param examGradeVo
     * @return
     * @author wei
     */
    @Override
    public Page<ExamGradeVo> gradeList(Page<ExamGradeVo> page, ExamGradeVo examGradeVo) {
        return examGradeBo.gradeList(page,examGradeVo);
    }
    /**
     * 查询试卷评分详细列表
     * @return
     */
    @Override
    public Page<ExamScore> gradeByEachList(Page<ExamScore> page, ExamScore examScore) {
        return examGradeBo.gradeByEachList(page,examScore);
    }
    /**
     * 获取主观题
     * @return
     */
    @Override
    public Test getQuestion(String scoreId) {
        return examGradeBo.getQuestion(scoreId);
    }
    /**
     * 保存评分
     * @return
     * @param test
     */
    @Override
    public String saveGrade(Test test) {
        return examGradeBo.saveGrade(test);
    }

}
