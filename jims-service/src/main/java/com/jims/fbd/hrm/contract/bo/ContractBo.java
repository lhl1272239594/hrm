
package com.jims.fbd.hrm.contract.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.contract.dao.ContractDao;
import com.jims.fbd.hrm.contract.entity.Contract;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author txb
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class ContractBo extends CrudImplService<ContractDao, Contract>{
    /**
     * 业务处理：查询
     *
     * @return
     */
   public Page<Contract> findList(Page<Contract> page, Contract contract ) {
       contract.setPage(page);
       page.setList(dao.findList(contract));
       return page;
   }
    /**
     * 业务处理：合同名称重复校验
     *
     * @return
     */
    public List<Contract> findContractBoolean(String orgId,String contractName) {

        return dao.findContractBoolean(orgId,contractName);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(Contract contract) {

        return dao.insertPrimary(contract)+"";
    }
    /**
     *查询合同编号是否重复
     * @return
     * @author ZYG
     */
    public List<Contract> findPartsame(String orgId,String contractCode,String contractId){
        return dao.findPartsame(orgId,contractCode,contractId);
    }
    public List<Contract> findSignNum(String orgId,String userId ){
        return dao.findSignNum(orgId,userId);
    }
    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(Contract contract) {

        return dao.updatePrimary(contract)+"";
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    public void delPrimary(String dataId) {

        dao.delPrimary(dataId);
    }
}