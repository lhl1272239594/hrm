package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.DeptConfigDao;
import com.jims.fbd.hrm.evaluation.dao.EvaluationDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class DeptConfigBo extends CrudImplService<DeptConfigDao,DeptConfig> {
    /**
     * 查询科系
     * @return
     */
    public List<DeptConfig> getParent() {
        return dao.getParent();
    }
    /**
     * 新增修改科系名称
     *
     * @param deptConfig
     * @return
     */
    public String deptMerge(DeptConfig deptConfig) {
        int num=dao.getDeptName(deptConfig);
        if(num>0){
            return "hasName";
        }
        if (!deptConfig.getId().equals("")){
            deptConfig.preUpdate();
            dao.updateDept(deptConfig);
            return "success";
        }
        else{
            deptConfig.preInsert();
            dao.insertDept(deptConfig);
            return "success";
        }
    }
    /**
     * 删除科系名称
     * @param deptConfigs
     * @return
     */
    public String delDept(List<DeptConfig> deptConfigs) {
        for(DeptConfig dc:deptConfigs){
            dc.setPid("");
            dao.delDept(dc);
        }
        return "success";
    }
    /**
     * 查询科室
     * @return
     */
    public List<DeptConfig> findListByPid(String id) {
        return dao.findListByPid(id);
    }
    /**
     * 新增科室
     * @param deptConfig
     * @return
     */
    public String Merge(DeptConfig deptConfig) {
        String id=deptConfig.getId();
        String name=deptConfig.getName();
        List<PersonVo> personVo=deptConfig.getDept();
        for(PersonVo p:personVo){
            int num=dao.checkDept(p.getUserId());
            if(num==0){
                DeptConfig dc=new DeptConfig();
                dc.setId(p.getUserId());
                dc.setName(name);
                dc.setPid(id);
                dc.setType("2");
                dao.insertDept(dc);
            }
        }
        return "success";
    }
    /**
     * 删除科室
     * @param deptConfig
     * @return
     */
    public String remove(DeptConfig deptConfig) {
        String id=deptConfig.getId();
        List<PersonVo> personVo=deptConfig.getDept();
        for(PersonVo p:personVo){
            DeptConfig dc=new DeptConfig();
            dc.setPid(id);
            dc.setId(p.getId());
            dao.remove(dc);
        }
        return "success";
    }
    /**
     * 查询已选科室
     * @return
     */
    public List<DeptConfig> getDeptById(String id) {
        return dao.getDeptById(id);
    }
}
