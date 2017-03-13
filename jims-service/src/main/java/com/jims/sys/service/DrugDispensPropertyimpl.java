package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DrugDispensPropertyApi;
import com.jims.sys.dao.DrugDispensPropertyDao;
import com.jims.sys.entity.DrugDispensProperty;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service(version = "1.0.0")

public class DrugDispensPropertyimpl extends CrudImplService<DrugDispensPropertyDao, DrugDispensProperty> implements DrugDispensPropertyApi{

    @Override
    public List<String> findTypeList() {
        return dao.findTypeList(new DrugDispensProperty());
    }
}
