package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface EvaluationTempletDao extends CrudDao<TempletVo>{

    /**
     * 查询考评类型
     *
     * @return
     */
    public List<EvaluationType> evaluationType(@Param("orgId" ) String orgId);
    /**
     * 查询考评模板
     *
     * @return
     */
    public List<TempletVo> templetList(TempletVo templetVo);
    /**
     * 查询考评项目
     *
     * @param projectVo
     * @return
     */
    public List<ProjectVo> projectList(ProjectVo projectVo);
    /**
     * 校验模板名称是否存在
     *
     * @param templetVo
     * @return
     */
    public int getTempletName(TempletVo templetVo);
    /**
     * 新增考评模板
     *
     * @param templetVo
     * @return
     */
    public void insertTemplet(@Param("TempletVo" ) TempletVo templetVo);
    /**
     * 更新考评模板
     *
     * @param templetVo
     * @return
     */
    public void updateTemplet(@Param("TempletVo" ) TempletVo templetVo);
    /**
     * 根据项目查询标准
     *
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo);
    /**
     * 删除考评模板
     *
     * @return
     */
    public void delTemplet(@Param("id") String id);
    /**
     * 停用启用考评模板
     *
     * @return
     */
    public void editTemplet(@Param("id") String id,@Param("type") String type);
    /**
     * 保存考评标准
     * @param StandardVo
     * @return
     */
    public void saveStandard(@Param("StandardVo" ) StandardVo StandardVo);
    /**
     * 查看已选考评标准
     *
     * @return
     */
    public List<StandardVo> templetStandard(@Param("orgId" )String orgId, @Param("templetId" ) String templetId);
    /**
     * 删除考评标准
     *
     * @param templetId
     * @return
     */
    public void delStandard(@Param("templetId" ) String templetId);
    /**
     * 更新考评模板总分
     *
     * @param s
     * @return
     */
    public void addTempletScore(@Param("StandardVo" ) StandardVo s);
    /**
     * 更新考评模板总分
     *
     * @param s
     * @return
     */
    public void subTempletScore(@Param("StandardVo" ) StandardVo s);
    /**
     * 查看授权人员
     *
     * @return
     */
    public List<PersonVo> getPersonById(@Param("type")  String type,@Param("templetId") String templetId);
    /**
     * 保存考评对象
     *
     * @return
     */
    public void savePerson(@Param("PersonVo" ) PersonVo s);
    /**
     * 保存考评部门
     *
     * @return
     */
    public void saveDept(@Param("PersonVo" ) PersonVo s);
    /**
     * 删除考评对象
     *
     * @return
     */
    public void removePerson(@Param("id" ) String id);
    /**
     * 新增考评计划
     *
     * @return
     */
    public void insertEvaluationPlan(@Param("EvaluationPlan" ) EvaluationPlan e);
    /**
     * 查询考评计划名称是否重复
     *
     * @return
     */
    public int getPlanName(@Param("name" ) String name,@Param("orgId" ) String orgId);
    /**
     * 查询考核对象
     *
     * @return
     */
    public List<PersonVo> getPerson(@Param("templetId") String templetId, @Param("obj") String obj,@Param("depId") String depId);
    /**
     * 新增考评主表
     *
     * @return
     * @param es
     */
    public void insertEvaluationScore(EvaluationPlan es);

    /**
     * 查询考评部门对象
     *
     * @return
     */
    public List<PersonVo> getDeptById(@Param("type" )String type,@Param("templetId" ) String templetId);
    /**
     * 更新最后启动时间
     *
     * @return
     */
    public void lastPublish(@Param("templetId" )String templetId);
    /**
     * 查询考核对象
     *
     * @return
     */
    public List<PersonVo> getDept(@Param("templetId") String templetId, @Param("obj") String obj,@Param("type") String type);
    /**
     * 获取评分人员
     *
     * @return
     */
    public List<PersonVo> getGradePerson(@Param("templetId" )String templetId,@Param("type" ) String type);
    /**
     * 删除模板授权人员
     *
     * @return
     */
    public void delAuthorize(@Param("id" )String id);
    /**
     * 删除模板标准
     *
     * @return
     */
    public void delStandardById(@Param("id" )String id);
    /**
     * 模板总分清零
     *
     * @return
     */
    public void clearStandard(@Param("id" ) String id);
    /**
     * 考评模板总分清零
     *
     * @return
     */
    public void clearScore(@Param("id" ) String id);
    /**
     * 查看模板授权
     *
     * @return
     */
    public int checkAuthorize(@Param("id" )String id,@Param("type" ) String type);
    /**
     * 计算相差天数
     *
     * @return
     * @param lastStartDate
     */
    public int checkDay(@Param("lastStartDate" ) String lastStartDate);
    /**
     * 把模板标准复制到计划标准表
     *
     * @return
     */
    public void insertPlanStandard(@Param("templetId") String templetId,@Param("id") String id);
    /**
     * 我的考评标准
     *
     * @param id
     * @param name
     * @return
     */
    public List<StandardVo> myStandard(@Param("id") String id,@Param("name") String name);
    /**
     * 保存自评评分人员
     *
     * @param s
     * @return
     */
    public void saveDeptPerson(@Param("PersonVo" ) PersonVo s);
    /**
     * 获取科室考评标准总分
     *
     * @param depId
     * @return
     */
    public String getTotalScore(@Param("depId" )String depId);
    /**
     * 获取科室考评标准总分
     *
     * @param templetId
     * @param obj
     * @return
     */
    public List<PersonVo> getDeptNotGrade(@Param("templetId" )String templetId,@Param("obj" ) String obj);
    /**
     * 考评对象为科室，按照选择的人员所有科室生成考评头表
     * @param e
     */
    public void insertEvaluationScoreByDept(EvaluationPlan e);
    /**
     * 考评对象为科室，按照选择的人员所有科室生成考评头表
     * @param e
     */
    public void insertEvaluationScoreByPerson(EvaluationPlan e);
    /**
     * 从已选科室中查询出上月参加自评的科室生成考评头表
     * @param e
     */
    public void insertEvaluationScoreByHasGrade(EvaluationPlan e);
    /**
     * 从已选科室中查询出上月未参加自评的科室生成考评头表
     * @param e
     */
    public void insertEvaluationScoreByHasNotGrade(EvaluationPlan e);
    /**
     * 获取所有评分人员，生成每个评分人员针对所选科室的考评主表
     * @param e
     */
    public void insertEvaluationScoreGradeDept(EvaluationPlan e);
    /**
     * 获取所有评分人员，生成每个评分人员针对所选科室的考评主表
     * @param e
     */
    public void insertEvaluationScoreGradePerson(EvaluationPlan e);
    /**
     * 生成科室自评主表
     * @param e
     */
    public void insertEvaluationScoreDept(EvaluationPlan e);
}
