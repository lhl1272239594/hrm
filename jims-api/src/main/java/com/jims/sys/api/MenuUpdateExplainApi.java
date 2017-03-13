package com.jims.sys.api;

import com.jims.sys.entity.MenuUpdateExplain;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 服务菜单更新说明Api
* @author lgx
* @version 2016-07-19
*/
public interface MenuUpdateExplainApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public MenuUpdateExplain get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<MenuUpdateExplain> findList(MenuUpdateExplain entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<MenuUpdateExplain> findPage(Page<MenuUpdateExplain> page, MenuUpdateExplain entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(MenuUpdateExplain entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<MenuUpdateExplain> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;
}