package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.MenuDictApi;
import com.jims.sys.dao.MenuDictDao;
import com.jims.sys.entity.MenuDict;


import java.util.List;

/**
 * 菜单
 * @author tangxb
 * @version 2016-04-25
 */

@Service(version = "1.0.0")

public class MenuDictImpl extends CrudImplService<MenuDictDao, MenuDict> implements MenuDictApi{

    /**
     * 保存方法（返回id）
     * @param menuDict
     */
    @Override
    public String insertReturnObject(MenuDict menuDict) {
        menuDict.preInsert();//添加日期
        int i = dao.insertReturnObject(menuDict);
        String id = menuDict.getId();
        return  id;
    }

    /**
     * 修改方法（返回id）
     * @param menuDict
     * @return
     */
    @Override
    public String updateReturnObject(MenuDict menuDict) {
        menuDict.preUpdate();//添加日期
        int i = dao.updateReturnObject(menuDict);
        String id = menuDict.getId();
        return  id;
    }

    /**
     * 获取全部列表
     * @return
     */
    @Override
    public List<MenuDict> findAll() {
        return dao.findAllList(new MenuDict());
    }

    /**
     * 根据名称获取菜单列表
     * @return
     */
    @Override
    public List<MenuDict> findByName(String q) {
        return dao.findByName(q);
    }

    /**
     * 根据orgId,personId查询可访问菜单
     * @return
     * @zhuqi
     */
    @Override
    public List<MenuDict> findByPersonId(String orgId,String personId) {
        return dao.findByPersonId(orgId,personId);
    }

}
