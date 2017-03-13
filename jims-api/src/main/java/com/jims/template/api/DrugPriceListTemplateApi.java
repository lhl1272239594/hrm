package com.jims.template.api;

import com.jims.template.entity.DrugPriceListTemplate;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 药品价格模板Api
* @author lgx
* @version 2016-08-12
*/
public interface DrugPriceListTemplateApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public DrugPriceListTemplate get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<DrugPriceListTemplate> findList(DrugPriceListTemplate entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<DrugPriceListTemplate> findPage(Page<DrugPriceListTemplate> page, DrugPriceListTemplate entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(DrugPriceListTemplate entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<DrugPriceListTemplate> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;
}