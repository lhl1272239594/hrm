/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">JeeSite</a> All rights reserved.
 */
package com.jims.common.service;


import com.jims.common.persistence.DataEntity;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;



public interface  CrudService<T extends DataEntity<T>> {
	

	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity);

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 删除数据
	 * @param entity
	 */
	public void delete(T entity);
//
//	public  int upCourseState(T entity){
//		return dao.upCourseState(entity);
//	}
}
