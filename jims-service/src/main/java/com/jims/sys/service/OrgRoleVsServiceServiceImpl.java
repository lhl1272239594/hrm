package com.jims.sys.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.sys.api.OrgRoleVsServiceApi;
import com.jims.sys.bo.OrgRoleVsServiceService;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 角色服务权限Service
 * @author luohk
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class OrgRoleVsServiceServiceImpl implements OrgRoleVsServiceApi {

    @Autowired
    private OrgRoleVsServiceService orgRoleVsServiceService;

    /**
     * 根据角色ID和服务ID更改服务下的菜单状态
     * @param roleId 角色ID
     * @param serviceId 服务ID
     * @return
     * @author fengyuguang
     */
    public String updateMenuOperate(String roleId, String serviceId, String operate){
        return orgRoleVsServiceService.updateMenuOperate(roleId,serviceId,operate);
    }

    public OrgRoleVsService get(String id){
         return orgRoleVsServiceService.get(id);
    }

    public List<OrgRoleVsService> findList(OrgRoleVsService orgRoleVsService){
        return orgRoleVsServiceService.findList(orgRoleVsService);
    }

    public Page<OrgRoleVsService> findPage(Page<OrgRoleVsService> page, OrgRoleVsService orgRoleVsService){
        return orgRoleVsServiceService.findPage(page,orgRoleVsService);
    }

    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService){
        return orgRoleVsServiceService.OrgRoleVsServiceSave(orgRoleVsService);
    }

    /**
     * 保存角色新添加的自定义服务
     * @param orgRoleVsServices
     * @return
     * @author fengyuguang
     */
    public String saveService(List<OrgRoleVsService> orgRoleVsServices){
        return orgRoleVsServiceService.saveService(orgRoleVsServices);
    }

    /**
     * 删除角色的自定义服务
     * @param serviceId
     * @param roleId
     * @return
     * @author fengyuguang
     */
    public String delete(String serviceId,String roleId){
        return orgRoleVsServiceService.delete(serviceId,roleId);
    }

    public String delete(OrgRoleVsService orgRoleVsService){
        return orgRoleVsServiceService.delete(orgRoleVsService);
    }

    public String delete(String id){
        return orgRoleVsServiceService.delete(id);
    }

    public List<OrgRoleVsService> findAll(){
        return orgRoleVsServiceService.findAll();
    }

    public List<RoleServiceMenu> find(String id){
        return orgRoleVsServiceService.find(id);
    }

    public List<OrgRoleVsService> findRole(String roleid,String q) {
        return orgRoleVsServiceService.findRole(roleid,q);
    }

    @Override
    public String del(String ids) {
        return orgRoleVsServiceService.deletes(ids);
    }

}