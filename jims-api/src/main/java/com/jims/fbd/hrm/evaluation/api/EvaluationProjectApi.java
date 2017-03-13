package com.jims.fbd.hrm.evaluation.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.vo.EvaluationType;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;

import java.util.List;

public interface EvaluationProjectApi {
    /**
     * 查询一级项目
     *
     * @return
     */
    public Page<ProjectVo> firstLevelList(Page<ProjectVo> page, ProjectVo projectVo);
    /**
     * 查询二级项目
     *
     * @return
     */
    public Page<ProjectVo> secondLevelList(Page<ProjectVo> page, ProjectVo projectVo);
    /**
     * 新增考评项目
     *
     * @param projectVo
     * @return
     */
    public String saveProject(ProjectVo projectVo);
    /**
     * 修改考评项目状态
     *
     * @param projectVo
     * @return
     */
    public String editProject(ProjectVo projectVo);
    /**
     * 查询考评类型
     *
     * @return
     */
    public Page<EvaluationType> evaluationType(Page<EvaluationType> evaluationTypePage, EvaluationType evaluationType);
    /**
     * 新增修改考评类型
     *
     * @param evaluationType
     * @return
     */
    public String typeMerge(EvaluationType evaluationType);
    /**
     * 启用停用考评类型
     * @param evaluationTypes
     * @return
     */
    public String typeStatus(List<EvaluationType> evaluationTypes);
    /**
     * 删除考评类型
     * @param evaluationTypes
     * @return
     */
    public String delType(List<EvaluationType> evaluationTypes);
    /**
     * 查看考评类型是否被占用
     * @param evaluationTypes
     * @return
     */
    public String checkTypeIsUsed(List<EvaluationType> evaluationTypes);
    /**
     * 查看考评项目是否被占用
     * @return
     */
    public String checkProjectIsUsed(String id, String lx);
    /**
     * 修改考评类型分类
     * @return
     */
    public String changeType(EvaluationType evaluationType);
}
