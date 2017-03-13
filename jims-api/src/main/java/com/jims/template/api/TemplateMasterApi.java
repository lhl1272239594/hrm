package com.jims.template.api;

import com.jims.template.entity.TemplateMaster;
import com.jims.common.persistence.Page;
import com.jims.template.vo.OrgRoleTemplateVo;

import java.util.List;

/**
* 模板信息Api
* @author lgx
* @version 2016-08-09
*/
public interface TemplateMasterApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public TemplateMaster get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<TemplateMaster> findList(TemplateMaster entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<TemplateMaster> findPage(Page<TemplateMaster> page, TemplateMaster entity);

    /**
     *根据区域查询此区域在表中是否唯一
     * @param entity
     * @return
     */
    public List<TemplateMaster>  getArea(TemplateMaster entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(TemplateMaster entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<TemplateMaster> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;

    /**
     * 保存  增删改
     * @param templateVo
     * @return
     *  @author  yangruidong
     */
    public List<TemplateMaster> saveAll(OrgRoleTemplateVo<TemplateMaster> templateVo);
}