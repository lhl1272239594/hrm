package com.jims.fbd.hrm.contract.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.contract.api.ContractApi;
import com.jims.fbd.hrm.contract.bo.ContractBo;
import com.jims.fbd.hrm.contract.dao.ContractDao;
import com.jims.fbd.hrm.contract.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class ContractImpl implements ContractApi {
    @Autowired
    private ContractBo contractBo;
    @Autowired
    private ContractDao contractDao;


    /**
     * 业务处理：查询
     *
     * @return
     */
    @Override
    public Page<Contract> findList(Page<Contract> page, Contract contract) {

        return contractBo.findList(page,contract);
    }
    /**
     * 业务处理：合同名称重复校验
     *
     * @return
     */
    @Override
    public List<Contract> findContractBoolean(String orgId,String contractName) {

        return contractBo.findContractBoolean(orgId,contractName);
    }
    /**
     * 查询合同编号是否重复
     * @return
     * @author ZYG
     * @version 2016-08-23
     */
    @Override
    public List<Contract> findPartsame(String orgId,String contractCode,String contractId) {
        return contractBo.findPartsame(orgId,contractCode,contractId);
    }

    @Override
    public List<Contract> findSignNum(String orgId,String userId) {
        return contractBo.findSignNum(orgId,userId);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(Contract contract) {

        return contractBo.insertPrimary(contract);
    }
    /**
     * 业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(Contract contract) {

        return contractBo.updatePrimary(contract);
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Override
    public String delPrimary(String dataId) {
        try {
            contractBo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

}


