package com.jims.template.api;

import com.jims.sys.vo.StaffGroupVo;
import com.jims.template.entity.PriceListTemplate;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 价表模板Api
* @author lgx
* @version 2016-08-09
*/
public interface PriceListTemplateApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public PriceListTemplate get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<PriceListTemplate> findList(PriceListTemplate entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<PriceListTemplate> findPage(Page<PriceListTemplate> page, PriceListTemplate entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(PriceListTemplate entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<PriceListTemplate> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;

    /**
     * 机构使用模板
     * @param id
     * @param orgId
     * @return 0 失败，1成功
     */
    public String useTemplate(String id,String orgId);

    /**
     * 诊疗项目使用模板
     * @param area
     * @param orgId
     * @return 0 失败，1成功
     */
    public String importClinicItemByTemplate(String area,String orgId);

    /**
     * 机构数据导入到模板
     * @param area
     * @param areaName
     * @param orgId
     * @return 0 失败，1成功，2失败，模板中有数据
     */
    public String importTemplate(String area,String areaName,String orgId,String masterId);

    /**
     * execl数据导入到模板
     *
     */
    public String saveListData(List<StaffGroupVo> infos);


}