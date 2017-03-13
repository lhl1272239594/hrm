package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;

import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.bo.OrgStaffBo;
import com.jims.sys.entity.*;
import com.jims.sys.vo.OrgStaffVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")

public class OrgStaffImpl implements OrgStaffApi {

    @Autowired
    private OrgStaffBo orgStaffBo;


    @Override
    public OrgStaff get(String id) {
        return orgStaffBo.get(id);
    }

    @Override
    public Page<OrgStaff> findPage(Page<OrgStaff> page, OrgStaff orgStaff) {
        return orgStaffBo.findPage(page,orgStaff);
    }

    /**
     * 多表分页查询  返回orgStaffVo
     *
     * @param page
     * @param orgStaffVo
     * @return
     */
    @Override
    public Page<OrgStaffVo> findPageByVo(Page<OrgStaffVo> page, OrgStaffVo orgStaffVo) {

        return orgStaffBo.findPageByVo(page, orgStaffVo);
    }

    @Override
    public String save(OrgStaff orgStaff) {
        return orgStaffBo.save(orgStaff);
    }

    @Override
    public String delete(String ids) {
        return orgStaffBo.delete(ids);
    }

    /**
     * 删除三张表的数据
     *
     * @param ids
     * @return
     */
    @Override

    public String deleteByAll(String ids) {
        int i = 0;
        try {
            orgStaffBo.deleteByAll(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 添加组织机构人员
     * 向orgStaff表中插入一条记录，向staff_vs_role表中添加记录，并且向persionInfo，sysUser表中插入或更新
     *
     * @param persionInfo
     * @param sysUser
     * @param orgStaff
     * @return
     */
    @Override
    public String insertOrgStaffAndPersion(PersionInfo persionInfo, SysUser sysUser, OrgStaff orgStaff,String[] array) {
        return orgStaffBo.insertOrgStaffAndPersion(persionInfo,sysUser,orgStaff,array);
    }

    /**
     * 通过persionId查询密码，并用于回显
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Override
    public SysUser findPasswordByPersionId(String persionId) {
        return orgStaffBo.findPasswordByPersionId(persionId);
    }

    /**
     * 通过persionId查询title职称  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Override
    public OrgStaff findStaffByPersionId(String persionId,String orgId) {
        return orgStaffBo.findStaffByPersionId(persionId,orgId);
    }

    /**
     * 根据人员ID和组织机构ID查询该人员在某家组织机构的员工信息
     * @param personId 人员ID
     * @param orgId    组织机构ID
     * @return 员工信息
     * @author fengyuguang
     */
    public OrgStaff findStaffByPersonIdOrgId(String personId, String orgId){
        return orgStaffBo.findStaffByPersonIdOrgId(personId,orgId);
    }

    /**
     * 查询人员角色信息
     *
     * @return
     */
    @Override
    public List<OrgRole> getRole(String staffId){
        return orgStaffBo.getRole(staffId);
    }

    /**
     * 根据员工ID查询员工拥有的角色下所有的服务
     * @param staffId 员工ID
     * @return 角色对应服务的list集合
     * @author fengyuguang
     */
    @Override
    public List<OrgRoleVsService> findServiceId(String staffId) {
        return orgStaffBo.findServiceId(staffId);
    }

    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    @Override
    public List<OrgSelfServiceVsMenu> findByServiceId(String serviceId,String staffId) {
        return orgStaffBo.findByServiceId(serviceId,staffId);
    }
}
