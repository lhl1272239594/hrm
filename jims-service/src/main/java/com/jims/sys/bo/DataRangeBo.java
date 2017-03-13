/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.DataRangeDao;
import com.jims.sys.entity.DeptDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author   yangchen
 * @version 2016-08-21
 */
@Service
@Component
@Transactional(readOnly = false)
public class DataRangeBo extends CrudImplService<DataRangeDao, DeptDict> {


    @Autowired
    private DataRangeDao  dataRangeDao;



    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findList(String orgId, String serviceId, String menuId) {

        //查询出所有的科室信息
        List<DeptDict> list = dataRangeDao.findList(orgId,serviceId,menuId);

        return list;
    }

   /* *//**
     * 查询所有的科室属性的类型
     * @return
     *//*
    public List<DeptDict> findProperty() {
        return deptDictDao.findProperty();
    }

    *//**
     * 查询所有的上级科室
     * @return
     *//*
    public List<DeptDict> findParent() {
        return deptDictDao.findParent();
    }

    *//**
     * 查询某个机构的上级科室
     * @return
     *//*
    public List<DeptDict> findListParent(String orgId){
        return deptDictDao.findListParent(orgId) ;
    }

    *//**
     * 查询科室代码下的所以科室
     * @return
     *//*
    public List<DeptDict> findListByCode(String code){
        return deptDictDao.findListByCode(code);
    }

    *//**
     * 查询所有科室
     * @return
     *//*
    public List<DeptDict> getList(){
        return deptDictDao.getList();
    }

    *//**
     * 手术科室
     * @return
     *//*
    public List<DeptDict> getOperation(String orgId){
        return deptDictDao.getOperation(orgId);
    }

    *//**
     * 获取医生科室
     * @param orgId
     * @param persionId
     * @return
     *//*
    public List<DeptDict> getDoctorDept(String orgId,String persionId,String doctorGroup) {
        return deptDictDao.getDoctorDept(orgId,persionId,doctorGroup);
    }*/
}