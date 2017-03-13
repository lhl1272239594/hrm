package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.DeptConfigApi;
import com.jims.fbd.hrm.evaluation.api.EvaluationProjectApi;
import com.jims.fbd.hrm.evaluation.bo.DeptConfigBo;
import com.jims.fbd.hrm.evaluation.bo.EvaluationProjectBo;
import com.jims.fbd.hrm.evaluation.dao.DeptConfigDao;
import com.jims.fbd.hrm.evaluation.dao.EvaluationProjectDao;
import com.jims.fbd.hrm.evaluation.vo.DeptConfig;
import com.jims.fbd.hrm.evaluation.vo.EvaluationType;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class DeptConfigImpl extends CrudImplService<DeptConfigDao, DeptConfig> implements DeptConfigApi {

    @Autowired
    private DeptConfigBo deptConfigBo;
    /**
     * 查询科系
     * @return
     */
    @Override
    public List<DeptConfig> getParent() {
        return deptConfigBo.getParent();
    }
    /**
     * 新增修改科系名称
     *
     * @param deptConfig
     * @return
     */
    @Override
    public String deptMerge(DeptConfig deptConfig) {
        return deptConfigBo.deptMerge(deptConfig);
    }
    /**
     * 删除科系名称
     * @param deptConfigs
     * @return
     */
    @Override
    public String delDept(List<DeptConfig> deptConfigs) {
        return deptConfigBo.delDept(deptConfigs);
    }
    /**
     * 查询科室
     * @return
     */
    @Override
    public List<DeptConfig> findListByPid(String id) {
        return deptConfigBo.findListByPid(id);
    }
    /**
     * 新增科室
     * @param deptConfig
     * @return
     */
    @Override
    public String Merge(DeptConfig deptConfig) {
        return deptConfigBo.Merge(deptConfig);
    }
    /**
     * 删除科室
     * @param deptConfig
     * @return
     */
    @Override
    public String remove(DeptConfig deptConfig) {
        return deptConfigBo.remove(deptConfig);
    }
    /**
     * 查询已选科室
     * @return
     */
    @Override
    public List<DeptConfig> getDeptById(String id) {
        return deptConfigBo.getDeptById(id);
    }
}
