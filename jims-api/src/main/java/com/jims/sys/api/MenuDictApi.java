/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.api;

import com.jims.sys.entity.MenuDict;

import java.util.List;


/**
 * 菜单
 * @author tangxb
 * @version 2016-04-25
 */

public interface MenuDictApi {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public MenuDict get(String id);

    /**
     * 保存修改方法
     * @param menuDict
     */
	public String save(MenuDict menuDict);

    /**
     * 保存方法（返回id）
     * @param menuDict
     */
	public String insertReturnObject(MenuDict menuDict);
    /**
     * 修改方法（返回id）
     * @param menuDict
     */
	public String updateReturnObject(MenuDict menuDict);

    /**
     * 删除方法
     * @param ids
     */
	public String delete(String ids);

    /**
     * 查询全部数据
     * @return
     */
    public List<MenuDict> findAll();

    /**
     * 根据菜单名查询
     * @return
     */
    public List<MenuDict> findByName(String q);

    /**
     * 根据orgId,personId查询可访问菜单
     * @return
     * @zhuqi
     */
    public List<MenuDict> findByPersonId(String orgId,String personId);

}
