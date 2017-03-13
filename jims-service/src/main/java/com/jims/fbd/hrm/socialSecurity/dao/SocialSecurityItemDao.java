package com.jims.fbd.hrm.socialSecurity.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface SocialSecurityItemDao extends CrudDao<SocialSecurityItem>{

  /**
   * 业务处理：查询-分页
   *
   * @return
   */
  List<SocialSecurityItem> findList(SocialSecurityItem socialSecurityItem);
  /**
   * 业务处理：查询-无分页
   *
   * @return
   */
  List<SocialSecurityItem> findAllList(@Param("orgId") String orgId);
  /**
   * 新增重复判断
   * @return
   * @author
   * @version 2016-08-22
   */
  List<SocialSecurityItem> findSame(@Param("orgId") String orgId, @Param("editItemDes") String editItemDes, @Param("id") String id);

  /**
   * 删除占用判断
   *
   * @param socialSecurityItem
   * @return
   */
  public int findOccupy(@Param("SocialSecurityItem" ) SocialSecurityItem socialSecurityItem);

  void delPrimary(@Param("dataId") String dataId) ;

}