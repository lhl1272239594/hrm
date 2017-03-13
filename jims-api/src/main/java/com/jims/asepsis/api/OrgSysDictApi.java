package com.jims.asepsis.api;

import com.jims.asepsis.entity.OrgSysDict;
import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
* 包单位维护Api
* @author yangruidong
* @version 2016-06-28
*/
public interface OrgSysDictApi {

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
     * 保存多条增删改数据
     * @param beanChangeVo 多条增删改数据的集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<OrgSysDict> beanChangeVo);

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
    * 根据ID检索
    * @param id
    * @return
    */
    public OrgSysDict get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<OrgSysDict> findList(OrgSysDict entity);

    /**
     * 根据类型查询包单位
     * @param entity
     * @return
     */
    public List<OrgSysDict>  findUnits(OrgSysDict entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<OrgSysDict> findPage(Page<OrgSysDict> page, OrgSysDict entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(OrgSysDict entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<OrgSysDict> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;
}