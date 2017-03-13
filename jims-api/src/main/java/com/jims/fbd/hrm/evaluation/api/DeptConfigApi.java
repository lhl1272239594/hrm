package com.jims.fbd.hrm.evaluation.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.vo.*;

import java.util.List;

public interface DeptConfigApi {
    /**
     * 查询科系
     * @return
     */
    public List<DeptConfig> getParent();
    /**
     * 新增修改科系名称
     *
     * @param deptConfig
     * @return
     */
    public String deptMerge(DeptConfig deptConfig);
    /**
     * 删除科系名称
     * @param deptConfigs
     * @return
     */
    public String delDept(List<DeptConfig> deptConfigs);
    /**
     * 查询科室
     * @return
     */
    public List<DeptConfig> findListByPid(String id);
    /**
     * 新增科室
     *
     * @param deptConfig
     * @return
     */
    public String Merge(DeptConfig deptConfig);
    /**
     * 删除科室
     * @param deptConfig
     * @return
     */
    public String remove(DeptConfig deptConfig);
    /**
     * 查询已选科室
     * @return
     */
    public List<DeptConfig> getDeptById(String id);
}
