/**
 * Created by Administrator on 2016/6/25.
 */
package com.jims.asepsis.dao;

import com.jims.asepsis.entity.StaffDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 员工(用户)表管理DAO接口（操作员，查对者）
 * @author louhuili
 * @version 2016-06-27
 */
@MyBatisDao
public interface StaffDictDao extends CrudDao<StaffDict> {

    /**
     * 员工(用户)表
     * @return
     * @author pq
     */
    public List<StaffDict> listStaffDict();
}