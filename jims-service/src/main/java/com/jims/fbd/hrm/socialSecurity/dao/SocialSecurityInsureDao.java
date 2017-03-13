package com.jims.fbd.hrm.socialSecurity.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsure;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsurePerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface SocialSecurityInsureDao extends CrudDao<SocialSecurityInsurePerson>{
  /**
   * 业务处理：查询-分页
   *
   * @return
   */

  List<SocialSecurityInsurePerson> findList(SocialSecurityInsurePerson socialSecurityInsurePerson);
  /**
   * 业务处理：查询-无分页
   *
   * @return
   */
  List<SocialSecurityInsurePerson> findAllList(@Param("orgId") String orgId);
  /**
   * 业务处理：删除
   *
   * @return
   */
  void delPrimary(@Param("SocialSecurityInsure") SocialSecurityInsure socialSecurityInsure);
  /**
   * 新增社保之前先删除该员工社保
   * @param socialSecurityInsurePerson
   * @return
   */
  void deletePrimary(@Param("SocialSecurityInsurePerson") SocialSecurityInsurePerson socialSecurityInsurePerson);

}