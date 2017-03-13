package com.jims.fbd.hrm.contract.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.contract.entity.ContractType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface ContractTypeDao extends CrudDao<ContractType>{
  /**
   * 合同类型查询
   * @return
   * @author yangchen
   * @version 2016-08-23
   */
  public List<ContractType> findList(ContractType contractType);
  /**
   * 合同类型查询--不带分页
   * @return
   * @author yangchen
   * @version 2016-08-23
   */
  public List<ContractType> findAllList(ContractType contractType);
  /**
   * 合同类型名称重复校验
   * @return
   * @author yangchen
   * @version 2016-08-23
   */
  public List<ContractType> findBoolean(ContractType contractType);

  public void delPrimary(@Param("dataId") String dataId) ;

}