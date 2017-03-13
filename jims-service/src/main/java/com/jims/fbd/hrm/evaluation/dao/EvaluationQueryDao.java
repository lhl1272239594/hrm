package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreDetailVo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface EvaluationQueryDao extends CrudDao<EvaluationScoreVo>{
    /**
     * 查询我的考核
     *
     * @return
     */
    public List<EvaluationScoreVo> myEvaluation(EvaluationScoreVo evaluationScoreVo);
    /**
     * 查询评分列表
     *
     * @return
     */
    public List<EvaluationPlan> myGrade(EvaluationPlan evaluationPlan);
    /**
     * 查看评分
     *
     * @return
     */
    public List<EvaluationScoreVo> getScore(@Param("persionId") String persionId, @Param("planId") String planId, @Param("tableName") String tableName,@Param("tableName1") String tableName1);
    /**
     * 获取考评标准
     *
     * @return
     */
    public List<EvaluationScoreDetailVo> getDetail(@Param("id")String id,@Param("tableName") String tableName);
}
