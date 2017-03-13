package com.jims.sys.api;

import com.jims.sys.entity.SysServiceParam;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * Created by heren on 2016/7/4.
 */
public interface ServiceParamApi {
    /**
     * 查询某一个服务的所有ID
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> listSysServiceParam(String serviceId);

    /**
     * 修改保存Service服务
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams);

    /**
     * 删除对应的服务参数
     * @param sysServiceParams
     * @return
     */
    public int deleteSysServiceParam(List<SysServiceParam> sysServiceParams);

    /***
     * 一次性修改相关数据
     * @param sysServiceParamBeanChangeVo
     * @return
     */
    public int mergeSysServiceParam(BeanChangeVo<SysServiceParam> sysServiceParamBeanChangeVo) ;

    /**
     * 根据自定义服务查询出，该自定义服务应的菜单
     * @param selfServiceId
     * @param orgId
     * @return
     */
    public List<SysServiceParam> findSysServiceParamBySelfServiceId(String selfServiceId, String orgId);
}
