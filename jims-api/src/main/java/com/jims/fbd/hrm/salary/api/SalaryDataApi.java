package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryData;
import com.jims.fbd.hrm.salary.vo.SalaryDataPersonVo;
import com.jims.sys.entity.DeptDict;

import java.util.List;

/**
 * 薪资档案api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryDataApi {


    /**
     * 人员树状列表查询
     * @param page
     * @param salaryData
     * @return
     */
    Page<SalaryData> findPageByVo(Page<SalaryData> page, SalaryData salaryData);
    /**
     * 查询薪资档案列表
     * @return
     * @author 
     * @version 2016-08-20
     */
    Page<SalaryData> dataList(Page<SalaryData> page, SalaryData salaryData);
    /**
     * 查询职业信息列表
     * @return
     * @author 
     * @version 2016-09-29
     */
    Page<SalaryData> careerList(Page<SalaryData> page, SalaryData salaryData);
    /**
     * 查询职业变动报表
     * @return
     * @author 
     * @version 2016-11-03
     */
    Page<SalaryData> careerSearch(Page<SalaryData> page, SalaryData salaryData);
    /**
     * 查询职业信息历史列表
     * @return
     * @author 
     * @version 2016-09-29
     */
    Page<SalaryData> careerListAll(Page<SalaryData> page, SalaryData salaryData);
    /**
     * 新增保存薪资档案(老方法不用了)
     * @param loop
     * @return
     */
    String merge(List<SalaryDataPersonVo> loop);
    /**
     * 新增修改职业信息
     * @author 
     * @version 2016-09-30
     * @return
     */
    String careerMerge(SalaryData salaryData, String userName);
    /**
     * 起薪停薪
     * @param datas
     * @return
     * @author 
     */
    String enableFlag(List<SalaryData> datas);
    /**
     * 变更工资级别
     * @param datas
     * @return
     * @author 
     */
    String change(List<SalaryData> datas, String personId, String createDept);
    /**
     * 删除职业信息
     * @param salaryDatas
     * @return
     * @author 
     * @version 2016-08-31
     */
    String del_career(List<SalaryData> salaryDatas);
    /**
     * 查询科室信息
     * @return
     * @author 
     * @version 2016-08-31
     */
    List<DeptDict> findAllList(String orgId, String deptIds);

}
