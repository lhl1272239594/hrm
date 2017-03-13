package com.jims.register.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.TreeUtils;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.dao.MenuDictDao;
import com.jims.sys.dao.ServiceSelfVsSysDao;
import com.jims.sys.entity.ServiceSelfVsSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 机构服务事务管理层
 * @author lgx
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class OrgServiceManagerBo extends CrudImplService<OrgServiceListDao, OrgServiceList> {

    @Autowired
    private OrgSelfServiceListDao selfServiceDao;
    @Autowired
    private OrgSelfServiceVsMenuDao selfServiceVsMenuDao;
    @Autowired
    private ServiceSelfVsSysDao vsSysDao;
    @Autowired
    private MenuDictDao menuDictDao;

    /**
     * 保存选择的服务
     * @param serviceList 服务列表
     */
    public void saveService(List<OrgServiceList> serviceList){
        if(serviceList != null || serviceList.size() > 0){
            for(OrgServiceList orgService : serviceList){
                save(orgService);
            }
        }
    }

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单,
     *                        参数OrgSelfServiceList属性中
     *                        delFlag 为 1 时，属性id为药删除的自定义服务id,多个以‘,’隔开，
     *                        id不为空，orgId为空时，属性menus为服务(id)对应的菜单数据(树形结构)
     *                                              servicesVs 为对应平台服务数据
     *
     *                        其他值时，为修改的自定义服务，当为添加的自定义服务时，包含所有需添加信息。
     */
    public void saveSelfService(List<OrgSelfServiceList> selfServiceList){
        if(selfServiceList != null || selfServiceList.size() > 0){
            for(OrgSelfServiceList service : selfServiceList){
                if("1".equals(service.getDelFlag())){
                    String[] ids = service.getId().split(",");
                    for (int i = 0; i < ids.length; i++){
                        selfServiceDao.delete(ids[i]);
                        selfServiceVsMenuDao.deleteByServiceId(ids[i]);
                        vsSysDao.deleteBySelfServiceId(ids[i]);
                    }
                    continue;
                }
                if(service.getId() != null && service.getOrgId() == null){
                    selfServiceVsMenuDao.deleteByServiceId(service.getId());
                    saveSelfServiceVsMenu(service.getMenus(),service.getId(),null);
                    vsSysDao.deleteBySelfServiceId(service.getId());
                    saveSelfVsSys(service.getServiceVs(),service.getId());
                    continue;
                }
                if (service.getIsNewRecord()){
                    service.preInsert();
                    selfServiceDao.insert(service);
                    saveSelfServiceVsMenu(service.getMenus(),service.getId(),null);
                    saveSelfVsSys(service.getServiceVs(),service.getId());
                }else{
                    service.preUpdate();
                    selfServiceDao.update(service);
                }
            }
        }
    }

    /**
     * 保存自定义服务对应菜单
     * @param menus 菜单序列
     * @param selfServiceID 服务ID
     * @param parentId 菜单父节点
     */
    private void saveSelfServiceVsMenu(List<OrgSelfServiceVsMenu> menus,String selfServiceID,String parentId){
        if(menus != null && menus.size() > 0){
            for(int i=0;i<menus.size();i++){
                OrgSelfServiceVsMenu menu = menus.get(i);
                menu.setSelfServiceId(selfServiceID);
                menu.setPid(parentId);
                menu.setMenuSort(i+1);
                if(menu.getIsNewRecord()){
                    menu.preInsert();
                    selfServiceVsMenuDao.insert(menu);
                }else{
                    menu.preUpdate();
                    selfServiceVsMenuDao.update(menu);
                }
                saveSelfServiceVsMenu(menu.getChildren(),selfServiceID,menu.getMenuId());
            }
        }
    }

    private void saveSelfVsSys(List<ServiceSelfVsSys> serviceSelfVsSys,String selfServiceId){
        if(serviceSelfVsSys != null && serviceSelfVsSys.size() > 0){
            for(ServiceSelfVsSys vs : serviceSelfVsSys){
                vs.setSelfServiceId(selfServiceId);
                vs.preInsert();
                vsSysDao.insert(vs);
            }
        }
    }

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgServiceList> findService(String orgId){
        OrgServiceList serviceParam = new OrgServiceList();
        serviceParam.setOrgId(orgId);
        List<OrgServiceList> services = dao.findList(serviceParam);
        if(services != null && services.size() > 0){
            for(OrgServiceList service : services){
                service.setMenus(TreeUtils.handleTreeList(service.getMenus()));
            }
        }
        return services;
    }

    /**
     * 检索机构自定义的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgSelfServiceList> findSelfService(String orgId){
        OrgSelfServiceList selfService = new OrgSelfServiceList();
        selfService.setOrgId(orgId);
        return selfServiceDao.findList(selfService);
    }

    /**
     * 检索自定义服务
     * @param personId 人员ID
     * @param orgId    机构ID
     * @return 自定义服务
     * @author fengyuguang
     */
    public List<OrgSelfServiceList> findSelfServiceByOrgIdPersonId(String personId, String orgId){
        return selfServiceDao.findSelfServiceByOrgIdPersonId(personId,orgId);
    }

    /**
     * 检索机构自定义菜单
     * @param selfServiceId 自定义服务Id
     * @param isTree 是否为树形结构
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(String selfServiceId,boolean isTree){
        OrgSelfServiceVsMenu selfServiceVsMenu = new OrgSelfServiceVsMenu();
        selfServiceVsMenu.setSelfServiceId(selfServiceId);
        List<OrgSelfServiceVsMenu> menus = selfServiceVsMenuDao.findList(selfServiceVsMenu);
        if(isTree && menus != null && menus.size() > 1){
            Map<String,String> keyMap = new HashMap<String, String>();
            keyMap.put("id","menuId");
            keyMap.put("pid","pid");
            keyMap.put("children","children");
            return TreeUtils.handleTreeList(menus,keyMap);
        }
        return menus;
    }

    public List<OrgSelfServiceVsMenu> findSelfServiceMenu(String serviceId, String roleId,boolean isTree) {

        List<OrgSelfServiceVsMenu> orgSelfServiceVsMenus = selfServiceVsMenuDao.findSelfServiceId(serviceId, roleId);
        if(isTree && orgSelfServiceVsMenus != null && orgSelfServiceVsMenus.size() > 1){
        Map<String,String> keyMap = new HashMap<String, String>();
        keyMap.put("id","menuId");
        keyMap.put("pid","pid");
        keyMap.put("children","children");

        return TreeUtils.handleTreeList(orgSelfServiceVsMenus,keyMap);
        }
        return orgSelfServiceVsMenus;
    }

    public List<ServiceSelfVsSys> findVsSys(String selfServiceId){
        ServiceSelfVsSys vs = new ServiceSelfVsSys();
        vs.setSelfServiceId(selfServiceId);
        return vsSysDao.findList(vs);
    }

    public List<OrgSelfServiceVsMenu> findSelfServiceVsButton(String serviceId, String roleId,boolean isTree) {

        List<OrgSelfServiceVsMenu> orgSelfServiceVsMenus = selfServiceVsMenuDao.findSelfServiceVsButton(serviceId, roleId);
        if(isTree && orgSelfServiceVsMenus != null && orgSelfServiceVsMenus.size() > 1){
            Map<String,String> keyMap = new HashMap<String, String>();
            keyMap.put("id","menuId");
            keyMap.put("pid","pid");
            keyMap.put("children","children");

            return TreeUtils.handleTreeList(orgSelfServiceVsMenus,keyMap);
        }
        return orgSelfServiceVsMenus;
    }

}