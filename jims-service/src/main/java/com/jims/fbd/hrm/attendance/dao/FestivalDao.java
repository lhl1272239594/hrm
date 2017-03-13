package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.Festival;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 节日设置DAO接口
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface FestivalDao extends CrudDao<Festival>{

  public List<Festival> findFesList(Festival festival);

  public List<Festival> findFesDateList(@Param("orgId") String orgId,@Param("fesId") String fesId);

  public List<Festival> findFesBoolean(@Param("orgId") String orgId, @Param("fesDesId") String fesDesId, @Param("yearId") String yearId,@Param("fesId") String fesId);

  public void delPrimary(Festival festival) ;


  public void delForeign(@Param("Festival" ) Festival festival) ;
  public void delForeign1(@Param("Festival" ) Festival festival) ;
  //删除从表行信息
  public void delFestival(@Param("fesId" ) String fesId);
}