package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.EvaluationStandardApi;
import com.jims.fbd.hrm.evaluation.bo.EvaluationStandardBo;
import com.jims.fbd.hrm.evaluation.dao.EvaluationStandardDao;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.evaluation.vo.StandardMergeVo;
import com.jims.fbd.hrm.evaluation.vo.StandardPersonVo;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class EvaluationStandardImpl extends CrudImplService<EvaluationStandardDao, ProjectVo> implements EvaluationStandardApi {

    @Autowired
    private EvaluationStandardBo evaluationStandardBo;

    /**
     * 查询考评项目
     *
     * @return
     */

    @Override
    public List<ProjectVo> projectList(ProjectVo projectVo) {
        return evaluationStandardBo.projectList(projectVo);
    }

    /**
     * 新增考评标准项目
     *
     * @param standardVo
     * @return
     */
    @Override
    public String saveStandard(StandardVo standardVo) {
        return evaluationStandardBo.saveStandard(standardVo);
    }
    /**
     * 修改考评标准状态
     *
     * @param standardMergeVo
     * @return
     */
    @Override
    public String standardMerge(StandardMergeVo standardMergeVo) {
        return evaluationStandardBo.standardMerge(standardMergeVo);
    }
    /**
     * 查看授权人员
     *
     * @param id
     * @return
     */
    @Override
    public List<StandardPersonVo> getPersonById(String id) {
        return evaluationStandardBo.getPersonById(id);
    }
    /**
     * 根据项目查询标准
     *
     * @return
     */
    @Override
    public Page<StandardVo> standardByProject(Page<StandardVo> page, StandardVo standardVo) {
        return evaluationStandardBo.standardByProject(page,standardVo);
    }
    /**
     * 查看考评标准是否被占用
     * @param standardVos
     * @return
     */
    @Override
    public String checkStandardIsUsed(List<StandardVo> standardVos) {
        return evaluationStandardBo.checkStandardIsUsed(standardVos);
    }

    @Override
    public String getPcode(String pname) {
        return evaluationStandardBo.getPcode(pname);
    }

    @Override
    public String getScode(String sname, String pcode) {
        return evaluationStandardBo.getScode(sname,pcode);
    }

    @Override
    public String getUserId(String userName) {
        return null;
    }

    @Override
    public StandardPersonVo getUser(String userName) {
        return evaluationStandardBo.getUser(userName);
    }
}
