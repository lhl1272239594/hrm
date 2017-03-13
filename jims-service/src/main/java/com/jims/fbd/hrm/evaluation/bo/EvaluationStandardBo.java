package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.EvaluationProjectDao;
import com.jims.fbd.hrm.evaluation.dao.EvaluationStandardDao;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.evaluation.vo.StandardMergeVo;
import com.jims.fbd.hrm.evaluation.vo.StandardPersonVo;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.sys.entity.OrgStaff;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class EvaluationStandardBo extends CrudImplService<EvaluationStandardDao,ProjectVo> {

    /**
     * 查询考评项目
     * @param projectVo
     * @return
     */
    public List<ProjectVo> projectList(ProjectVo projectVo) {
        return dao.projectList(projectVo);
    }
    /**
     * 根据项目查询标准
     * @param standardVo
     * @return
     */
    public Page<StandardVo> standardByProject(Page<StandardVo> page, StandardVo standardVo) {
        standardVo.setPage(page);
        page.setList(dao.standardByProject(standardVo));
        return page;
    }
    /**
     * 新增考评标准项目
     *
     * @param standardVo
     * @return
     */
    public String saveStandard(StandardVo standardVo) {
        String pcode=standardVo.getPcode();
        String orgId=standardVo.getOrgId();
        String name=standardVo.getName();
        String id=standardVo.getId();
        int nameNum=dao.getName(pcode,orgId,name,id);
        if(nameNum>0){
            return "hasName";
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
            standardVo.setUpdateBy(standardVo.getCreateBy());
            String score=dao.getScore(id);
            StandardVo s=new StandardVo();
            s.setScore(score);
            s.setPcode(pcode);
            dao.subScore(s);
            dao.updateScore(standardVo);
            dao.updateStandard(standardVo);
            dao.removeStandardPerson(id);
            List<StandardPersonVo> standardPersonVos=standardVo.getStandardPersonVos();
            for(StandardPersonVo o:standardPersonVos){
                o.preInsert();
                o.setStandardId(id);
                o.setOrgId(orgId);
                dao.insertStandardPerson(o);
            }
            return "success";
        }
        else{
            standardVo.preInsert();
            dao.insertStandard(standardVo);
            dao.updateScore(standardVo);
            List<StandardPersonVo> standardPersonVos=standardVo.getStandardPersonVos();
            for(StandardPersonVo o:standardPersonVos){
                o.preInsert();
                o.setStandardId(standardVo.getId());
                o.setOrgId(orgId);
                dao.insertStandardPerson(o);
            }
        }

        return "success";
    }
    /**
     * 修改考评标准状态
     *
     * @param standardMergeVo
     * @return
     */
    public String standardMerge(StandardMergeVo standardMergeVo) {
        String type=standardMergeVo.getType();
        List<StandardVo> standardVos=standardMergeVo.getStandardVos();
        if(type.equals("del")){
            for(StandardVo s:standardVos){
                dao.delStandard(s.getId());
                dao.subScore(s);
            }
        }
        else {
            for(StandardVo s:standardVos){
                dao.editStandard(s.getId(),type);
            }
        }
        return "success";
    }
    /**
     * 查看授权人员
     *
     * @param id
     * @return
     */
    public List<StandardPersonVo> getPersonById(String id) {
        return dao.getPersonById(id);
    }
    /**
     * 查看考评标准是否被占用
     * @param standardVos
     * @return
     */
    public String checkStandardIsUsed(List<StandardVo> standardVos) {
        for (StandardVo q : standardVos) {
            int num=dao.checkStandardIsUsed(q);
            if(num>0){
                return "isUsed";
            }
        }
        return "success";
    }

    public String getPcode(String pname) {
        return dao.getPcode(pname);
    }

    public String getScode(String pname, String pcode) {
        return dao.getScode(pname,pcode);
    }

    public StandardPersonVo getUser(String userName) {
        return dao.getUser(userName);
    }
}
