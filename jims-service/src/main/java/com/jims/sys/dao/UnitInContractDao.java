package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.UnitInContract;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@MyBatisDao
public interface UnitInContractDao extends CrudDao<UnitInContract>{
    public List<String> findTypeList(UnitInContract unitInContract);


    /**
     * 查询全部数据
     * @param unitInContract
     * @return
     * @author wei
     */
    public List<UnitInContract> findAllLists(String orgId,UnitInContract unitInContract);


    /**
     * 根据拼音码查询
     * @param inputCode 拼音码
     * @return
     * @author wei
     */
    public List<UnitInContract> getInputCode(String orgId,String inputCode);
}
