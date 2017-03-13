package com.jims.template.api;

import com.jims.template.entity.OrgRoleTemplate;
import com.jims.common.persistence.Page;
import com.jims.template.vo.OrgRoleTemplateVo;

import java.util.List;

/**
* 角色模板Api
* @author lgx
* @version 2016-08-09
*/
public interface OrgRoleTemplateApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public OrgRoleTemplate get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<OrgRoleTemplate> findList(OrgRoleTemplate entity);
    /**
     * 检索
     * @param entity
     * @return
     */
    public List<OrgRoleTemplate> findAllList(OrgRoleTemplate entity);

    /**
     * 保存  增删改
     * @param orgRoleTemplateVo
     * @return
     *  @author  yangruidong
     */
    public List<OrgRoleTemplate> saveAll(OrgRoleTemplateVo<OrgRoleTemplate> orgRoleTemplateVo);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<OrgRoleTemplate> findPage(Page<OrgRoleTemplate> page, OrgRoleTemplate entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(OrgRoleTemplate entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<OrgRoleTemplate> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;


    /*
     *使用模板数据
     * @param orgId
     * @param area
     * @return  */

    public String saveTemplate(String orgId,String area,String areaName);
}