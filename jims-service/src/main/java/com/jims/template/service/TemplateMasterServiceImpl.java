package com.jims.template.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.template.entity.TemplateMaster;
import com.jims.template.api.TemplateMasterApi;
import com.jims.template.bo.TemplateMasterBo;
import com.jims.common.persistence.Page;
import com.jims.template.vo.OrgRoleTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 模板信息service
* @author lgx
* @version 2016-08-09
*/
@Service(version = "1.0.0")
public class TemplateMasterServiceImpl implements TemplateMasterApi{

    @Autowired
    private TemplateMasterBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public TemplateMaster get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<TemplateMaster> findList(TemplateMaster entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<TemplateMaster> findPage(Page<TemplateMaster> page, TemplateMaster entity) {
        return bo.findPage(page, entity);
    }

    /**
     *根据区域查询此区域在表中是否唯一
     * @param entity
     * @return
     */
    public List<TemplateMaster>  getArea(TemplateMaster entity){
        return bo.getArea(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(TemplateMaster entity) {
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
    public String save(List<TemplateMaster> list) {
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


    /**
     * 保存  增删改
     *
     * @param templateVo
     * @return
     * @author yangruidong
     */
    @Override
    public List<TemplateMaster> saveAll(OrgRoleTemplateVo<TemplateMaster> templateVo) {
        return bo.saveAll(templateVo);
    }
}