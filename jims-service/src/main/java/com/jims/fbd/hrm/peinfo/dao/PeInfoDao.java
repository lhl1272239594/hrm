package com.jims.fbd.hrm.peinfo.dao;

/**
 * 人员管理DAO接口
 * @author 
 * @version 2016-09-22
 */
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.fbd.hrm.peinfo.entity.HumanSkill;
import com.jims.fbd.hrm.peinfo.entity.MyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface PeInfoDao extends CrudDao<MyInfo>
{

    /**
     * 查询我的信息

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<MyInfo> getmyinfo(String personId, String orgId);

    /**
     * 查询人员信息
     * @param myInfo
     * @return
     */
    public List<MyInfo> findHumanList(MyInfo myInfo);

    /**
     * 查询人员信息（登录人信息）
     * @param personId
     * @param orgId
     * @return
     */
    //public List<MyInfo> findHumanList1(MyInfo myInfo);
    public List<MyInfo> findHumanList1(String personId, String orgId);

    /**
     * 根据人员id查询组织机构人员信息
     * @param persionId
     * @return
     */
    public MyInfo getByPersionId(String persionId) ;

    /**
     * 通过persionId删除信息
     * @param id
     * @return
     */
    public Integer deleteByPid(String id);

    /**
     * 重置密码
     * @param staffId
     * @return
     */
    public void back(@Param("staffId") String staffId, @Param("userName") String userName);

    /**
     * 查询

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<HumanSkill> skillList(HumanSkill humanSkill);
    /**
     * 查询是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<HumanSkill> findSkillsame(@Param("orgId") String orgId, @Param("skill") String skill, @Param("skillId") String skillId);

    /**
     * 修改
     *
     * @param humanSkill
     * @return
     */
    public void updateSkill(@Param("HumanSkill") HumanSkill humanSkill);
    /**
     * 新增
     *
     * @param humanSkill
     * @return
     */
    public void insertSkill(@Param("HumanSkill") HumanSkill humanSkill);
    /**
     * 批量删除
     *
     * @param humanSkill
     * @return
     */
    public void deleteSkill(@Param("HumanSkill" ) HumanSkill humanSkill);
    /**
     * 删除占用判断
     *
     * @param humanSkill
     * @return
     */
    public int findOccupy(@Param("HumanSkill" ) HumanSkill humanSkill);
    /**
     * 人员技能、技能等级查询及下拉框带回
     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<HumanSkill> skillDownlist(@Param("orgId") String orgId);
    public List<HumanSkill> skillDownlist1(@Param("orgId") String orgId);
    public List<HumanSkill> levelDownlist(@Param("orgId") String orgId,@Param("skillId") String skillId);
    public List<HumanSkill> levelDownlist1(@Param("orgId") String orgId,@Param("skillId") String skillId);
    List<HumanSkill> findList(HumanSkill humanSkill);
    /**
     * 新增重复判断
     * @return
     * @author
     * @version 2016-08-22
     */
    List<HumanSkill> findSame(@Param("orgId") String orgId, @Param("level") String level,@Param("levelId") String levelId, @Param("skillId") String skillId);
    /**
     * 删除方法
     * @return
     * @author
     * @version 2016-08-22
     */
    void delPrimary(@Param("dataId") String dataId) ;

    /**
     * 修改
     * @param humanSkill
     * @return
     */
    public void updatePrimary(@Param("HumanSkill") HumanSkill humanSkill);
    /**
     * 新增人员技能
     * @param humanSkill
     * @return
     */
    public void insertPrimary(@Param("HumanSkill") HumanSkill humanSkill);
    /**
     * 删除工作经历
     * @param personId
     * @return
     */
    void delExp(@Param("personId") String personId,@Param("orgId") String orgId) ;
    /**
     * 删除工作经历
     * @param expId
     * @return
     */
    void delForeign(@Param("expId") String expId) ;
    /**
     * 工作经历列表
     * @param orgId
     * @return
     */
    public List<MyInfo> workExplist(@Param("orgId") String orgId,@Param("personId") String personId);

    /**
     * 删除社会关系
     * @param orgId
     * @return
     */
    void delRel(@Param("personId") String personId,@Param("orgId") String orgId) ;
    /**
     * 删除社会关系
     * @param relId
     * @return
     */
    void delForeign1(@Param("relId") String relId) ;
    /**
     * 新增社会关系
     * @param myInfo
     * @return
     */
    public void insertForeign1(@Param("MyInfo") MyInfo myInfo);
    /**
     * 更新社会关系
     * @param myInfo
     * @return
     */
    public void updateForeign1(@Param("MyInfo") MyInfo myInfo);
    /**
     * 社会关系列表
     * @param personId
     * @return
     */
    public List<MyInfo> rellist(@Param("orgId") String orgId,@Param("personId") String personId);
}
