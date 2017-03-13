package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DrugTypeDictApi;
import com.jims.sys.dao.DrugTypeDictDao;
import com.jims.sys.entity.DrugTypeDict;


import java.util.List;

/**
 * Created by zhu on 2016/5/14.
 */

@Service(version = "1.0.0")

public class DrugTypeDictImpl extends CrudImplService<DrugTypeDictDao, DrugTypeDict> implements DrugTypeDictApi {

    public List<DrugTypeDict> findAllList() {
        return dao.findAllList();
    }
}
