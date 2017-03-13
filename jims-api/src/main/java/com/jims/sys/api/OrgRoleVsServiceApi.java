package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface OrgRoleVsServiceApi {

    /**
     * 根据角色ID和服务ID更改服务下的菜单状态
     * @param roleId 角色ID
     * @param serviceId 服务ID
     * @return
     * @author fengyuguang
     */
    public String updateMenuOperate(String roleId,String serviceId,String operate);

    /**
     * 根据角色权限id获取
     * @param id
     * @return
     */
    public OrgRoleVsService get(String id);

    /**
     *  获取角色服务权限列表
     * @param orgRoleVsService
     * @return
     */
    public List<OrgRoleVsService> findList(OrgRoleVsService orgRoleVsService);

    /**
     *  获取角色权限列表
     * @param page
     * @param orgRoleVsService
     * @return
     */
    public Page<OrgRoleVsService> findPage(Page<OrgRoleVsService> page, OrgRoleVsService orgRoleVsService);

    /**
     *  保存角色角色权限
     * @param orgRoleVsService
     * @return
     */
    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService);

    /**
     * 保存角色新添加的自定义服务
     * @param orgRoleVsServices
     * @return
     * @author fengyuguang
     */
    public String saveService(List<OrgRoleVsService> orgRoleVsServices);

    /**
     * 删除角色的自定义服务
     * @param serviceId
     * @param roleId
     * @return
     * @author fengyuguang
     */
    public String delete(String serviceId, String roleId);

    /**
     * 删除角色权限
     * @param orgRoleVsService
     * @return
     */
    public String delete(OrgRoleVsService orgRoleVsService);

    /**
     * 根据主键进行删除
     * @param id
     * @return
     */
    public String delete(String id);

    /**
     * 查询所有角色权限
     * @return
     */
    public List<OrgRoleVsService> findAll();

    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    public List<RoleServiceMenu> find(String id);

    /**
     * 根据角色id进行查询
     * @param roleid
     * @return
     */
    public List<OrgRoleVsService> findRole(String roleid,String q);

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    public String del(String ids);
}
