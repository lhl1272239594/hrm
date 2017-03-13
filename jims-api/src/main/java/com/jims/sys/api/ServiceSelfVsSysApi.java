package com.jims.sys.api;

import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 机构服务自定义对应系统服务Api
* @author lgx
* @version 2016-07-11
*/
public interface ServiceSelfVsSysApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public ServiceSelfVsSys get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<ServiceSelfVsSys> findList(ServiceSelfVsSys entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<ServiceSelfVsSys> findPage(Page<ServiceSelfVsSys> page, ServiceSelfVsSys entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(ServiceSelfVsSys entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<ServiceSelfVsSys> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;
}