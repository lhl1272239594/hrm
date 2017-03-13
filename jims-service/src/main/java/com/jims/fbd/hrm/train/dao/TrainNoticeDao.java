package com.jims.fbd.hrm.train.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.train.entity.TrainNoticePerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface TrainNoticeDao extends CrudDao<TrainNoticePerson>{

  //查询培训通知列表

  public List<TrainNoticePerson> findList(TrainNoticePerson trainNoticePerson);
  //查询参与该培训通知的人
  public List<TrainNoticePerson> findNoticeToPerson(TrainNoticePerson trainNoticePerson);
  //删除主表信息
  public void delPrimary(TrainNoticePerson trainNotice) ;
  //删除从表信息

  public void delForeign(TrainNoticePerson trainPlanPerson) ;
  //查询名称是否重复
  public int checkName(@Param("id")String id,@Param("orgId") String orgId,@Param("name") String name);
  //发布培训通知
  public void dataPublish(@Param("id") String id);
  //我的培训通知列表
  public  List<TrainNoticePerson> mytrain(TrainNoticePerson trainNoticePerson);
}