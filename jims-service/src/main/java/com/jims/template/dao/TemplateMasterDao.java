package com.jims.template.dao;

import com.jims.template.entity.TemplateMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 模板信息Dao
* @author lgx
* @version 2016-08-09
*/
@MyBatisDao
public interface TemplateMasterDao extends CrudDao<TemplateMaster> {


    /**
     *根据区域查询此区域在表中是否唯一
     * @param entity
     * @return
     */
    public List<TemplateMaster>  getArea(TemplateMaster entity);

    /**
     *向表中插入数据并返回id
     * @param entity
     * @return
     */
    public int insertById(TemplateMaster entity);
}