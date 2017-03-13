/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.dao.OrgStaffDao;
import com.jims.sys.dao.PersionInfoDao;
import com.jims.sys.dao.StaffVsRoleDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.*;
import com.jims.sys.vo.OrgStaffVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangruidong
 * @version 2016-06-16
 */
@Service
@Component
@Transactional(readOnly = false)
public class OrgStaffBo extends CrudImplService<OrgStaffDao, OrgStaff> {

    @Autowired
    private PersionInfoDao persionInfoDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private StaffVsRoleDao staffVsRoleDao;


    /**
     * 多表分页查询  返回orgStaffVo
     *
     * @param page
     * @param orgStaffVo
     * @return
     */
    public Page<OrgStaffVo> findPageByVo(Page<OrgStaffVo> page, OrgStaffVo orgStaffVo) {
        orgStaffVo.setPage(page);
        page.setList(dao.findListByVo(orgStaffVo));
        return page;
    }

    /**
     * 删除三张表的数据
     *
     * @param ids
     * @return
     */

    public String deleteByAll(String ids) {
        int i = 0;
        try {
            dao.deleteByPid(ids);
            dao.deleteSysUserByPid(ids);
            dao.deletePersonInfo(ids);
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
    public String insertOrgStaffAndPersion(PersionInfo persionInfo, SysUser sysUser, OrgStaff orgStaff, String[] array) {
        PersionInfo oldPersion = new PersionInfo();
        StringBuilder sb = new StringBuilder();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getId())) {
            boolean flag = false;
            oldPersion = persionInfoDao.get(persionInfo.getId());

            persionInfoDao.updateById(persionInfo);
            persionInfoDao.insertCareer(persionInfo);
            OrgStaff neworgStaff = dao.getByPersionId(persionInfo.getId());
            OrgStaff of = null;
            if (neworgStaff == null) {
                of = new OrgStaff();
                of.setTitle(orgStaff.getTitle());
            }

            if (of != null) {

                //向orgStaff表中插入数据
                orgStaff.preInsert();
                dao.insert(orgStaff);
                //orgStaff的id
                String id = orgStaff.getId();
                StaffVsRole staffVsRole = new StaffVsRole();

                for (int i = 0; i < array.length; i++) {
                    staffVsRole.preInsert();
                    staffVsRole.setStaffId(id);
                    staffVsRole.setRoleId(array[i]);
                    staffVsRoleDao.insert(staffVsRole);
                }
            } else {
                neworgStaff.setTitle(orgStaff.getTitle());
                neworgStaff.setDelFlag("0");
                //如果页面传递的科室和从数据库中查询的科室一样，说明不修改科室，直接更改
                if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(orgStaff.getDeptId(), neworgStaff.getDeptId())) {

                    dao.update(neworgStaff);
                    String id = neworgStaff.getId();
                    if (array != null) {
                        staffVsRoleDao.delete(id);
                        StaffVsRole staffVsRole = new StaffVsRole();

                        for (int i = 0; i < array.length; i++) {
                            staffVsRole.preInsert();
                            staffVsRole.setStaffId(id);
                            staffVsRole.setRoleId(array[i]);
                            staffVsRoleDao.insert(staffVsRole);
                        }
                    }
                } else {

                    //如果也面传递的科室和数据库中的科室不一样，说明要更改科室
                    neworgStaff.setDeptId(orgStaff.getDeptId());
                    dao.update(neworgStaff);
                    String id = neworgStaff.getId();
                    if (array.length > 0) {
                        staffVsRoleDao.delete(id);
                        StaffVsRole staffVsRole = new StaffVsRole();

                        for (int i = 0; i < array.length; i++) {
                            staffVsRole.preInsert();
                            staffVsRole.setStaffId(id);
                            staffVsRole.setRoleId(array[i]);
                            staffVsRoleDao.insert(staffVsRole);
                        }
                    }
                }
            }
            //登录表中添加记录（身份证号）
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getCardNo())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getCardNo(), persionInfo.getId());
                if (sysUser1 == null) {
                    SysUser user = new SysUser();
                    user.preInsert();
                    user.setLoginName(persionInfo.getCardNo());
                    if (sysUser.getPassword() != null) {
                        user.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.insert(user);
                } else {
                    sysUser1.setLoginName(persionInfo.getCardNo());
                    if (sysUser.getPassword() != null) {
                        sysUser1.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.updateById(sysUser1);
                }
            }
            //登录表中添加记录（联系电话）
            /*if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getPhoneNum())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getPhoneNum(), persionInfo.getId());
                if (sysUser1 == null) {
                    SysUser user = new SysUser();
                    user.preInsert();
                    user.setLoginName(persionInfo.getPhoneNum());
                    if (sysUser.getPassword() != null) {
                        user.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.insert(user);
                } else {
                    sysUser1.setLoginName(persionInfo.getPhoneNum());
                    if (sysUser.getPassword() != null) {
                        sysUser1.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.updateById(sysUser1);
                }

            }
            //登录表中添加记录（邮箱）
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getEmail())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getEmail(), persionInfo.getId());
                if (sysUser1 == null) {
                    SysUser user = new SysUser();
                    user.preInsert();
                    user.setLoginName(persionInfo.getEmail());
                    if (sysUser.getPassword() != null) {
                        user.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.insert(user);
                } else {
                    sysUser1.setLoginName(persionInfo.getEmail());
                    if (sysUser.getPassword() != null) {
                        sysUser1.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.updateById(sysUser1);
                }
            }
            //登录表中添加记录（昵称）
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getNickName())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getNickName(), persionInfo.getId());
                if (sysUser1 == null) {
                    SysUser user = new SysUser();
                    user.preInsert();
                    user.setLoginName(persionInfo.getNickName());
                    if (sysUser.getPassword() != null) {
                        user.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.insert(user);
                } else {
                    sysUser1.setLoginName(persionInfo.getNickName());
                    if (sysUser.getPassword() != null) {
                        sysUser1.setPassword(sysUser.getPassword());
                    }
                    sysUserDao.updateById(sysUser1);
                }
            }*/
            return "success";

        } else {
            persionInfo.preInsert();
            //向persionInfo表中插入数据
            persionInfoDao.register(persionInfo);
            persionInfoDao.insertCareer(persionInfo);
            //插入成功后返回的id
            String id = persionInfo.getId();
            //向orgStaff表中插入组织机构人员信息
            orgStaff.preInsert();
            orgStaff.setPersionId(id);
            dao.insert(orgStaff);
            String staffId = orgStaff.getId();


            StaffVsRole staffVsRole = new StaffVsRole();
            for (int i = 0; i < array.length; i++) {
                staffVsRole.preInsert();
                staffVsRole.setStaffId(staffId);
                staffVsRole.setRoleId(array[i]);
                staffVsRoleDao.insert(staffVsRole);
            }

            //登录表中添加记录（身份证号）
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getCardNo())) {
                sysUser.preInsert();
                sysUser.setPersionId(id);
                sysUser.setLoginName(persionInfo.getCardNo());
                sysUserDao.insert(sysUser);

            }
            //登录表中添加记录（手机号）
            /*if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getPhoneNum())) {
                sysUser.preInsert();
                sysUser.setPersionId(id);
                sysUser.setLoginName(persionInfo.getPhoneNum());
                sysUserDao.insert(sysUser);
            }
            //登录表中添加记录（用户名）
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getNickName())) {
                sysUser.preInsert();
                sysUser.setPersionId(id);
                sysUser.setLoginName(persionInfo.getNickName());
                sysUserDao.insert(sysUser);
            }
            //登录表中添加记录（邮箱）
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getEmail())) {
                sysUser.preInsert();
                sysUser.setPersionId(id);
                sysUser.setLoginName(persionInfo.getEmail());
                sysUserDao.insert(sysUser);
            }*/

            return "success";
        }
    }

    /**
     * 通过persionId查询密码，并用于回显
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    public SysUser findPasswordByPersionId(String persionId) {
        return sysUserDao.findPasswordByPersionId(persionId);
    }

    /**
     * 通过persionId查询title职称  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    public OrgStaff findStaffByPersionId(String persionId, String orgId) {
        return dao.findStaffByPersonIdOrgId(persionId, orgId);
    }

    /**
     * 根据人员ID和组织机构ID查询该人员在某家组织机构的员工信息
     *
     * @param personId 人员ID
     * @param orgId    组织机构ID
     * @return 员工信息
     * @author fengyuguang
     */
    public OrgStaff findStaffByPersonIdOrgId(String personId, String orgId) {
        return dao.findStaffByPersonIdOrgId(personId, orgId);
    }

    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<OrgRole> getRole(String staffId) {
        return staffVsRoleDao.getRole(staffId);
    }

    /**
     * 根据员工ID查询员工拥有的角色下所有的服务
     *
     * @param staffId 员工ID
     * @return 角色对应服务的list集合
     * @author fengyuguang
     */
    public List<OrgRoleVsService> findServiceId(String staffId) {
        return staffVsRoleDao.findServiceId(staffId);
    }

    /**
     * 根据roleServiceId查询数据列表
     *
     * @param serviceId 服务ID
     * @param staffId   员工ID
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    public List<OrgSelfServiceVsMenu> findByServiceId(String serviceId, String staffId) {
        return dao.findByServiceId(serviceId, staffId);
    }

}