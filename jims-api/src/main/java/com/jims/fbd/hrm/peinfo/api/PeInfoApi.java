package com.jims.fbd.hrm.peinfo.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.peinfo.entity.HumanSkill;
import com.jims.fbd.hrm.peinfo.entity.MyInfo;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityPlan;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;

import java.util.List;

/**
 * 人员管理api层
 * @author 
 * @version 2016-10-22
 */
public interface PeInfoApi {
    public List<MyInfo> getmyinfo(String personId, String orgId);
    /**
     * 查询组织机构vo人员列表
     * @param page
     * @param myInfo
     * @return
     */
    public Page<MyInfo> findPageByVo(Page<MyInfo> page, MyInfo myInfo);

    /**
     * 查询组织机构vo人员列表(只检索登录人信息)
     * @param personId
     * @param orgId
     * @return
     */
    //public Page<MyInfo> findPageByVo1(Page<MyInfo> page, MyInfo myInfo);
    public List<MyInfo> findPageByVo1(String personId, String orgId);
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
     * @param
     * @return
     */
    public String insertOrgStaffAndPersion(PersionInfo persionInfo, SysUser sysUser, MyInfo myinfo, String[] array);
    /**
     * 重置密码
     * @param staffId
     * @return
     * @author 
     */
    String back(String staffId, String userName);
    /**
     * 查询
     * @return
     * @author 
     * @version 2016-08-20
     */
    Page<HumanSkill> skillList(Page<HumanSkill> page, HumanSkill salaryPart);
    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<HumanSkill> findSkillsame(String orgId, String skill, String skillId);

    /**
     * 保存或修改
     * @author 
     * @version 2016-08-31
     * @return
     */
    String merge(HumanSkill humanSkill, String userName, String createDept);

    /**
     * 删除
     * @param skills
     * @return
     * @author 
     * @version 2016-08-31
     */
    String del_skill(List<HumanSkill> skills);
    /**
     * 删除占用判断
     * @author 
     * @version 2016-08-22
     * @return
     */
    String findOccupy(List<HumanSkill> parts);
    /**
     * 人员技能下拉框带回
     * @author 
     * @version 2016-08-22
     * @return
     */
    public List<HumanSkill> skillDownlist(String orgId);
    public List<HumanSkill> skillDownlist1(String orgId);
    /**
     * 技能等级下拉框带回
     * @author 
     * @version 2016-08-22
     * @return
     */
    public List<HumanSkill> levelDownlist(String orgId,String skillId);
    public List<HumanSkill> levelDownlist1(String orgId,String skillId);
    Page<HumanSkill> findList(Page<HumanSkill> page, HumanSkill humanSkill);

    /**
     * 新增重复判断
     * @author
     * @version 2016-08-22
     * @return
     */
    List<HumanSkill> findSame(String orgId, String level,String levelId,String skillId);
    //新增人员技能
    String insertPrimary(HumanSkill humanSkill);

    //更新人员技能
    String updatePrimary(HumanSkill humanSkill);

    //删除人员技能
    String delPrimary(String dataId);

    //新增工作经历
    String insertForeign(MyInfo myInfo);
    //更新工作经历
    String updateForeign(MyInfo myInfo);
    //删除工作经历（textarea）
    String delExp(String personId,String orgId);
    //删除工作经历（editarea）
    String delForeign(String expId);
    //工作经历列表
    public List<MyInfo> workExplist(String orgId,String personId);
    //新增社会关系
    String insertForeign1(MyInfo myInfo);
    //更新社会关系
    String updateForeign1(MyInfo myInfo);
    //删除社会关系（textarea）
    String delRel(String personId,String orgId);
    //删除社会关系（editrow）
    String delForeign1(String relId);
    //社会关系列表
    public List<MyInfo> rellist(String orgId,String personId);
}
