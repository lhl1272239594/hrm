package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.EvaluationProjectApi;
import com.jims.fbd.hrm.evaluation.bo.EvaluationProjectBo;
import com.jims.fbd.hrm.evaluation.dao.EvaluationProjectDao;
import com.jims.fbd.hrm.evaluation.vo.EvaluationType;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class EvaluationProjectImpl extends CrudImplService<EvaluationProjectDao, ProjectVo> implements EvaluationProjectApi {

    @Autowired
    private EvaluationProjectBo evaluationProjectBo;

    /**
     * 查询一级项目
     *
     * @return
     */
    @Override
    public Page<ProjectVo> firstLevelList(Page<ProjectVo> page, ProjectVo projectVo) {
        return evaluationProjectBo.firstLevelList(page,projectVo);
    }
    /**
     * 查询二级项目
     *
     * @return
     */
    @Override
    public Page<ProjectVo> secondLevelList(Page<ProjectVo> page, ProjectVo projectVo) {
        return evaluationProjectBo.secondLevelList(page,projectVo);
    }
    /**
     * 新增考评项目
     *
     * @param projectVo
     * @return
     */
    @Override
    public String saveProject(ProjectVo projectVo) {
        return evaluationProjectBo.saveProject(projectVo);
    }
    /**
     * 修改考评项目状态
     *
     * @param projectVo
     * @return
     */
    @Override
    public String editProject(ProjectVo projectVo) {
        return evaluationProjectBo.editProject(projectVo);
    }
    /**
     * 查询考评类型
     *
     * @return
     */
    @Override
    public Page<EvaluationType> evaluationType(Page<EvaluationType> page, EvaluationType evaluationType) {
        return evaluationProjectBo.evaluationType(page,evaluationType);
    }
    /**
     * 新增修改考评类型
     *
     * @param evaluationType
     * @return
     */
    @Override
    public String typeMerge(EvaluationType evaluationType) {
        return evaluationProjectBo.typeMerge(evaluationType);
    }
    /**
     * 启用停用考评类型
     * @param evaluationTypes
     * @return
     */
    @Override
    public String typeStatus(List<EvaluationType> evaluationTypes) {
        return evaluationProjectBo.typeStatus(evaluationTypes);
    }
    /**
     * 删除考评类型
     * @param evaluationTypes
     * @return
     */
    @Override
    public String delType(List<EvaluationType> evaluationTypes) {
        return evaluationProjectBo.delType(evaluationTypes);
    }
    /**
     * 查看考评类型是否被占用
     * @param evaluationTypes
     * @return
     */
    @Override
    public String checkTypeIsUsed(List<EvaluationType> evaluationTypes) {
        return evaluationProjectBo.checkTypeIsUsed(evaluationTypes);
    }
    /**
     * 查看考评项目是否被占用
     * @return
     */
    @Override
    public String checkProjectIsUsed(String id, String lx) {
        return evaluationProjectBo.checkProjectIsUsed(id,lx);
    }
    /**
     * 修改考评类型分类
     * @return
     */
    @Override
    public String changeType(EvaluationType evaluationType) {
        return evaluationProjectBo.changeType(evaluationType);
    }
}
