package com.jims.fbd.hrm.contract.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.contract.api.ContractProbationPeriodApi;
import com.jims.fbd.hrm.contract.bo.ContractProbationPeriodBo;
import com.jims.fbd.hrm.contract.dao.ContractProbationPeriodDao;
import com.jims.fbd.hrm.contract.entity.ContractProbationPeriod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class ContractProbationPeriodImpl implements ContractProbationPeriodApi {
    @Autowired
    private ContractProbationPeriodBo contractProbationPeriodBo;
    @Autowired
    private ContractProbationPeriodDao contractProbationPeriodDao;

    /**
     * 合同试用期查询
     * @return
     * @author yangchen
     */
    @Override
    public Page<ContractProbationPeriod> findList(Page<ContractProbationPeriod> page, ContractProbationPeriod contractProbationPeriod) {

        return contractProbationPeriodBo.findList(page,contractProbationPeriod);
    }
    /**
     * 合同试用期查询--不带分页
     * @return
     * @author yangchen
     */
    @Override
    public List<ContractProbationPeriod> findAllList(ContractProbationPeriod contractProbationPeriod) {

        return contractProbationPeriodBo.findAllList(contractProbationPeriod);
    }
    /**
     * 合同试用期重复校验
     * @return
     * @author yangchen
     */

    @Override
    public List<ContractProbationPeriod> findBoolean(ContractProbationPeriod contractProbationPeriod) {

        return contractProbationPeriodBo.findBoolean(contractProbationPeriod);
    }
    /**
     * 合同试用期新增保存方法
     * @return
     * @author yangchen
     */

    @Override
    public String insertPrimary(ContractProbationPeriod contractProbationPeriod) {

        return contractProbationPeriodBo.insertPrimary(contractProbationPeriod);
    }
    /**
     * 合同试用期修改方法
     * @return
     * @author yangchen
     */
    @Override
    public String updatePrimary(ContractProbationPeriod contractProbationPeriod) {

        return contractProbationPeriodBo.updatePrimary(contractProbationPeriod);
    }
    /**
     * 合同试用期删除方法
     * @return
     * @author yangchen
     */
    @Override
    public String delPrimary(String dataId) {
        try {
            contractProbationPeriodBo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

}


