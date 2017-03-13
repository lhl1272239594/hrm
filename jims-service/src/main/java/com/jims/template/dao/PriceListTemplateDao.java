package com.jims.template.dao;

import com.jims.template.entity.PriceListTemplate;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
* 价表模板Dao
* @author lgx
* @version 2016-08-09
*/
@MyBatisDao
public interface PriceListTemplateDao extends CrudDao<PriceListTemplate> {

    public Integer sum(String area);

    public int executeInsertSql(@Param("sql")String sql);
}