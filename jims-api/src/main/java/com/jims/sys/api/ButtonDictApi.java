/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.api;

import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.ButtonDict;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.MENU_DICT_BUTTON;

import java.util.List;


/**
 * 菜单
 * @author yangchen
 * @version 2016-08-17
 */

public interface ButtonDictApi {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public ButtonDict get(String id);

    /**
     * 保存修改方法
     * @param buttonDict
     */
	public String save(ButtonDict buttonDict);
    /**
     * 查询所有的按钮按ID差
     */
    public List<MENU_DICT_BUTTON> getALLbut(String mid);
    /**
     * 保存方法（返回id）
     * @param buttonDict
     */
	public String insertReturnObject(ButtonDict buttonDict);
    /**
     * 修改方法（返回id）
     * @param buttonDict
     */
	public String updateReturnObject(ButtonDict buttonDict);

    /**
     * 删除方法
     * @param ids
     */
	public String delete(String ids);

    /**
     * 查询全部数据
     * @return
     */
    public List<ButtonDict> findAll();
    /**
     * 检索机构自定义服务菜单
     *
     * @param serviceId
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceMenu(String serviceId, String roleId, boolean isTree,String orgid);
    /**
     * 查询所有科室信息
     *
     * @return
     */
    public List<DeptDict> findAllList(String orgId, String roleid, String serid, String mid);

    /**
     * 删除方法制定按钮
     * @param ids
     */
    public void delbt(String ids);

    /**
     * 更新数据权限
     *
     * @return
     */
    public void upmdata(ButtonDict buttonDict) ;
    /**
     * 根据角色和服务添加按钮的权限
     *
     * @param buttonDict
     * @return
     */
    public void upbtnrole(List<ButtonDict> buttonDict);
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenuBtn(String serviceId, String roleId, boolean isTree, String orgid,String meid);
}
