package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.sys.api.ServiceSelfVsSysApi;
import com.jims.sys.bo.ServiceSelfVsSysBo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 机构服务自定义对应系统服务service
* @author lgx
* @version 2016-07-11
*/
@Service(version = "1.0.0")
public class ServiceSelfVsSysServiceImpl implements ServiceSelfVsSysApi{

    @Autowired
    private ServiceSelfVsSysBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public ServiceSelfVsSys get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<ServiceSelfVsSys> findList(ServiceSelfVsSys entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<ServiceSelfVsSys> findPage(Page<ServiceSelfVsSys> page, ServiceSelfVsSys entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(ServiceSelfVsSys entity) {
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
    public String save(List<ServiceSelfVsSys> list) {
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