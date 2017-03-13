package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreDetailVo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface EvaluationManageDao extends CrudDao<EvaluationPlan>{
    /**
     * 查询考评计划
     *
     * @return
     */
    public  List<EvaluationPlan> planList(EvaluationPlan evaluationPlan);

    /**
     * 根据obj查询考评主表
     *
     * @return
     */
    public List<EvaluationScoreVo> getScoreByObj(@Param("planId")String planId,@Param("obj") String obj);
    /**
     * 获取考评对象为部门的评分
     *
     * @return
     * @param planId
     * @param deptId
     */
    public String getScoreByDept(@Param("planId")String planId,@Param("deptId") String deptId);
    /**
     * 获取考评对象为人员的评分
     *
     * @return
     * @param planId
     * @param userId
     */
    public String getScoreByUser(@Param("planId")String planId,@Param("userId") String userId);
    /**
     * 更新考评成绩
     *
     * @return
     */
    public void updateScore(@Param("id")String id,@Param("score") String score);
    /**
     * 更新考评计划状态
     *
     * @return
     */
    public void updatePlanState(@Param("planId") String planId);
    /**
     * 将考评主表复制到归档表
     * @param planId
     * @return
     */
    public void insertScore(@Param("planId") String planId);
    /**
     * 将考评行表复制到归档表
     * @param planId
     * @return
     */
    public void insertScoreDetail(@Param("planId") String planId);
    /**
     * 删除考评主表数据
     * @param planId
     * @return
     */
    public void delScore(@Param("planId") String planId);
    /**
     * 删除考评行表数据
     * @param planId
     * @return
     */
    public void delScoreDetail(@Param("planId") String planId);
    /**
     * 查询汇总成绩
     *
     * @return
     */
    public List<EvaluationScoreVo> getScore(@Param("planId") String planId, @Param("obj") String obj);
    /**
     * 查询发布成绩
     *
     * @return
     */
    public List<EvaluationScoreVo> getScoreResult(@Param("planId") String planId, @Param("obj") String obj);
    /**
     * 查询考评部门评分人员主表
     *
     * @return
     */
    public List<EvaluationScoreVo> getDeptScore(@Param("planId") String planId, @Param("id") String id,@Param("tableName") String tableName);
    /**
     * 查询考评人员评分人员主表
     *
     * @return
     */
    public List<EvaluationScoreVo> getUserScore(@Param("planId") String planId, @Param("id") String id,@Param("tableName") String tableName);
    /**
     * 获取考评标准
     *
     * @return
     */
    public List<EvaluationScoreDetailVo> getDetail(EvaluationScoreDetailVo es);
    /**
     * 发布成绩
     * @param planId
     * @return
     */
    public void publish(@Param("planId")String planId);
    /**
     * 查询已结束考评
     *
     * @return
     */
    public List<EvaluationPlan> resultList(EvaluationPlan evaluationPlan);
    /**
     * 按权限获取成绩头表
     *
     * @return
     */
    public List<EvaluationScoreVo> getResultScore(@Param("planId")String planId,@Param("obj") String obj,@Param("deptIds") String deptIds);
    /**
     * 删除考评计划
     * @return
     */
    public void delPlan(EvaluationPlan evaluationPlan);
    /**
     * 删除考评计划头表
     * @return
     */
    public void delPlanScore(EvaluationPlan evaluationPlan);
    /**
     * 删除考评计划行表
     * @return
     */
    public void delPlanScoreDetail(EvaluationPlan evaluationPlan);
    /**
     * 删除考评计划标准
     * @return
     */
    public void delPlanStandard(EvaluationPlan evaluationPlan);
}
