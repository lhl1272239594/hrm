package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.*;
import com.jims.sys.vo.OrgStaffVo;
import com.jims.sys.vo.RoleServiceMenuVsMenuDictVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
public interface OrgStaffApi {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public OrgStaff get(String id);
    /**
     * 查询组织机构人员列表
     *
     * @return
     */
    public Page<OrgStaff> findPage(Page<OrgStaff> page, OrgStaff orgStaff);

    /**
     * 查询组织机构vo人员列表
     * @param page
     * @param orgStaffVo
     * @return
     */
    public Page<OrgStaffVo> findPageByVo(Page<OrgStaffVo> page, OrgStaffVo orgStaffVo);


    /**
     * 保存或修改
     * @param orgStaff
     * @return
     */
    public  String save(OrgStaff orgStaff);



    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 通过persion_id删除三张表的信息
     *
     * @param ids
     * @return
     */
    public String deleteByAll(String ids);

    /**
     * 同时将sys_user ,  persion_info , org_staff 三张表保存
     * @param persionInfo
     * @param sysUser
     * @param orgStaff
     * @return
     */
    public String insertOrgStaffAndPersion(PersionInfo persionInfo,SysUser sysUser,OrgStaff orgStaff,String[] array);

    /**
     * 通过persionId查询密码，并用于回显
     * @param persionId
     * @return
     * @author  yangruidong
     */
    public SysUser findPasswordByPersionId(String persionId);

    /**
     * 通过persionId查询title职称  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    public OrgStaff findStaffByPersionId(String persionId ,String orgId);

    /**
     * 根据人员ID和组织机构ID查询该人员在某家组织机构的员工信息
     * @param personId 人员ID
     * @param orgId    组织机构ID
     * @return 员工信息
     * @author fengyuguang
     */
    public OrgStaff findStaffByPersonIdOrgId(String personId, String orgId);

    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<OrgRole> getRole(String staffId);

    /**
     * 根据员工ID查询员工拥有的角色下所有的服务
     * @param staffId 员工ID
     * @return 角色对应服务的list集合
     * @author fengyuguang
     */
    public List<OrgRoleVsService> findServiceId(String staffId);

    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    public List<OrgSelfServiceVsMenu> findByServiceId(String serviceId,String staffId);

}
