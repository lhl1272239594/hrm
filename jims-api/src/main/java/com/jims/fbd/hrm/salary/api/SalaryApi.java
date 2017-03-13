package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.Salary;

import java.util.List;

/**
 * 工资管理api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryApi {


    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-08-22
     * @return
     */
    public List<Salary> findLevelsame(String orgId, String typeId, String levelName);
    /**
     * 工资级别名称下拉框带回
     * @author 
     * @version 2016-08-22
     * @return
     */
    public List<Salary> levelDownlist(String orgId);
    /**
     * 保存或修改
     * @param salary
     * @author 
     * @version 2016-05-31
     * @return
     */
    public String save_level(Salary salary);

   /* *//**
     * 修改保存服务明细
     * @param priceBeanVo
     * @return
     * @author 
     * @version 2016-06-01
     */
    /**
     * 保存修改数据
     * @param salary
     * @return
     */
    public String save(Salary salary);
    public String update(Salary salary);

    /**
     * 启用
     * @param levelId
     * @return
     * @author 
     * @version 2016-05-31
     */
    public void changeup(String levelId);
    /**
     * 停用
     * @param levelId
     * @return
     * @author 
     * @version 2016-05-31
     */
    public void changedown(String levelId);
    /**
     * 删除
     * @param ids
     * @return
     * @author 
     * @version 2016-05-31
     */
    public String del_level(String ids);
    /**
     * 查询服务全部列表
     * @return
     * @author 
     * @version 2016-08-20
     */

    public Page<Salary> levelLists(Page<Salary> page, Salary salary);


}
