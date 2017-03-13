package com.jims.fbd.hrm.evaluation.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.evaluation.vo.StandardMergeVo;
import com.jims.fbd.hrm.evaluation.vo.StandardPersonVo;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;

import java.util.List;

public interface EvaluationStandardApi {
    /**
     * 查询考评项目
     *
     * @return
     */
    public List<ProjectVo> projectList( ProjectVo projectVo);

    /**
     * 新增考评标准项目
     *
     * @param standardVo
     * @return
     */
    public String saveStandard(StandardVo standardVo);
    /**
     * 修改考评标准状态
     *
     * @param standardMergeVo
     * @return
     */
    public String standardMerge(StandardMergeVo standardMergeVo);
    /**
     * 查看授权人员
     *
     * @param id
     * @return
     */
    public List<StandardPersonVo> getPersonById(String id);
    /**
     * 根据项目查询标准
     *
     * @return
     */
    public Page<StandardVo> standardByProject(Page<StandardVo> standardVoPage, StandardVo standardVo);
    /**
     * 查看考评标准是否被占用
     * @param standardVos
     * @return
     */
    public String checkStandardIsUsed(List<StandardVo> standardVos);

    String getPcode(String pname);

    String getScode(String sname, String pcode);

    String getUserId(String userName);

    StandardPersonVo getUser(String userName);
}
