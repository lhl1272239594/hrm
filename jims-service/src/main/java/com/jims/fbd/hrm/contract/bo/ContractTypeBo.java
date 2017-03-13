
package com.jims.fbd.hrm.contract.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.contract.dao.ContractTypeDao;
import com.jims.fbd.hrm.contract.entity.ContractType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author yangchen
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class ContractTypeBo extends CrudImplService<ContractTypeDao, ContractType>{
    /**
     * 合同类型查询
     * @return
     * @author yangchen
     * @version 2016-08-23
     */

    public Page<ContractType> findList(Page<ContractType> page, ContractType contractType ) {

        contractType.setPage(page);
        page.setList(dao.findList(contractType));
        return page;
    }
    /**
     * 合同类型查询--不带分页
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public List<ContractType> findAllList(ContractType contractType){

        return dao.findAllList(contractType);
    }
    /**
     * 合同类型名称重复校验
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public List<ContractType> findBoolean(ContractType contractType) {

        return dao.findBoolean(contractType);
    }
    /**
     * 合同类型新增保存方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public String insertPrimary(ContractType contractType) {

        return dao.insertPrimary(contractType)+"";
    }
    /**
     * 合同类型修改方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */

    public String updatePrimary(ContractType contractType) {

        return dao.updatePrimary(contractType)+"";
    }
    /**
     * 合同类型删除方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public void delPrimary(String dataId) {

        dao.delPrimary(dataId);
    }
}