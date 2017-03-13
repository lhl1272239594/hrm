package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.bo.DeptDictBo;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")

public class DeptDictImpl implements DeptDictApi {

    @Autowired
    private DeptDictBo deptDictBo;
    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAllList(String orgId) {
        return deptDictBo.findAllList(orgId);

    }

    /**
     *查询某个科室
     * @param orgId
     * @return
     */
    public List<DeptDict>  getByOrgId(String orgId){
        return deptDictBo.getByOrgId(orgId);
    }
    /**
     * 查询所有的科室属性的类型
     * @return
     */
    @Override
    public List<DeptDict> findProperty() {
        return deptDictBo.findProperty();
    }



    /**
     * 查询所有的上级科室
     * @return
     */
    @Override
    public List<DeptDict> findParent() {
        return deptDictBo.findParent();
    }

    /**
     * 查询某个机构的上级科室
     * @return
     */
    public List<DeptDict> findListParent(String orgId,String  q,String deptCode){
        return deptDictBo.findListParent(orgId, q, deptCode) ;
    }

    /**
     * 保存科室
     * @param deptDict
     * @return
     */
    @Override
    public String save(DeptDict deptDict) {
        return deptDictBo.save(deptDict);
    }

    @Override
    public String delete(String ids) {
        return deptDictBo.delete(ids);
    }

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(String code){
        return deptDictBo.findListByCode(code);
    }

    /**
     * 检索科室
     * @param dept
     * @return
     */
    public List<DeptDict> findList(DeptDict dept){
        return deptDictBo.findList(dept);
    }

    /**
     * 查询所有科室
     * @return
     */
    public List<DeptDict> getList(){
        return deptDictBo.getList();
    }

    /**
     * 手术科室
     * @return
     */
    @Override
    public List<DeptDict> getOperation(String orgId) {
        return deptDictBo.getOperation(orgId);
    }

    @Override
    public DeptDict get(String id) {
        return deptDictBo.get(id);
    }

    /**
     * 获取医生科室
     * @param orgId
     * @param persionId
     * @return
     */

    @Override
    public List<DeptDict> getDoctorDept(String orgId,String persionId,String doctorGroup) {
        return deptDictBo.getDoctorDept(orgId, persionId, doctorGroup);
    }

    /**
     * 获取科室的医生
     * @param orgId
     * @param doctorGroup
     * @param deptId
     * @return
     */
    @Override
    public List<BaseDto> getDoctor(String orgId, String doctorGroup, String deptId) {
        return deptDictBo.getDoctor(orgId,doctorGroup,deptId);
    }

    /**
     * 根据部门名称和组织机构ID查询指定组织机构的部门信息
     * @param deptName 部门名称
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<DeptDict> getByNameOrgId(String deptName,String orgId){
        return deptDictBo.getByNameOrgId(deptName,orgId);
    }

    /**
     * 查询病房
     * @param orgId
     * @param q
     * @return
     */
    @Override
    public List<DeptDict> findListOfInpatients(String orgId, String q) {
        return deptDictBo.findListOfInpatients(orgId,q);
    }

    /**
     * 查询当前登陆人所属科室
     * @param orgId
     * @param personId
     * @return
     */
    @Override
    public List<DeptDict> findByPersonId(String orgId, String personId) {
        return deptDictBo.findByPersonId(orgId,personId);
    }

    /**
     *根据id查询科室编码
     * @param
     * @param
     * @return
     */
    public DeptDict findDeptCode(String id){
        return deptDictBo.findDeptCode(id);
    }

}
