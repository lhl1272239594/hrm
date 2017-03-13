package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.SpecimanDictServiceApi;
import com.jims.sys.dao.SpecimanDictDao;
import com.jims.sys.entity.SpecimanDict;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 标本字典
 * @author xueyx
 * @version 2016-05-04
 */
@Service(version = "1.0.0")

public class SpecimanDictServiceImpl extends CrudImplService<SpecimanDictDao, SpecimanDict> implements SpecimanDictServiceApi {

    @Autowired
    private SpecimanDictDao dpecimanDictDao;
    /**
     * 查询科室代码下的检验标本
     * @param检验科室编码 deptCode
     * @return
     */
    public List<SpecimanDict> findListByDeptCode(String deptCode){
        return dpecimanDictDao.findListByDeptCode(deptCode);
    }

}
