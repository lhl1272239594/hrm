package com.jims.fbd.hrm.contract.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.contract.entity.Contract;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface ContractApi {

    /**
     * 业务处理：查询
     *
     * @return
     */

    public Page<Contract> findList(Page<Contract> page, Contract contract);
    /**
     * 业务处理：合同名称重复校验
     *
     * @return
     */
    public List<Contract> findContractBoolean(String orgId,String contractName);
    /**
     * 合同编号是否重复
     * @author ZYG
     * @version 2016-08-22
     * @return
     */
    List<Contract> findPartsame(String orgId, String contractCode, String contractId);

    List<Contract> findSignNum(String orgId, String userId);

    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(Contract contract);

    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(Contract contract);
    /**
     * 业务处理：删除
     *
     * @return
     */
    public String delPrimary(String dataId);

}
