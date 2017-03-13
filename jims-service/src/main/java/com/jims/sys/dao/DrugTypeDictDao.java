package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.DrugTypeDict;

import java.util.List;

/**
 * Created by zhu on 2016/5/14.
 */

@MyBatisDao
public interface DrugTypeDictDao extends CrudDao<DrugTypeDict> {
    /***
     * 获取列表\
     * zq
     */
    public List<DrugTypeDict> findAllList();
}
