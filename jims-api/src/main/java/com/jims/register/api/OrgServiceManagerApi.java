package com.jims.register.api;

import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.sys.vo.MenuDictVo;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;

import java.util.List;

/**
 * 机构选择服务管理API
 * @author lgx
 * @version 2016-05-31
 */
public interface OrgServiceManagerApi {

    /**
     * 保存选择的服务
     * @param serviceList 服务列表
     * @return
     */
    public String saveService(List<OrgServiceList> serviceList);

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单,
     *                        参数OrgSelfServiceList属性中
     *                        delFlag 为 1 时，属性id为药删除的自定义服务id,多个以‘,’隔开，
     *                        id不为空，orgId为空时，属性menus为服务(id)对应的菜单数据(树形结构)
     *                                              servicesVs 为对应平台服务数据
     *
     *                        其他值时，为修改的自定义服务，当为添加的自定义服务时，包含所有需添加信息。
     * @return 0保存失败，1保存成功
     */
    public String saveSelfService(List<OrgSelfServiceList> selfServiceList);

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgServiceList> findService(String orgId);

    /**
     * 检索机构自定义的服务
     * @param orgId 机构Id
     * @return
     */
    public List<OrgSelfServiceList> findSelfService(String orgId);

    /**
     * 检索自定义服务
     * @param personId 人员ID
     * @param orgId    机构ID
     * @return 自定义服务
     * @author fengyuguang
     */
    public List<OrgSelfServiceList> findSelfServiceByOrgIdPersonId(String personId,String orgId);

    /**
     * 检索机构自定义菜单
     * @param selfServiceId 自定义服务Id
     * @param isTree 是否为树形结构
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(String selfServiceId,boolean isTree);

    /**
     * 检索机构自定义服务菜单
     *
     * @param serviceId
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceMenu(String serviceId, String roleId,boolean isTree);

    /**
     * 检索自定义服务对应平台服务
     * @param selfServiceId
     * @return
     */
    public List<ServiceSelfVsSys> findVsSys(String selfServiceId);
    /**
     * 检索机构自定义服务按钮
     *
     * @param serviceId
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsButton(String serviceId, String roleId,boolean isTree);
}