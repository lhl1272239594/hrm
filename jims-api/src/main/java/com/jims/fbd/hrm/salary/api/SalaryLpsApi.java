package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryLps;

import java.util.List;

/**
 * 工资管理api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryLpsApi {

    //根据参数查询新增数据是否重复
    public List<SalaryLps> findLpssame(String orgId, String levelId, String projectId);


   /**
     * 保存修改数据
     * @param salaryLps
     * @return
     */
    public String save(SalaryLps salaryLps);
    public String update(SalaryLps salaryLps);

    /**
     * 启用
     * @param lpsId
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public void changeup(String lpsId);
    /**
     * 停用
     * @param lpsId
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public void changedown(String lpsId);

    /**
     * 删除
     * @param ids
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public String del_lps(String ids);
    /**
     * 查询服务全部列表
     * @return
     * @author 
     * @version 2016-08-20
     */

    public Page<SalaryLps> lpsList(Page<SalaryLps> page, SalaryLps salaryLps);


}
