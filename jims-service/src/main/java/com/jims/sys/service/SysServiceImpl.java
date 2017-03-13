package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.SysServiceApi;
import com.jims.sys.bo.SysServiceBo;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import com.jims.sys.vo.StaffGroupVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 系统服务imp层
 *
 * @author txb
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class SysServiceImpl implements SysServiceApi {
    @Autowired
    private SysServiceBo sysServiceBo;
    @Autowired
    private SysServiceDao sysServiceDao;

    /**
     * 保存修改
     * @param sysService
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Override
    public String save(SysService sysService) {
        if (sysService.getServiceImage() == null){
            sysService.setServiceImage(sysServiceBo.get(sysService.getId()).getServiceImage());
        }
        return sysServiceBo.save(sysService);
    }
    /**
     * 修改保存服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public String saveDetail(StaffGroupVo priceBeanVo){
        return sysServiceBo.saveDetail(priceBeanVo);
    }
    /**
     * 修改保存服务菜单
     * @param serviceVsMenus
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Override
    public String saveServiceVsMenu(List<ServiceVsMenu> serviceVsMenus) {
        return sysServiceBo.saveServiceVsMenu(serviceVsMenus);
    }

    /**
     * 删除
     * @param ids
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Override
    public String delete(String ids) {
        return sysServiceBo.delete(ids);
    }
    /**
     * 查询全部列表
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Override
    public List<SysService> findList() {
        return sysServiceBo.findList(new SysService());
    }
    /**
     * 查询服务明细全部列表
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findDetailList(String serviceId){
        return sysServiceBo.findDetailList(serviceId);
    }
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Override
    public List<SysService> serviceListByTC(String serviceType, String serviceClass) {
        return sysServiceBo.serviceListByTC(serviceType,serviceClass);
    }
    /**
     * 查询服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Override
    public List<ServiceVsMenu> serviceVsMenuList(String serviceId) {
        return sysServiceBo.serviceVsMenuList(serviceId);
    }


    /**
     * 检索不同人群的服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
     * @param id  sys_service.id
     * @return
     */
    public List<SysService> findServiceWithPrice(String serviceClass,String serviceType,String persionId, String state ,String id){
        return sysServiceBo.findServiceWithPrice(serviceClass,serviceType, persionId, state ,id);
    }

    /**
     * 根据id查询服务
     * @param id 服务ID
     * @return 服务
     * @author fengyuguang
     */
    @Override
    public SysService get(String id) {
        return sysServiceDao.get(id);
    }

    ;
}
