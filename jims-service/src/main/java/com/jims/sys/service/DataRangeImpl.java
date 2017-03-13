package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.DataRangeApi;
import com.jims.sys.bo.DataRangeBo;
import com.jims.sys.entity.DeptDict;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *  @author yangchen
 * @version 2016-08-21
 * @uodateinfo:
 */
@Service(version = "1.0.0")

public class DataRangeImpl implements DataRangeApi {

    @Autowired
    private DataRangeBo DataRangebo;
    /**
     * 查询所有的组织机构信息
     * @return
     */
    public List<DeptDict> findList(String orgId, String serviceId, String menuId){

        return DataRangebo.findList(orgId,serviceId,menuId);

    }
   /* *//**
     * 查询所有的科室属性的类型
     * @return
     *//*
    @Override
    public List<DeptDict> findProperty() {
        return deptDictBo.findProperty();
    }

    *//**
     * 查询所有的上级科室
     * @return
     *//*
    @Override
    public List<DeptDict> findParent() {
        return deptDictBo.findParent();
    }

    *//**
     * 查询某个机构的上级科室
     * @return
     *//*
    public List<DeptDict> findListParent(String orgId){
        return deptDictBo.findListParent(orgId) ;
    }

    *//**
     * 保存科室
     * @param deptDict
     * @return
     *//*
    @Override
    public String save(DeptDict deptDict) {
        return deptDictBo.save(deptDict);
    }

    @Override
    public String delete(String ids) {
        return deptDictBo.delete(ids);
    }

    *//**
     * 查询科室代码下的所以科室
     * @return
     *//*
    public List<DeptDict> findListByCode(String code){
        return deptDictBo.findListByCode(code);
    }

    *//**
     * 检索科室
     * @param dept
     * @return
     *//*
    public List<DeptDict> findList(DeptDict dept){
        return deptDictBo.findList(dept);
    }

    *//**
     * 查询所有科室
     * @return
     *//*
    public List<DeptDict> getList(){
        return deptDictBo.getList();
    }

    *//**
     * 手术科室
     * @return
     *//*
    @Override
    public List<DeptDict> getOperation(String orgId) {
        return deptDictBo.getOperation(orgId);
    }

    @Override
    public DeptDict get(String id) {
        return deptDictBo.get(id);
    }

    *//**
     * 获取医生科室
     * @param orgId
     * @param persionId
     * @return
     *//*

    @Override
    public List<DeptDict> getDoctorDept(String orgId,String persionId,String doctorGroup) {
        return deptDictBo.getDoctorDept(orgId,persionId,doctorGroup);
    }*/

}
