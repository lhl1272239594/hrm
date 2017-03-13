/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">JeeSite</a> All rights reserved.
 */
package com.jims.common.service.impl;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.DataEntity;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */

public abstract class CrudImplService<D extends CrudDao<T>, T extends DataEntity<T>> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}

	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */

	public String save(T entity) {
        int i=0;
        if (entity.getIsNewRecord()){
            entity.preInsert();
            i=dao.insert(entity);
        }else{
            entity.preUpdate();
            i=dao.update(entity);
        }
        return i+"";
	}

	/**
	 * 删除数据
	 * @param entity
	 */

	public String delete(T entity) {
        int i=0;
        dao.delete(entity);
        return i+"";
	}
    /**
     * 删除数据
     * @param
     */

    public String delete(String ids) {
        int i=0;
        String[] id = ids.split(",");
        for (int j = 0; j < id.length; j++){
            dao.delete(id[j]);
            i++;
        }
        return i+"";
    }


}
