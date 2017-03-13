/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.vo.OrgStaffVo;

import java.util.List;

/**
 * 组织员工信息DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface OrgStaffDao extends CrudDao<OrgStaff> {


    /**
     * 根据人员id查询组织机构人员信息
     * @param persionId
     * @return
     */
    public OrgStaff getByPersionId(String persionId) ;

    /**
     * 根据人员id查询组织机构人员信息
     * @param persionId
     * @return
     */
    public List<OrgStaff> findListByPersionId(String persionId) ;


    /**
     * 多表查询返回orgStaffVo
     * @param orgStaffVo
     * @return
     */
    public List<OrgStaffVo> findListByVo(OrgStaffVo orgStaffVo);

    /**
     * 通过persionId删除信息
     * @param id
     * @return
     */
    public Integer deleteByPid(String id);

    /**
     * 通过persionId查询title职称  ，用于回显数据
     * @param persionId
     * @return
     * @author yangruidong
     */
    public OrgStaff findStaffByPersionId(String persionId);

    /**
     * 根据人员ID和组织机构ID查询该人员在某家组织机构的员工信息
     * @param personId 人员ID
     * @param orgId    组织机构ID
     * @return 员工信息
     * @author fengyuguang
     */
    public OrgStaff findStaffByPersonIdOrgId(String personId, String orgId);

    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    public List<OrgSelfServiceVsMenu> findByServiceId(String serviceId, String staffId);
    /**
     * 通过SysUser删除信息
     * @param id
     * @return
     */
    public void deleteSysUserByPid(String id);
    /**
     * 通过persion_info删除信息
     * @param id
     * @return
     */
    public void deletePersonInfo(String id);
}