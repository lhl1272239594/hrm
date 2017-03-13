package com.jims.fbd.hrm.contract.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.contract.api.ContractTypeApi;
import com.jims.fbd.hrm.contract.bo.ContractTypeBo;
import com.jims.fbd.hrm.contract.dao.ContractTypeDao;
import com.jims.fbd.hrm.contract.entity.ContractType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class ContractTypeImpl implements ContractTypeApi {
    @Autowired
    private ContractTypeBo contractTypeBo;
    @Autowired
    private ContractTypeDao contractTypeDao;

    /**
     * 合同类型查询
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public Page<ContractType> findList(Page<ContractType> page, ContractType contractType) {

        return contractTypeBo.findList(page,contractType);
    }
    /**
     * 合同类型查询--不带分页
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public List<ContractType> findAllList(ContractType contractType) {

        return contractTypeBo.findAllList(contractType);
    }
    /**
     * 合同类型名称重复校验
     * @return
     * @author yangchen
     * @version 2016-08-23
     */

    @Override
    public List<ContractType> findBoolean(ContractType contractType) {

        return contractTypeBo.findBoolean(contractType);
    }
    /**
     * 合同类型新增保存方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */

    @Override
    public String insertPrimary(ContractType contractType) {

        return contractTypeBo.insertPrimary(contractType);
    }
    /**
     * 合同类型修改方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String updatePrimary(ContractType contractType) {

        return contractTypeBo.updatePrimary(contractType);
    }
    /**
     * 合同类型删除方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String delPrimary(String dataId) {
        try {
            contractTypeBo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

}


