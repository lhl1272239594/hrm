
package com.jims.fbd.hrm.contract.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.contract.dao.ContractProbationPeriodDao;
import com.jims.fbd.hrm.contract.entity.ContractProbationPeriod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author yangchen
 * @version 2017-03-07
 */
@Service
@Component
@Transactional(readOnly = false)
public class ContractProbationPeriodBo extends CrudImplService<ContractProbationPeriodDao, ContractProbationPeriod>{
    /**
     * 合同试用期查询
     * @return
     * @author yangchen
     */

    public Page<ContractProbationPeriod> findList(Page<ContractProbationPeriod> page, ContractProbationPeriod contractProbationPeriod ) {

        contractProbationPeriod.setPage(page);
        page.setList(dao.findList(contractProbationPeriod));
        return page;
    }
    /**
     * 合同试用期查询--不带分页
     * @return
     * @author yangchen
     */
    public List<ContractProbationPeriod> findAllList(ContractProbationPeriod contractProbationPeriod){

        return dao.findAllList(contractProbationPeriod);
    }
    /**
     * 合同试用期重复校验
     * @return
     * @author yangchen
     */
    public List<ContractProbationPeriod> findBoolean(ContractProbationPeriod contractProbationPeriod) {

        return dao.findBoolean(contractProbationPeriod);
    }
    /**
     * 合同试用期新增保存方法
     * @return
     * @author yangchen
     */
    public String insertPrimary(ContractProbationPeriod contractProbationPeriod) {

        return dao.insertPrimary(contractProbationPeriod)+"";
    }
    /**
     * 合同试用期修改方法
     * @return
     * @author yangchen
     */

    public String updatePrimary(ContractProbationPeriod contractProbationPeriod) {

        return dao.updatePrimary(contractProbationPeriod)+"";
    }
    /**
     * 合同试用期删除方法
     * @return
     * @author yangchen
     */
    public void delPrimary(String dataId) {

        dao.delPrimary(dataId);
    }
}