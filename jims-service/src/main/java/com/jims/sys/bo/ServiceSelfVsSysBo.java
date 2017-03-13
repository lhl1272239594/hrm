package com.jims.sys.bo;

import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.sys.dao.ServiceSelfVsSysDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 机构服务自定义对应系统服务bo
* @author lgx
* @version 2016-07-11
*/
@Service
@Transactional(readOnly = false)
public class ServiceSelfVsSysBo extends CrudImplService<ServiceSelfVsSysDao, ServiceSelfVsSys>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<ServiceSelfVsSys> list) {
        if(list != null && list.size() > 0) {
            for(ServiceSelfVsSys entity : list) {
                save(entity);
            }
        }
    }
}