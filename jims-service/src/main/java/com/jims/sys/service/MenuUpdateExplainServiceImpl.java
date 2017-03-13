package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.entity.MenuUpdateExplain;
import com.jims.sys.api.MenuUpdateExplainApi;
import com.jims.sys.bo.MenuUpdateExplainBo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 服务菜单更新说明service
* @author lgx
* @version 2016-07-19
*/
@Service(version = "1.0.0")
public class MenuUpdateExplainServiceImpl implements MenuUpdateExplainApi{

    @Autowired
    private MenuUpdateExplainBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public MenuUpdateExplain get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<MenuUpdateExplain> findList(MenuUpdateExplain entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<MenuUpdateExplain> findPage(Page<MenuUpdateExplain> page, MenuUpdateExplain entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(MenuUpdateExplain entity) {
        try {
            bo.save(entity);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<MenuUpdateExplain> list) {
        try {
            bo.save(list);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
}