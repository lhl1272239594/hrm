/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.MenuDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface MenuDictDao extends CrudDao<MenuDict> {
    /**
     * 保存方法（返回id）
     * @param menuDict
     */
    public Integer insertReturnObject(MenuDict menuDict);
    /**
     * 修改方法（返回id）
     * @param menuDict
     */
    public Integer updateReturnObject(MenuDict menuDict);

    /**
     * 根据名称获取菜单列表
     * @return
     */
    public List<MenuDict>findByName(@Param("q") String q);

    /**
     * 根据orgId,personId查询可访问菜单
     * @return
     * @zhuqi
     */
    public List<MenuDict>findByPersonId(@Param("orgId") String orgId,@Param("personId")String personId);
}