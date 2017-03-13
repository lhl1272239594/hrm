package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryTmp;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建工资DAO接口
 * @author 
 * @version 2016-09-22
 */
@MyBatisHrmDao
public interface SalaryTmpDao extends CrudDao<SalaryTmp> {


    /**
     * 查询创建工资列表

     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTmp> salaryList(SalaryTmp salaryTmp);
    /**
     * 查询待发放工资列表

     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTmp> salarytodoList(SalaryTmp salaryTmp);
    /**
     * 查询待发放工资列表

     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTmp> salarytodoList1(SalaryTmp salaryTmp);
    /**
     * 手工调整
     * @param salaryTmp
     * @return
     */
    void change(@Param("SalaryTmp") SalaryTmp salaryTmp, @Param("userName") String userName);
    /**
     * 创建工资之前先删除
     * @param PersonVo
     * @return
     */
    void deleteSalary(@Param("PersonVo") PersonVo PersonVo);
    /**
     * 创建工资
     * @param PersonVo
     * @return
     */
    void create(@Param("PersonVo") PersonVo PersonVo);

    /**
     * 重新计算
     * @param salaryTmp
     * @return
     */
    void recal(@Param("SalaryTmp") SalaryTmp salaryTmp);

    /**
     * 下发工资前先修改状态为已下发(全部)
     * @param salaryTmp
     * @return
     */
    void updateState(@Param("SalaryTmp") SalaryTmp salaryTmp);
    void updateStateByDept(@Param("PersonVo") PersonVo PersonVo);//按部门
    /**
     * 工资数据插入工资历史表(全部)
     * @param salaryTmp
     * @return
     */
    void insertHistory(@Param("SalaryTmp") SalaryTmp salaryTmp);
    void insertHistoryByDept(@Param("PersonVo") PersonVo PersonVo);//按部门
    /**
     * 更新确认人和确认时间
     * @param salaryTmp
     * @return
     */
    void updateConfirm(@Param("SalaryTmp") SalaryTmp salaryTmp);
    void updateConfirmDept(@Param("PersonVo") PersonVo PersonVo);//按部门
    /**
     * 查询工资历史列表

     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTmp> salaryHistorylist(SalaryTmp salaryTmp);
    /**
     * 查询历史信息

     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTmp> salaryHistoryall(SalaryTmp salaryTmp);
    /**
     * 查询工资历史列表
     * 我的工资
     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTmp> getmypay(SalaryTmp salaryTmp);
    /**
     * 查询工资是否已下发

     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTmp> findSalarysame(@Param("orgId") String orgId);
    /**
     * 查询工资详单
     * @return
     */
    List<SalaryTmp> salaryInfo(SalaryTmp salaryTmp);
    /**
     * 查询工资详单
     * @return
     */
    List<SalaryTmp> salaryDetail(SalaryTmp salaryTmp);

}