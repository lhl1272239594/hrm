package com.jims.template.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.template.entity.OrgRoleTemplate;
import com.jims.template.api.OrgRoleTemplateApi;
import com.jims.template.bo.OrgRoleTemplateBo;
import com.jims.common.persistence.Page;
import com.jims.template.vo.OrgRoleTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 角色模板service
* @author lgx
* @version 2016-08-09
*/
@Service(version = "1.0.0")
public class OrgRoleTemplateServiceImpl implements OrgRoleTemplateApi{

    @Autowired
    private OrgRoleTemplateBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public OrgRoleTemplate get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<OrgRoleTemplate> findList(OrgRoleTemplate entity) {
        return bo.findList(entity);
    }

    /**
     * 检索
     * @param entity
     * @return
     */
    public List<OrgRoleTemplate> findAllList(OrgRoleTemplate entity) {
        return bo.findAllList(entity);
    }

    /**
     * 保存  增删改
     *
     * @param orgRoleTemplateVo
     * @return
     * @author yangruidong
     */
    @Override
    public List<OrgRoleTemplate> saveAll(OrgRoleTemplateVo<OrgRoleTemplate> orgRoleTemplateVo) {
        return bo.saveAll(orgRoleTemplateVo);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<OrgRoleTemplate> findPage(Page<OrgRoleTemplate> page, OrgRoleTemplate entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(OrgRoleTemplate entity) {
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
    public String save(List<OrgRoleTemplate> list) {
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

    /*
     *使用模板数据
     * @param orgId
     * @param area
     * @return
     * */

    public String saveTemplate(String orgId,String area,String areaName){
       return bo.saveTemplate(orgId,area,areaName);
    }
}