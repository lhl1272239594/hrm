package com.jims.fbd.hrm.salary.api;


import com.jims.fbd.hrm.salary.entity.SalaryTime;

import java.util.List;

/**
 * 工资结算日期api层
 * @author 
 * @version 2016-09-22
 */
public interface SalaryTimeApi {

    //根据参数查询新增数据是否存在
    List<SalaryTime> findTimesame(String orgId);

    /**
     * 新增修改保存
     * @author 
     * @version 2016-09-22
     * @return
     */
 String merge(SalaryTime salaryTime, String userName, String createDept);

    /**
     * 查询工资结算日期列表
     * @return
     */
 List<SalaryTime> timeList(SalaryTime salaryTime);

}
