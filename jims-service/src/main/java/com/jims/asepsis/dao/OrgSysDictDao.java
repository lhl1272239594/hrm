package com.jims.asepsis.dao;

import com.jims.asepsis.entity.OrgSysDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 包单位维护Dao
* @author yangruidong
* @version 2016-06-28
*/
@MyBatisDao
public interface OrgSysDictDao extends CrudDao<OrgSysDict> {

    /**
     * 异步加载页面左侧表格:机构字典表的类型和描述列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> leftList(String orgId);

    /**
     * 异步加载页面右侧表格:机构字典表的键值列表
     * @param type  类型
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> rightList(String type,String orgId);

    /**
     * 根据类型或描述查询某个组织机构的字典数据
     * @param type 类型
     * @param description 描述
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> search(String type,String description,String orgId);

    /**
     * 根据类型查询包单位
     * @param entity
     * @return
     */
    public List<OrgSysDict> findUnits(OrgSysDict entity);
}