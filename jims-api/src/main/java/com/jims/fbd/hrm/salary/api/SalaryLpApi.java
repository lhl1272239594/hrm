package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryLp;

import java.util.List;

/**
 * 工资级别项目api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryLpApi {

    //根据参数查询新增数据是否重复
    public List<SalaryLp> findLpsame(String orgId, String levelId, String projectId);


   /**
     * 保存修改数据
     * @param salaryLp
     * @return
     */
    public String save(SalaryLp salaryLp);
    public String update(SalaryLp salaryLp);

    /**
     * 启用
     * @param lpId
     * @return
     * @author 
     * @version 2016-08-31
     */
    public void changeup(String lpId);
    /**
     * 停用
     * @param lpId
     * @return
     * @author 
     * @version 2016-08-31
     */
    public void changedown(String lpId);

    /**
     * 删除
     * @param ids
     * @return
     * @author 
     * @version 2016-08-31
     */
    public String del_lp(String ids);
    /**
     * 查询服务全部列表
     * @return
     * @author 
     * @version 2016-08-20
     */

    public Page<SalaryLp> lpList(Page<SalaryLp> page, SalaryLp salaryLp);


}
