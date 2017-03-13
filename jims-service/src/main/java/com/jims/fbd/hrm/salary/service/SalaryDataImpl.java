package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryDataApi;
import com.jims.fbd.hrm.salary.bo.SalaryDataBo;
import com.jims.fbd.hrm.salary.dao.SalaryDataDao;
import com.jims.fbd.hrm.salary.entity.SalaryData;
import com.jims.fbd.hrm.salary.vo.SalaryDataPersonVo;
import com.jims.sys.entity.DeptDict;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 薪资档案imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class SalaryDataImpl extends CrudImplService<SalaryDataDao,SalaryData> implements SalaryDataApi {
    @Autowired
    private SalaryDataBo salaryDataBo;

    /**
     * 多表分页查询  返回orgStaffVo
     *
     * @param page
     * @param salaryData
     * @return
     */
    @Override
    public Page<SalaryData> findPageByVo(Page<SalaryData> page, SalaryData salaryData) {

        return salaryDataBo.findPageByVo(page, salaryData);
    }

    /**
     * 查询薪资档案数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryData> dataList(Page<SalaryData> page, SalaryData salaryData) {
        return salaryDataBo.dataList(page,salaryData);
    }
    /**
     * 查询职业信息数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryData> careerList(Page<SalaryData> page, SalaryData salaryData) {
        return salaryDataBo.careerList(page,salaryData);
    }
    /**
     * 查询职业变动报表
     * @return
     * @author 
     */
    @Override
    public Page<SalaryData> careerSearch(Page<SalaryData> page, SalaryData salaryData) {
        return salaryDataBo.careerSearch(page,salaryData);
    }
    /**
     * 查询职业历史信息数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryData> careerListAll(Page<SalaryData> page, SalaryData salaryData) {
        return salaryDataBo.careerListAll(page,salaryData);
    }
    /**
     * 新增薪资档案
     *
     * @param loop
     * @return
     */
    @Override
    public String merge(List<SalaryDataPersonVo> loop) {
        return salaryDataBo.merge(loop);
    }
    /**
     * 职业信息保存修改
     * @return
     * @author 
     * @version 2016-09-30
     */
    @Override
    public String careerMerge(SalaryData salaryData, String userName ) {
        return salaryDataBo.careerMerge(salaryData,userName);
    }
    /**
     * 起薪停薪
     * @param datas
     * @return
     * @author 
     */
    @Override
    public String enableFlag(List<SalaryData> datas) {
        return salaryDataBo.enableFlag(datas);
    }
    /**
     * 变更类别
     * @param datas
     * @return
     * @author 
     */
    @Override
    public String change(List<SalaryData> datas, String personId, String createDept) {
        return salaryDataBo.change(datas,personId,createDept);
    }
    /**
     * 删除职业信息
     * @param salaryDatas
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_career(List<SalaryData> salaryDatas) {
        return salaryDataBo.deleteCareer(salaryDatas);
    }
    /**
     * 查询科室信息
     * @return
     */
    public List<DeptDict> findAllList(String orgId, String deptIds) {
        return salaryDataBo.findAllList(orgId,deptIds);

    }
}
