package com.jims.fbd.hrm.contract.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.contract.entity.ContractProbationPeriod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2017-03-07
 */
@MyBatisHrmDao
public interface ContractProbationPeriodDao extends CrudDao<ContractProbationPeriod>{
  /**
   * 合同试用期查询
   * @return
   * @author yangchen
   */
  public List<ContractProbationPeriod> findList(ContractProbationPeriod contractProbationPeriod);
  /**
   * 合同试用期查询--不带分页
   * @return
   * @author yangchen
   */
  public List<ContractProbationPeriod> findAllList(ContractProbationPeriod contractProbationPeriod);
  /**
   * 合同试用期重复校验
   * @return
   * @author yangchen
   */
  public List<ContractProbationPeriod> findBoolean(ContractProbationPeriod contractProbationPeriod);

  public void delPrimary(@Param("dataId") String dataId) ;

}