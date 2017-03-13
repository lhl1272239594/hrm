package com.jims.fbd.hrm.task;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;
import com.jims.fbd.hrm.evaluation.vo.TempletVo;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@MyBatisHrmDao
public interface TaskDao extends CrudDao<TempletVo>{

    //获取服务器当前时间
    public Date getNow();

    //获取符合条件的考评模板
    public List<TempletVo> getTemplet();

    //新增考评计划
    public void insertEvaluationPlan(@Param("EvaluationPlan" ) EvaluationPlan e);

    //更新最后启动时间
    public void lastPublish(@Param("templetId" )String templetId);

    //查询考核对象
    public List<PersonVo> getPerson(@Param("templetId" )String templetId, @Param("obj" ) String obj);

    //新增考评主表
    public void insertEvaluationScore(@Param("EvaluationScoreVo" ) EvaluationScoreVo es);

    //查询考核对象
    public List<PersonVo> getDept(@Param("templetId") String templetId, @Param("obj") String obj,@Param("type") String type);
    //获取科室考评标准总分
    public String getTotalScore(@Param("depId" )String depId);
    //获取评分人员
    public List<PersonVo> getGradePerson(@Param("templetId" )String templetId,@Param("type" ) String type);
    //结束考评计划
    public void finishPlan();
    //开始考评计划
    public void startPlan();
    //把模板标准复制到计划标准表
    public void insertPlanStandard(@Param("templetId") String templetId,@Param("id") String id);
    //获取考评类型
    public String getType(@Param("templetId") String templetId);
    //获取科室考评标准总分
    public List<PersonVo> getDeptNotGrade(@Param("templetId" )String templetId,@Param("obj" ) String obj);
    //查询考核对象
    public List<PersonVo> getPerson(@Param("templetId") String templetId, @Param("obj") String obj,@Param("depId") String depId);

}
