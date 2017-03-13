package com.jims.fbd.hrm.peinfo.bo;

/**
 * Created by fengq on 2016/9/26.
 */

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.peinfo.dao.PeInfoDao;
import com.jims.fbd.hrm.peinfo.entity.HumanSkill;
import com.jims.fbd.hrm.peinfo.entity.MyInfo;
import com.jims.sys.dao.PersionInfoDao;
import com.jims.sys.dao.StaffVsRoleDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.StaffVsRole;
import com.jims.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人员管理BO层
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class PeInfoBo extends CrudImplService<PeInfoDao, MyInfo> {

    @Autowired
    private PersionInfoDao persionInfoDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private StaffVsRoleDao staffVsRoleDao;

    public List<MyInfo> getmyinfo(String personId, String orgId)
    {
        return dao.getmyinfo(personId,orgId);
    }
    /**
     * 人员信息分页查询
     * @param page
     * @param myInfo
     * @return
     */
    public Page<MyInfo> findPageByVo(Page<MyInfo> page, MyInfo myInfo) {
        myInfo.setPage(page);
        page.setList(dao.findHumanList(myInfo));
        return page;
    }
    /**
     * 人员信息分页查询（只检索登录人信息）
     * @param personId
     * @param orgId
     * @return
     */
    public List<MyInfo> findPageByVo1(String personId, String orgId)
    {
        return dao.findHumanList1(personId,orgId);
    }
    /**
     * 删除三张表的数据
     * @param ids
     * @return
     */

    public String deleteByAll(String ids) {
        int i = 0;
        try {
            dao.deleteByPid(ids);
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
     * @param myinfo
     * @return
     */
    public String insertOrgStaffAndPersion(PersionInfo persionInfo, SysUser sysUser, MyInfo myinfo, String[] array) {
        PersionInfo oldPersion = new PersionInfo();
        StringBuilder sb = new StringBuilder();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getId())) {
            boolean flag = false;
            oldPersion = persionInfoDao.get(persionInfo.getId());
            persionInfoDao.updateById(persionInfo);
            MyInfo myinfo1 = dao.getByPersionId(persionInfo.getId());
            OrgStaff of = null;
            if (myinfo1 == null) {
                of = new OrgStaff();
                of.setTitle(myinfo.getTitle());
            }
            if (of != null) {
                //向orgStaff表中插入数据
                myinfo.preInsert();
                dao.insert(myinfo);
                //orgStaff的id
                String id = myinfo.getId();
                StaffVsRole staffVsRole = new StaffVsRole();

                for (int i = 0; i < array.length; i++) {
                    staffVsRole.preInsert();
                    staffVsRole.setStaffId(id);
                    staffVsRole.setRoleId(array[i]);
                    staffVsRoleDao.insert(staffVsRole);
                }
            } else {
                myinfo1.setTitle(myinfo.getTitle());
                myinfo1.setDelFlag("0");
                //如果页面传递的科室和从数据库中查询的科室一样，说明不修改科室，直接更改
                if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(myinfo.getDeptId(), myinfo1.getDeptId())) {

                    dao.update(myinfo1);
                    String id = myinfo1.getId();
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
                    myinfo1.setDeptId(myinfo.getDeptId());
                    dao.update(myinfo1);
                    String id = myinfo1.getId();
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
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getPhoneNum())) {
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
            }
            return "success";

        } else {
            persionInfo.preInsert();
            //向persionInfo表中插入数据
            persionInfoDao.register(persionInfo);
            //插入成功后返回的id
            String id = persionInfo.getId();
            //向orgStaff表中插入组织机构人员信息
            myinfo.preInsert();
            myinfo.setPersionId(id);
            dao.insert(myinfo);
            String staffId = myinfo.getId();
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
            if (org.apache.commons.lang3.StringUtils.isNotBlank(persionInfo.getPhoneNum())) {
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
            }
            return "success";
        }
    }

    /**
     * 重置密码
     * @param  staffId
     * @return
     */
    public String back(String staffId,String userName) {
            dao.back(staffId,userName);
        return "sucsess";
    }

    /**
     * 查询人员类别数据
     * @return
     * @author 
     * @version 2016-08-22
     */
    public Page<HumanSkill> skillList(Page<HumanSkill> page,HumanSkill humanSkill) {
        humanSkill.setPage(page);
        page.setList(dao.skillList(humanSkill));
        return page;
    }
    /**
     *查询人员技能是否重复
     * @return
     * @author 
     */
    public List<HumanSkill> findSkillsame(String orgId, String skill, String skillId){
        return dao.findSkillsame(orgId,skill,skillId);
    }
    /**
     *新增修改保存
     * @return
     * @author 
     */
    public String merge(HumanSkill humanSkill, String userName,String createDept) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(humanSkill.getSkillId())){
            humanSkill.preUpdate();
            humanSkill.setUpdateBy(userName);
            dao.updateSkill(humanSkill);
            return "edit";
        }
        else{
            humanSkill.preInsert();
            humanSkill.setSkillId(humanSkill.getSkillId());
            humanSkill.setCreateBy(userName);
            humanSkill.setCreateDept(createDept);
            dao.insertSkill(humanSkill);
            return "add";
        }
    }
    /**
     * 删除数据
     * @param skills
     * @return
     * @author 
     */
    public String delete(List<HumanSkill> skills) {
        for (HumanSkill q : skills) {
            q.preUpdate();
            dao.deleteSkill(q);
        }
        return "sucsess";
    }
    /**
     * 删除占用判断
     * @return
     * @author 
     * @version 2016-08-23
     */
    public String findOccupy(List<HumanSkill> parts) {
        String result = "no";
        for (HumanSkill q : parts) {

            int sum = dao.findOccupy(q);
            if (sum>1||sum==1){
                result = "yes";
                break;
            }
        }
        return result;
    }
    /**
     *人员技能下拉框带回
     * @return
     * @author 
     */
    public List<HumanSkill> skillDownlist(String orgId){

        return dao.skillDownlist(orgId);
    }
    public List<HumanSkill> skillDownlist1(String orgId){

        return dao.skillDownlist1(orgId);
    }
    public List<HumanSkill> levelDownlist(String orgId,String skillId){

        return dao.levelDownlist(orgId,skillId);
    }
    public List<HumanSkill> levelDownlist1(String orgId,String skillId){

        return dao.levelDownlist1(orgId,skillId);
    }
    public Page<HumanSkill> findList(Page<HumanSkill> page, HumanSkill humanSkill ) {
        humanSkill.setPage(page);
        page.setList(dao.findList(humanSkill));
        return page;
    }

    /**
     *新增人员技能重复判断
     * @return
     */
    public List<HumanSkill> findSame(String orgId,String level,String levelId,String skillId){
        return dao.findSame(orgId,level,levelId,skillId);
    }
    /**
     *新增人员技能
     * @return
     */
    public String insertPrimary(HumanSkill humanSkill) {

         dao.insertPrimary(humanSkill);
        return "1";
    }
    /**
     * 更新人员技能
     * @return
     */
    public String updatePrimary(HumanSkill humanSkill) {

         dao.updatePrimary(humanSkill);
        return "1";
    }
    /**
     * 删除人员技能
     * @return
     */
    public void delPrimary(String dataId) {

        dao.delPrimary(dataId);
    }
    /**
     * 删除工作经历
     * @return
     */
    public void delExp(String personId,String orgId) {

        dao.delExp(personId,orgId);
    }
    /**
     * 删除工作经历
     * @return
     */
    public void delForeign(String expId) {

        dao.delForeign(expId);
    }
    /**
     * 新增工作经历
     * @param myInfo
     * @return
     * @author
     */
    public String insertForeign(MyInfo myInfo) {

        return dao.insertForeign(myInfo)+"";
    }
    /**
     * 更新工作经历
     * @param myInfo
     * @return
     * @author
     */
    public String updateForeign(MyInfo myInfo) {

        return dao.updateForeign(myInfo)+"";
    }
    /**
     * 工作经历列表
     * @param orgId
     * @return
     * @author
     */
    public List<MyInfo> workExplist(String orgId,String personId){

        return dao.workExplist(orgId,personId);
    }
    /**
     * 删除社会关系
     * @param orgId
     * @return
     * @author
     */
    public void delRel(String personId,String orgId) {

        dao.delRel(personId,orgId);
    }
    /**
     * 删除社会关系
     * @param relId
     * @return
     * @author
     */
    public void delForeign1(String relId) {

        dao.delForeign1(relId);
    }
    /**
     * 新增工作经历
     * @param myInfo
     * @return
     * @author
     */
    public String insertForeign1(MyInfo myInfo) {

         dao.insertForeign1(myInfo);
        return "1";
    }
    /**
     * 更新工作经历
     * @param myInfo
     * @return
     * @author
     */
    public String updateForeign1(MyInfo myInfo) {

        dao.updateForeign1(myInfo);
        return "1";
    }
    /**
     * 社会关系列表
     * @param orgId
     * @return
     * @author
     */
    public List<MyInfo> rellist(String orgId,String personId){
        return dao.rellist(orgId,personId);
    }
}
