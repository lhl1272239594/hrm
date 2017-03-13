package com.jims.fbd.hrm.socialSecurity.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 节日设置DAO接口
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface SocialSecurityPlanDao extends CrudDao<SocialSecurityPlan>{
  /**
   * 业务处理：社保方案查询-分页
   *
   * @return
   */
  List<SocialSecurityPlan> findList(SocialSecurityPlan socialSecurityPlan);
  /**
   * 业务处理：社保方案详细信息查询
   *
   * @return
   */
  List<SocialSecurityPlan> findDetailList(SocialSecurityPlan socialSecurityPlan);
  /**
   * 业务处理：查询-无分页
   *
   * @return
   */
  List<SocialSecurityPlan> findAllList(SocialSecurityPlan socialSecurityPlan);
  /**
   * 删除占用判断
   *
   * @param socialSecurityPlan
   * @return
   */
  public int findOccupy(@Param("SocialSecurityPlan" ) SocialSecurityPlan socialSecurityPlan);
  /**
   * 批量删除
   *
   * @param socialSecurityPlan
   * @return
   */
  void delPrimary(@Param("SocialSecurityPlan") SocialSecurityPlan socialSecurityPlan);
  /**
   * 业务处理：社保方案详细信息删除
   *
   * @return
   */
  void delForeign(@Param("dataId") String dataId) ;
  /**
   * 新增重复判断
   * @return
   * @author
   * @version 2016-08-22
   */
  List<SocialSecurityPlan> findSame(@Param("orgId") String orgId, @Param("ssPlanDes") String ssPlanDes, @Param("id") String id);
}