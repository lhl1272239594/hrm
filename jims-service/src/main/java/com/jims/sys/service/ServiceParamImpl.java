package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.ServiceParamApi;
import com.jims.sys.bo.SysServiceParamBo;
import com.jims.sys.entity.SysServiceParam;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by heren on 2016/7/4.
 */
@Service(version = "1.0.0")
public class ServiceParamImpl implements ServiceParamApi {

    @Autowired
    private SysServiceParamBo sysServiceParamBo ;

    /**
     * 根据服务查询所有的服务参数
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> listSysServiceParam(String serviceId) {

        return sysServiceParamBo.findSysServiceParamByServiceId(serviceId);
    }

    /**
     * 更新服务参数
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams) {
        return sysServiceParamBo.mergeSysServiceParam(sysServiceParams);
    }

    /**
     * 删除服务参数
     * @param sysServiceParams
     * @return
     */
    public int deleteSysServiceParam(List<SysServiceParam> sysServiceParams) {
        return sysServiceParamBo.delSysServiceParam(sysServiceParams);
    }

    /**
     * 一次性修改服务数据
     * @param sysServiceParamBeanChangeVo
     * @return
     */
    public int mergeSysServiceParam(BeanChangeVo<SysServiceParam> sysServiceParamBeanChangeVo) {


        return sysServiceParamBo.mergeSysServiceParam(sysServiceParamBeanChangeVo) ;

    }


    /**
     * 根据自定义服务ID查询出所有的服务参数
     * @param selfServiceId
     * @param orgId
     * @return
     */
    public List<SysServiceParam> findSysServiceParamBySelfServiceId(String selfServiceId, String orgId) {
        return sysServiceParamBo.findSysServiceParamBySelfServiceId(selfServiceId,orgId);
    }
}
