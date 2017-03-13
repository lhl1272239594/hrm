package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.sys.api.UnitInContractApi;
import com.jims.sys.bo.UnitInContractBo;
import com.jims.sys.entity.UnitInContract;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */

@Service(version = "1.0.0")

public class UnitInContractImpl  implements UnitInContractApi{
    @Autowired
    private UnitInContractBo bo;

    @Override
    public Page<UnitInContract> findPage(Page<UnitInContract> page, UnitInContract unitInContract) {
        return bo.findPage(page,unitInContract);
    }

    /**
     * 删除
     * @param ids
     * @return
     * @author wei
     */
    @Override
    public String delete(String ids) {
        return bo.delete(ids);
    }
    @Override
    public List<String> findTypeList() {
        return bo.findTypeList();
    }

    @Override
    public UnitInContract get(String id) {
        return bo.get(id);
    }

    /**
     * 查询全部数据
     * @param orgId
     * @param page
     * @param unitInContract
     * @return
     * @author wei
     */
    @Override
    public Page<UnitInContract> findAllPage(String orgId, Page<UnitInContract> page, UnitInContract unitInContract) {
        return bo.findAllPage(orgId,page,unitInContract);
    }

    /**
     * 增加数据
     * @param unitInContract
     * @return
     * @author wei
     */
    @Override
    public String save(UnitInContract unitInContract) {
        System.out.println(unitInContract.getId());
        return bo.save(unitInContract);
    }

    /**
     * 修改数据
     * @param unitInContract
     * @return
     * @author wei
     */
    public String update(UnitInContract unitInContract) {
        return bo.update(unitInContract);
    }

    /**
     *根据拼音码查询
     * @param orgId
     * @param inputCode
     * @return
     * @author wei
     */
    public List<UnitInContract> getInputCode(String orgId,String inputCode) {
        return bo.getInputCode(orgId, inputCode);
    }

}
