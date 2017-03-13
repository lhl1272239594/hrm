package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface EvaluationDao extends CrudDao<EvaluationPlan>{
    /**
     * 查询评分列表
     *
     * @return
     */
    public  List<EvaluationPlan> gradeList(EvaluationPlan evaluationPlan);
    /**
     * 获取考评头表
     *
     * @return
     */
    public List<EvaluationScoreVo> getScore(@Param("personId") String personId, @Param("planId") String planId, @Param("templetId") String templetId,@Param("type") String type);
    /**
     * 获取考评头表
     *
     * @return
     */
    public List<EvaluationScoreVo> getObj(@Param("personId") String personId, @Param("planId") String planId, @Param("templetId") String templetId,@Param("type") String type);
    /**
     * 查看已选考评标准
     *
     * @return
     */
    public List<StandardVo> templetStandard(@Param("orgId" )String orgId, @Param("templetId" )String templetId);
    /**
     * 新增考评行表
     *
     * @return
     */
    public void insertDetail(@Param("EvaluationScoreDetailVo" ) EvaluationScoreDetailVo esd);
    /**
     * 更新头表状态
     *
     * @return
     */
    public void updateScoreState(@Param("id" )String id);
    /**
     * 获取考评标准
     *
     * @return
     */
    public List<EvaluationScoreDetailVo> getDetail(EvaluationScoreDetailVo es);
    /**
     * 获取成绩头表的总分和当前分值
     *
     * @return
     */
    public EvaluationScoreVo getScoreById(@Param("id") String id,@Param("tableName") String tableName);
    /**
     * 保存评分
     * @return
     */
    public void updateScoreDetail(@Param("id") String id, @Param("resultScore") String resultScore, @Param("updateBy") String updateBy,@Param("fieldName") String fieldName);
    /**
     * 更新考评主表得分
     * @return
     */
    public void updateScore(@Param("tid" )String tid,@Param("score" ) String score);
    /**
     * 提交查看是否有未评分项
     *
     * @return
     */
    public int checkSubmit(@Param("tid" ) String tid);
    /**
     * 提交所有查看是否有未评分项
     *
     * @return
     */
    public List<EvaluationScoreVo> checkAllSubmit(@Param("planId" ) String planId,@Param("personId" ) String personId);
    /**
     * 提交所有评分
     *
     * @return
     */
    public void submitAllGrade(@Param("planId" )String planId,@Param("personId" ) String personId);
    /**
     * 提交评分
     *
     * @return
     */
    public void submitGrade(@Param("tid") String tid,@Param("personId") String personId);
    /**
     * 提交所有查看是否有未评分项
     *
     * @return
     */
    public List<EvaluationScoreVo> checkScore(@Param("planId" ) String planId,@Param("personId" ) String personId);
    /**
     * 获取科室自评标准
     */
    public List<StandardVo> deptStandard(@Param("deptId" )String deptId);
    /**
     * 批量插入考评行表
     */
    public void insertDetail1(@Param("orgId" )String orgId,@Param("id" ) String id,@Param("planId" ) String planId);
    /**
     * 批量插入科室考评行表
     */
    public void insertDeptDetail(@Param("orgId") String orgId, @Param("id") String id, @Param("deptId") String deptId,@Param("planId") String planId);
}
