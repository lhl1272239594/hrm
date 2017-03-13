package com.jims.sys.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.UnitInContractDao;
import com.jims.sys.entity.UnitInContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wei on 2016/6/22.
 */
@Service
@Component
@Transactional(readOnly = false)
public class UnitInContractBo extends CrudImplService<UnitInContractDao,UnitInContract> {

    @Autowired
    private UnitInContractDao unitInContractDao;


    public List<String> findTypeList() {
        return dao.findTypeList(new UnitInContract());
    }

    /**
     * 查询全部数据
     * @param page
     * @param unitInContract
     * @return
     * @author wei
     */
    public Page<UnitInContract> findAllPage(String orgId,Page<UnitInContract> page, UnitInContract unitInContract ) {
        unitInContract.setPage(page);
        page.setList(dao.findAllLists(orgId,unitInContract));
        return page;
    }

    /**
     *增加方法
     * @param unitInContract
     * @return
     * @author wei
     */
    public String save(UnitInContract unitInContract) {
        return dao.insert(unitInContract)+"";
    }

    /**
     *修改
     * @param unitInContract
     * @return
     * @author wei
     */
    public String update(UnitInContract unitInContract) {
        return dao.update(unitInContract)+"";
    }



    /**
     * 拼音码查询
     * @param inputCode
     * @return
     * @author wei
     */
    public List<UnitInContract> getInputCode(String orgId,String inputCode) {
        List<UnitInContract> list =dao.getInputCode(orgId, inputCode);
        return list;
    }

}
