package com.jims.template.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.template.entity.DrugPriceListTemplate;
import com.jims.template.api.DrugPriceListTemplateApi;
import com.jims.template.bo.DrugPriceListTemplateBo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 药品价格模板service
* @author lgx
* @version 2016-08-12
*/
@Service(version = "1.0.0")
public class DrugPriceListTemplateServiceImpl implements DrugPriceListTemplateApi{

    @Autowired
    private DrugPriceListTemplateBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public DrugPriceListTemplate get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<DrugPriceListTemplate> findList(DrugPriceListTemplate entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<DrugPriceListTemplate> findPage(Page<DrugPriceListTemplate> page, DrugPriceListTemplate entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(DrugPriceListTemplate entity) {
        try {
            bo.save(entity);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<DrugPriceListTemplate> list) {
        try {
            bo.save(list);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
}