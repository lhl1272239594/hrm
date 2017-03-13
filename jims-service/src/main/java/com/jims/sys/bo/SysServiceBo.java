
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.dao.ServiceVsMenuDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.dao.SysServicePriceDao;
import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import com.jims.sys.vo.StaffGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 系统服务BAO层
 *
 * @author txb
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SysServiceBo extends CrudImplService<SysServiceDao, SysService>{

    @Autowired
    private SysServicePriceDao priceDao;
    @Autowired
    private ServiceVsMenuDao serviceVsMenuDao;
    @Autowired
    private OrgSelfServiceVsMenuDao selfServiceVsMenuDao;

    /**
     * 查询服务明细全部列表
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findDetailList(String serviceId){
        return priceDao.findListByServiceId(serviceId);
    };

    /**
     * 删除服务数据
     * @param ids 服务的ID集合
     * @return
     * @author fengyuguang
     */
    @Transactional(readOnly = false)
    public String delete(String ids){
        int i = 0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                dao.delete(id[j]);  //删除服务
                priceDao.deleteByServiceId(id[j]);  //删除服务价格明细
                serviceVsMenuDao.deleteByServiceId(id[j]);  //删除服务所有菜单
                i++;
            }
        } catch (Exception e) {
            return i + "";
        }
        return i + "";
    }

    /**
     * 修改保存删除服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    @Transactional(readOnly = false)
    public String saveDetail(StaffGroupVo priceBeanVo){
        if (priceBeanVo != null){
            List<SysServicePrice> inserts = priceBeanVo.getInserted();
            List<SysServicePrice> updates = priceBeanVo.getUpdated();
            List<SysServicePrice> deletes = priceBeanVo.getDeleted();

            if (inserts != null && inserts.size() > 0) {
                for (SysServicePrice sysServicePrice : inserts) {
                    sysServicePrice.preInsert();
                    priceDao.insert(sysServicePrice);
                }
            }
            if (updates != null && updates.size() > 0) {
                for (SysServicePrice sysServicePrice : updates) {
                    sysServicePrice.preUpdate();
                    priceDao.update(sysServicePrice);
                }
            }
            if (deletes != null && deletes.size() > 0) {
                for (SysServicePrice sysServicePrice : deletes) {
                    priceDao.delete(sysServicePrice);
                }
            }
        }
        return "1";
    }
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<SysService> serviceListByTC( String serviceType,String serviceClass) {
        return dao.serviceListByTC(serviceType,serviceClass);
    }

    /**
     * 查询服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<ServiceVsMenu> serviceVsMenuList(String serviceId){
        return serviceVsMenuDao.serviceVsMenuList(serviceId);
    }
    /**
     * 修改保存服务菜单
     * @param serviceVsMenus
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public String saveServiceVsMenu(List<ServiceVsMenu> serviceVsMenus){
        List<ServiceVsMenu> menus = serviceVsMenuDao.serviceVsMenuList(serviceVsMenus.get(0).getServiceId());
        if(menus != null && menus.size() > 0){
            for(ServiceVsMenu menu : menus){
                for (int i=0,j=serviceVsMenus.size();i<j;i++){
                    if(menu.getMenuId().equals(serviceVsMenus.get(i).getMenuId()))
                        break;
                    if(i == j-1){
                        handlerSelfServiceVsMenu(menu);
                    }
                }
            }
            serviceVsMenuDao.deleteByServiceId(serviceVsMenus.get(0).getServiceId());
        }

        for (ServiceVsMenu serviceVsMenu : serviceVsMenus){
                serviceVsMenu.preInsert();
                serviceVsMenuDao.insert(serviceVsMenu);
        }
        return "1";
    };

    /**
     * 当删除平台服务菜单时处理自定义服务对应菜单
     * @param menu
     */
    private void handlerSelfServiceVsMenu(ServiceVsMenu menu){
        OrgSelfServiceVsMenu param = new OrgSelfServiceVsMenu();
        param.setMenuId(menu.getMenuId());
        param.setSysServiceId(menu.getServiceId());
        List<OrgSelfServiceVsMenu> selfMenus = selfServiceVsMenuDao.findListNoJoin(param);
        if(selfMenus != null && selfMenus.size() > 0){
            selfServiceVsMenuDao.deleteByMenuIdAndSysServiceId(menu.getMenuId(),menu.getServiceId());
            for(OrgSelfServiceVsMenu selfMenu : selfMenus){
                Integer maxSort = selfServiceVsMenuDao.findMaxSort(selfMenu);
                maxSort = maxSort == null || maxSort < 0 ? 0 : maxSort;
                String pid = selfMenu.getPid();
                selfMenu.setPid(selfMenu.getMenuId());
                selfMenu.setMenuId(null);
                List<OrgSelfServiceVsMenu> childrens = selfServiceVsMenuDao.findListNoJoin(selfMenu);
                if(childrens != null && childrens.size() > 0){
                    for(OrgSelfServiceVsMenu children : childrens){
                        children.setPid(pid);
                        children.setMenuSort(++maxSort);
                        selfServiceVsMenuDao.update(children);
                    }
                }
            }
        }
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
        state = (state!=null&&!state.equals("")&&state.equals("1")?"":(state!=null&&!state.equals("")&&state.equals("0")?"in":state));
        return dao.findServiceWithPrice(serviceClass,serviceType, persionId, state ,id);
    }
}