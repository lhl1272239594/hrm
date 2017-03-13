package com.jims.template.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.template.entity.PriceListTemplate;
import com.jims.template.api.PriceListTemplateApi;
import com.jims.template.bo.PriceListTemplateBo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 价表模板service
* @author lgx
* @version 2016-08-09
*/
@Service(version = "1.0.0")
public class PriceListTemplateServiceImpl implements PriceListTemplateApi{

    @Autowired
    private PriceListTemplateBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public PriceListTemplate get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<PriceListTemplate> findList(PriceListTemplate entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<PriceListTemplate> findPage(Page<PriceListTemplate> page, PriceListTemplate entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(PriceListTemplate entity) {
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
    public String save(List<PriceListTemplate> list) {
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
     * 机构使用模板
     * @param id
     * @param orgId
     * @return 0 失败，1成功
     */
    public String useTemplate(String id,String orgId){
        try {
            bo.useTemplate(id, orgId);
            return "1";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 诊疗项目使用模板
     * @param area
     * @param orgId
     * @return 0 失败，1成功
     */
    public String importClinicItemByTemplate(String area,String orgId){
        try {
            bo.importClinicItemByTemplate(area, orgId);
            return "1";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 机构数据导入到模板
     * @param area
     * @param areaName
     * @param orgId
     * @return 0 失败，1成功，2失败，模板中有数据
     */
    public String importTemplate(String area,String areaName,String orgId,String masterId){
        try {
            Integer result = bo.importTemplate(area, areaName,orgId,masterId);
            if(result != null && result > 0) return "2";
            return "1";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * execl数据导入到模板
     *
     */
    public String saveListData(List<StaffGroupVo> infos){
        try {
            return bo.saveListData(infos);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
}