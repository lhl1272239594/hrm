package com.jims.fbd.hrm.contract.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.contract.entity.ContractType;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface ContractTypeApi {


    /**
     * 合同类型查询
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public Page<ContractType> findList(Page<ContractType> page, ContractType contractType);
    /**
     * 合同类型查询--不带分页
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public List<ContractType> findAllList(ContractType contractType);
    /**
     * 合同类型名称重复校验
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public List<ContractType> findBoolean(ContractType contractType);


    /**
     * 合同类型新增保存方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public String insertPrimary(ContractType contractType);

    /**
     * 合同类型修改方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public String updatePrimary(ContractType contractType);
    /**
     * 合同类型删除方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    public String delPrimary(String dataId);

}
