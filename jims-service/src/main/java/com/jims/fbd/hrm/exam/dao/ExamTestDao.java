package com.jims.fbd.hrm.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.exam.entity.Exam;
import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.ExamScore;
import com.jims.fbd.hrm.exam.entity.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface ExamTestDao extends CrudDao<Exam>{



    /**
     * 查询考生是否开始考试
     * @param examId
     * @param planId
     * @param userId
     * @return
     */

    public ExamScore checkTest(@Param("examId" ) String examId, @Param("planId" ) String planId, @Param("userId" ) String userId);
    /**
     * 判断是否过了考试限制的时间
     * @param planId
     * @return
     */
    public int checkTime(@Param("planId" ) String planId);
    /**
     * 创建考生考试主表
     * @param ExamScore
     * @return
     */
    public void insertExamScore(@Param("ExamScore" )ExamScore ExamScore);
    /**
     * 查询剩余时间
     * @param start
     * @return
     */
    public int LeftTime(@Param("start" ) String start);
    /**
     * 查询试卷试题
     * @param examId
     * @return
     */
    public List<ExamDetail> getExamQuestion(@Param("examId" ) String examId);
    /**
     * 插入考试成绩行表
     * @param ExamDetail
     * @return
     */
    public void insertExamDetail(@Param("ExamDetail" )ExamDetail ExamDetail);
    /**
     * 获取考试成绩行表
     * @param id
     * @return
     */
    public List<ExamDetail> getExamDetail(@Param("id" ) String id);
    /**
     * 查看成绩行表
     *
     * @return
     */
    public List<ExamDetail> getQuestion(@Param("sort") String sort, @Param("scoreId" ) String scoreId);
    /**
     * 保存考生答案
     *
     * @param ExamDetail
     * @return
     */
    public void saveAnswer(@Param("ExamDetail" )ExamDetail ExamDetail);
    /**
     * 交卷验证
     * @param scoreId
     * @return
     */
    public int checkSubmitTime(@Param("scoreId") String scoreId);
    /**
     * 查看未答题数量
     * @param scoreId
     * @return
     */
    public int checkNotAnswerNum(@Param("scoreId") String scoreId);
    /**
     * 获取得分
     * @param scoreId
     * @return
     */
    public int getScore(@Param("scoreId") String scoreId);
    /**
     * 更新考试成绩
     * @param scoreId
     * @return
     */
    public void updateExamScore(@Param("state") String state, @Param("scoreId") String scoreId, @Param("score") String score);
    /**
     * 将考试成绩保存到成绩归档表
     * @param ExamScore
     * @return
     */
    public void insertExamResult(@Param("ExamScore" )ExamScore ExamScore);
    /**
     * 将考试成绩行表保存到成绩归档表
     * @param examScore
     * @return
     */
    public void insertExamDetailResult(ExamScore examScore);
    /**
     * 获取考试成绩主表
     * @param scoreId
     * @return
     */
    public ExamScore getExamScore(@Param("scoreId") String scoreId);
    /**
     * 获取考试成绩主表状态
     * @param scoreId
     * @return
     */
    public String getExamScoreState(@Param("scoreId") String scoreId);
    /**
     * 查询考试是否结束
     * @param planId
     * @return
     */
    public int getPlanIsComplete(@Param("planId") String planId);
    /**
     * 删除答案
     * @return
     */
    public void delExamDetail(ExamScore examScore);
    /**
     * 结束计划
     * @param planId
     * @return
     */
    public void finishPlan(@Param("planId") String planId);
    /**
     * 更新考试主表状态
     * @param id
     * @return
     */
    public void updateScore(@Param("id") String id);

}
