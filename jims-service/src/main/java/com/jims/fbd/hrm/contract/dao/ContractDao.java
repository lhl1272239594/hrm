package com.jims.fbd.hrm.contract.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.contract.entity.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface ContractDao extends CrudDao<Contract>{

  /**
   * 业务处理：查询
   *
   * @return
   */
  public List<Contract> findList(Contract contract);
  /**
   * 业务处理：合同名称重复校验
   *
   * @return
   *
   */
  public List<Contract> findContractBoolean(@Param("orgId") String orgId,@Param("contractName") String contractName);

  public List<Contract> findSignNum(@Param("orgId") String orgId,@Param("userId") String userId);

  /**
   * 业务处理：删除
   *
   * @return
   */
  public void delPrimary(@Param("dataId") String dataId) ;
  /**
   * 查询合同编号是否已存在

   * @return
   * @author ZYG
   * @version 2016-08-22
   */
  public List<Contract> findPartsame(@Param("orgId") String orgId, @Param("contractCode") String contractCode,@Param("contractId") String contractId);

}