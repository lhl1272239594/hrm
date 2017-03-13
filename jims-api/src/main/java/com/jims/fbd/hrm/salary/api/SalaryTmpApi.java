package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryTmp;

import java.util.List;

/**
 * 创建工资api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryTmpApi {

    /**
     * 查询创建工资列表
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<SalaryTmp> salaryList(Page<SalaryTmp> page, SalaryTmp salaryTmp);
    /**
     * 查询待发放工资列表
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<SalaryTmp> salarytodoList(Page<SalaryTmp> page, SalaryTmp salaryTmp);
    /**
     * 查询待发放工资列表
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<SalaryTmp> salarytodoList1(Page<SalaryTmp> page, SalaryTmp salaryTmp);
    /**
     * 手工调整
     * @param tmps
     * @return
     * @author 
     */
    String change(List<SalaryTmp> tmps, String userName);
    /**
     * 创建工资
     * @param salaryTmp
     * @return
     * @author 
     */
    String create(SalaryTmp salaryTmp);

    /**
     * 重新计算
     * @param tmps
     * @return
     * @author 
     */
    String recal(List<SalaryTmp> tmps);
    /**
     * 下发工资(全部)
     * @param salaryTmp
     * @return
     * @author 
     */
    String deal(SalaryTmp salaryTmp);
    /**
     * 下发工资(按部门)
     * @param salaryTmp
     * @return
     * @author 
     */
    String dealbyDept(SalaryTmp salaryTmp);
    /**
     * 查询工资历史列表
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<SalaryTmp> salaryHistorylist(Page<SalaryTmp> page, SalaryTmp salaryTmp);
    /**
     * 查询历史信息
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<SalaryTmp> salaryHistoryall(Page<SalaryTmp> page, SalaryTmp salaryTmp);

    /**
     * 查询工资历史列表
     * 我的工资使用
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<SalaryTmp> getmypay(Page<SalaryTmp> page, SalaryTmp salaryTmp);
    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryTmp> findSalarysame(String orgId);
    /**
     * 工资详单
     * @return
     */
    List<SalaryTmp> salaryInfo(SalaryTmp salaryTmp);
    /**
     * 工资详单
     * @return
     */
    List<SalaryTmp> salaryDetail(SalaryTmp salaryTmp);

}
