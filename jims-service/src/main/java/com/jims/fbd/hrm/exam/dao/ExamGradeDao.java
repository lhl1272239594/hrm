package com.jims.fbd.hrm.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.ExamScore;
import com.jims.fbd.hrm.exam.vo.ExamGradeVo;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface ExamGradeDao extends CrudDao<ExamGradeVo>{



    /**
     * 查询考试结果
     * @param examGradeVo
     * @return
     */
    public  List<ExamGradeVo> gradeList(ExamGradeVo examGradeVo);
    /**
     * 查询试卷评分详细列表
     * @param ExamScore
     * @return
     */
    public List<ExamScore> gradeByEachList(ExamScore ExamScore);
    /**
     * 获取主观题
     * @param scoreId
     * @return
     */
    public List<ExamDetail> getQuestion(@Param("scoreId" ) String scoreId);
    /**
     * 保存评分
     * @return
     */
    public void saveGrade(@Param("ExamDetail") ExamDetail ExamDetail);
    /**
     * 获取得分
     * @param scoreId
     * @return
     */
    public int getScore(@Param("scoreId") String scoreId);
    /**
     * 获取考试成绩主表状态
     * @param scoreId
     * @return
     */
    public String getExamScoreState(@Param("scoreId") String scoreId);
    /**
     * 更新考试成绩
     * @param scoreId
     * @return
     */
    public void updateExamScore(@Param("state") String state, @Param("scoreId") String scoreId, @Param("score") String score, @Param("userId") String userId);
    /**
     * 获取考试成绩主表
     * @param scoreId
     * @return
     */
    public ExamScore getExamScore(@Param("scoreId") String scoreId);
    /**
     * 将考试成绩保存到成绩归档表
     * @param ExamScore
     * @return
     */
    public void insertExamResult(@Param("ExamScore" )ExamScore ExamScore);
    /**
     * 查询考试是否结束
     * @param planId
     * @return
     */
    public int getPlanIsComplete(@Param("planId") String planId);
    /**
     * 删除答案
     * @param id
     * @return
     */
    public void delExamDetail(@Param("id") String id);
    /**
     * 结束计划
     * @param planId
     * @return
     */
    public void finishPlan(@Param("planId") String planId);

    /**
     * 将考试成绩行表保存到成绩归档表
     * @param examScore
     * @return
     */
    public void insertExamDetailResult(@Param("ExamScore" ) ExamScore examScore);
    /**
     * 删除考试成绩头表
     * @param id
     * @return
     */
    public void delExamScore(@Param("id") String id);
}
