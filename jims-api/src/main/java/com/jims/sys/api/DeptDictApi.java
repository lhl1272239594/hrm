package com.jims.sys.api;

import com.jims.common.web.impl.BaseDto;
import com.jims.sys.entity.DeptDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
public interface DeptDictApi {

    /**
     * 查询所有科室信息
     *
     * @return
     */
    public List<DeptDict> findAllList(String orgId);



    /**
     * 查询所有的科室属性类型
     * @return
     */
    public List<DeptDict> findProperty();

    /**
     *查询某个科室
     * @param orgId
     * @return
     */
    public List<DeptDict>  getByOrgId(String orgId) ;


    /**
     * 查询上级科室
     * @return
     */
    public List<DeptDict> findParent();

    /**
     * 查询某个机构的上级科室
     * @return
     */
    public List<DeptDict> findListParent(String orgId,String q,String deptCode);

    /**
     * 保存或修改
     * @param deptDict
     * @return
     */
    public  String save(DeptDict deptDict);

    /**
     * 删除
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(String code);

    /**
     * 检索科室
     * @param dept
     * @return
     */
    public List<DeptDict> findList(DeptDict dept);

    /**
     * 查询所有科室
     * @return
     */
    public List<DeptDict> getList();


    public List<DeptDict> getOperation(String orgId);

    public DeptDict get(String id) ;

    /**
     * 获取医生科室
     * @param orgId
     * @param persionId
     * @return
     */
    public List<DeptDict> getDoctorDept(String orgId,String persionId,String doctorGroup);

    /**
     * 获取科室的权限医生
     * @param orgId
     * @param doctorGroup
     * @param deptId
     * @return
     */
    public List<BaseDto> getDoctor(String orgId,String doctorGroup,String deptId);
    /**
     * 根据部门名称和组织机构ID查询指定组织机构的部门信息
     * @param deptName 部门名称
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<DeptDict> getByNameOrgId(String deptName,String orgId);


    /**
     * 查询病房
     * @param orgId
     * @param q
     * @return
     */
    public List<DeptDict> findListOfInpatients(String orgId,String q);

    /**
     * 查询当前登陆人所属科室
     * @param orgId
     * @param personId
     * @return
     */
    public  List<DeptDict> findByPersonId(String orgId,String personId);


    /**
     *根据id查询科室编码
     * @param
     * @param
     * @return
     */
    public DeptDict findDeptCode(String id);

    }





