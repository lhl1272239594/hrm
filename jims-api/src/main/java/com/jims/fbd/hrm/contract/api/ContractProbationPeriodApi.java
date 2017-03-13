package com.jims.fbd.hrm.contract.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.contract.entity.ContractProbationPeriod;

import java.util.List;

/**
 * Created by yangchen on 2017/03/07.
 */
public interface ContractProbationPeriodApi {


    /**
     * 合同试用期查询
     * @return
     * @author yangchen
     */
    public Page<ContractProbationPeriod> findList(Page<ContractProbationPeriod> page, ContractProbationPeriod contractProbationPeriod);
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


    /**
     * 合同试用期新增保存方法
     * @return
     * @author yangchen
     */
    public String insertPrimary(ContractProbationPeriod contractProbationPeriod);

    /**
     * 合同试用期修改方法
     * @return
     * @author yangchen
     */
    public String updatePrimary(ContractProbationPeriod contractProbationPeriod);
    /**
     * 合同试用期删除方法
     * @return
     * @author yangchen
     */
    public String delPrimary(String dataId);

}
