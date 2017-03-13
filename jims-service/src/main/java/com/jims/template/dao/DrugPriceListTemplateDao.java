package com.jims.template.dao;

import com.jims.template.entity.DrugPriceListTemplate;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
* 药品价格模板Dao
* @author lgx
* @version 2016-08-12
*/
@MyBatisDao
public interface DrugPriceListTemplateDao extends CrudDao<DrugPriceListTemplate> {

    public Integer sum(String area);

    public int executeInsertSql(@Param("sql")String sql);
}