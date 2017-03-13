package com.jims.template.bo;

import com.jims.template.entity.DrugPriceListTemplate;
import com.jims.template.dao.DrugPriceListTemplateDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 药品价格模板bo
* @author lgx
* @version 2016-08-12
*/
@Service
@Transactional(readOnly = false)
public class DrugPriceListTemplateBo extends CrudImplService<DrugPriceListTemplateDao, DrugPriceListTemplate>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<DrugPriceListTemplate> list) {
        if(list != null && list.size() > 0) {
            for(DrugPriceListTemplate entity : list) {
                save(entity);
            }
        }
    }
}