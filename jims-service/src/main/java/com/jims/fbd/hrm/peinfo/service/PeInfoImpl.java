package com.jims.fbd.hrm.peinfo.service;

/**
 * 人员管理imp层
 * @author
 * @version 2016-9-27
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.peinfo.api.PeInfoApi;
import com.jims.fbd.hrm.peinfo.bo.PeInfoBo;
import com.jims.fbd.hrm.peinfo.dao.PeInfoDao;
import com.jims.fbd.hrm.peinfo.entity.HumanSkill;
import com.jims.fbd.hrm.peinfo.entity.MyInfo;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class PeInfoImpl extends CrudImplService<PeInfoDao, MyInfo> implements PeInfoApi {

    @Autowired
    private PeInfoBo bo;
    /**
     * 我的信息查询
     * @param personId
     * @param orgId
     * @return
     */
    public List<MyInfo> getmyinfo(String personId, String orgId)
    {
         return  bo.getmyinfo(personId,orgId);
    }
    /**
     * 人员信息分页查询
     * @param page
     * @param myInfo
     * @return
     */
    @Override
    public Page<MyInfo> findPageByVo(Page<MyInfo> page, MyInfo myInfo) {

        return bo.findPageByVo(page, myInfo);
    }
    /**
     * 人员信息分页查询（只检索登录人信息）
     * @param personId
     * @param orgId
     * @return
     */
    @Override
    public List<MyInfo> findPageByVo1(String personId, String orgId){

        return bo.findPageByVo1(personId, orgId);
    }
    /**
     * 删除三张表的数据
     * @param ids
     * @return
     */
    @Override
    public String deleteByAll(String ids) {
        int i = 0;
        try {
            bo.deleteByAll(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 添加组织机构人员
     * 向orgStaff表中插入一条记录，向staff_vs_role表中添加记录，并且向persionInfo，sysUser表中插入或更新
     * @param persionInfo
     * @param sysUser
     * @param
     * @return
     */
    @Override
    public String insertOrgStaffAndPersion(PersionInfo persionInfo, SysUser sysUser, MyInfo myinfo, String[] array) {
        return bo.insertOrgStaffAndPersion(persionInfo,sysUser,myinfo,array);
    }
    /**
     * 重置密码
     * @param staffId
     * @return
     * @author
     */
    @Override
    public String back(String staffId,String userName) {
        return bo.back(staffId,userName);
    }

    /**
     * 查询
     * @return
     * @author
     */
    @Override
    public Page<HumanSkill> skillList(Page<HumanSkill> page, HumanSkill salaryPart) {
        return bo.skillList(page,salaryPart);
    }
    /**
     * 查询是否重复
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public List<HumanSkill> findSkillsame(String orgId, String skill, String skillId) {
        return bo.findSkillsame(orgId,skill,skillId);
    }

    /**
     * 保存修改
     * @return
     * @author
     * @version 2016-05-31
     */
    @Override
    public String merge(HumanSkill humanSkill, String userName, String createDept ) {
        return bo.merge(humanSkill,userName,createDept);
    }

    /**
     * 批量删除
     * @param skills
     * @return
     * @author
     * @version 2016-08-22
     */
    @Override
    public String del_skill(List<HumanSkill> skills) {
        return bo.delete(skills);
    }
    /**
     * 删除占用判断
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(List<HumanSkill> parts) {
        return bo.findOccupy(parts);
    }
    /**
     * 人员技能、等级下拉框带回
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public List<HumanSkill> skillDownlist(String orgId) {
        return bo.skillDownlist(orgId);
    }
    @Override
    public List<HumanSkill> skillDownlist1(String orgId) {
        return bo.skillDownlist1(orgId);
    }
    @Override
    public List<HumanSkill> levelDownlist(String orgId,String skillId) {
        return bo.levelDownlist(orgId,skillId);
    }
    @Override
    public List<HumanSkill> levelDownlist1(String orgId,String skillId) {
        return bo.levelDownlist1(orgId,skillId);
    }
    /**
     * 人员技能列表
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public Page<HumanSkill> findList(Page<HumanSkill> page, HumanSkill humanSkill) {

        return bo.findList(page,humanSkill);
    }
    /**
     * 查询是否重复
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public List<HumanSkill> findSame(String orgId,String level,String levelId,String skillId) {
        return bo.findSame(orgId,level,levelId,skillId);
    }
    /**
     * 新增人员技能
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String insertPrimary(HumanSkill humanSkill) {

        return bo.insertPrimary(humanSkill);
    }
    /**
     * 更新人员技能
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String updatePrimary(HumanSkill humanSkill) {

        return bo.updatePrimary(humanSkill);
    }
    /**
     * 删除人员技能
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String delPrimary(String dataId) {
        try {
            bo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 删除社会经验
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String delExp(String personId,String orgId) {
        try {
            bo.delExp(personId,orgId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 删除社会经验
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String delForeign(String expId) {
        try {
            bo.delForeign(expId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 新增工作经历信息
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String insertForeign(MyInfo myInfo) {
        return bo.insertForeign(myInfo);
    }
    /**
     * 更新工作经历信息
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String updateForeign(MyInfo myInfo) {
        return bo.updateForeign(myInfo);
    }
    /**
     * 工作经历列表
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public List<MyInfo> workExplist(String orgId,String personId) {
        return bo.workExplist(orgId,personId);
    }
    /**
     * 删除社会关系
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String delRel(String personId,String orgId) {
        try {
            bo.delRel(personId,orgId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 删除社会关系
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String delForeign1(String relId) {
        try {
            bo.delForeign1(relId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 新增工作经历信息
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String insertForeign1(MyInfo myInfo) {
        return bo.insertForeign1(myInfo);
    }
    /**
     * 更新工作经历信息
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public String updateForeign1(MyInfo myInfo) {
        return bo.updateForeign1(myInfo);
    }
    /**
     * 工作经历信息列表
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public List<MyInfo> rellist(String orgId,String personId) {
        return bo.rellist(orgId,personId);
    }
}
