package com.jims.template.bo;

import com.jims.common.utils.DateUtils;
import com.jims.common.utils.IdGen;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.template.dao.TemplateMasterDao;
import com.jims.template.entity.PriceListTemplate;
import com.jims.template.dao.PriceListTemplateDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.template.entity.TemplateMaster;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.Date;
import java.util.List;

/**
* 价表模板bo
* @author lgx
* @version 2016-08-09
*/
@Service
@Transactional(readOnly = false)
public class PriceListTemplateBo extends CrudImplService<PriceListTemplateDao, PriceListTemplate>{

    @Autowired
    private TemplateMasterDao masterDao;
    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<PriceListTemplate> list) {
        if(list != null && list.size() > 0) {
            for(PriceListTemplate entity : list) {
                save(entity);
            }
        }
    }

    public String save(PriceListTemplate entity){
        if(entity.getIsNewRecord()){
            entity.preInsert();
            dao.insert(entity);
        } else {
            entity.preUpdate();
            dao.update(entity);
        }
        return null;
    }

    /**
     * 机构使用模板
     * @param id
     * @param orgId
     * @return
     */
    public void useTemplate(String id,String orgId){
        String sql = "insert into price_list select "
                + "item_class,item_code,item_name,item_spec,units,price,prefer_price,"
                + "foreigner_price,performed_by,fee_type_mask,class_on_inp_rcpt,class_on_outp_rcpt,"
                + "class_on_reckoning,subj_code,class_on_mr,memo,start_date,"
                + "stop_date,operator,enter_date,high_price,material_code,"
                + "score_1,score_2, price_name_code, control_flag,input_code,"
                + "input_code_wb,std_code_1,changed_memo,class_on_insur_mr,cwtj_code,"
                + "xm_wy,lb_wy,mzsj_wy,zysj_wy,group_flag,stop_operator,sys_guid(),"
                + "null,sysdate,null,sysdate,null,'0','"
                + orgId + "'from price_list_template ,template_master m "
                + " where m.id = master_id and m.id= '" + id + "'";
        dao.executeInsertSql(sql);
    }

    /**
     * 诊疗项目使用模板
     * @param area
     * @param orgId
     * @return
     */
    public void importClinicItemByTemplate(String area,String orgId){
        String sql = "insert into clinic_item_dict(id,item_class,item_code"
                + ",item_name,input_code,input_code_wb,expand3,update_date"
                + ",create_date,del_flag,org_id) "
                + "select sys_guid(),t.item_class,t.item_code,t.item_name"
                + ",t.input_code,t.input_code_wb,t.performed_by,sysdate"
                + ",sysdate,'0','" + orgId + "' from price_list_template t"
                + ",template_master m "
                + " where m.id = master_id and m.area= '" + area + "'";
        dao.executeInsertSql(sql);
        sql = "insert into clinic_item_name_dict(id,std_indicator,item_class,item_code"
                + ",item_name,input_code,input_code_wb,expand3,update_date"
                + ",create_date,del_flag,org_id) "
                + "select sys_guid(),'1',t.item_class,t.item_code,t.item_name"
                + ",t.input_code,t.input_code_wb,t.performed_by,sysdate"
                + ",sysdate,'0','" + orgId + "' from price_list_template t"
                + ",template_master m "
                + " where m.id = master_id and m.area= '" + area + "'";
        dao.executeInsertSql(sql);
        sql = "insert into clinic_vs_charge(id,clinic_item_class,clinic_item_code"
                + ",charge_item_no,charge_item_class,charge_item_code,charge_item_spec,amount"
                + ",units,backbill_rule,update_date"
                + ",create_date,del_flag,org_id) "
                + "select sys_guid(),t.item_class,t.item_code,'1',t.item_class"
                + ",t.item_code,t.item_spec,1,t.units,'按次',sysdate"
                + ",sysdate,'0','" + orgId + "' from price_list_template t"
                + ",template_master m "
                + " where m.id = master_id and m.area= '" + area + "'";
        dao.executeInsertSql(sql);
    }

    public Integer importTemplate(String area,String areaName,String orgId,String masterId){
        String sql = "insert into price_list_template select "
                + "item_class,item_code,item_name,item_spec,units,price,prefer_price,"
                + "foreigner_price,performed_by,fee_type_mask,class_on_inp_rcpt,class_on_outp_rcpt,"
                + "class_on_reckoning,subj_code,class_on_mr,memo,start_date,"
                + "stop_date,operator,enter_date,high_price,material_code,"
                + "score_1,score_2, price_name_code, control_flag,input_code,"
                + "input_code_wb,std_code_1,changed_memo,class_on_insur_mr,cwtj_code,"
                + "xm_wy,lb_wy,mzsj_wy,zysj_wy,group_flag,stop_operator,sys_guid(),"
                + "'" + masterId + "' from price_list where org_id='" + orgId + "'"
                + "AND ( stop_date > sysdate or stop_date is NULL)"
                + "AND sysdate >=start_date ";
        dao.executeInsertSql(sql);
        return null;
    }


    /**
     * 导入excel到模板中
     *
     * @param dictListVo
     * @return
     * @author yangruidong
     */
    public String saveListData(List<StaffGroupVo> dictListVo) {

        for (int i = 0; i < dictListVo.size(); i++) {
            PriceListTemplate priceListTemplate=new PriceListTemplate();
            priceListTemplate.preInsert();
//            priceListTemplate.setItemClass(dictListVo.get(i).getItemClass());
//            priceListTemplate.setItemName(dictListVo.get(i).getItemName());
//            priceListTemplate.setItemCode(dictListVo.get(i).getItemCode());
//            priceListTemplate.setItemSpec(dictListVo.get(i).getItemSpec());
//            priceListTemplate.setUnits(dictListVo.get(i).getUnits());
//            priceListTemplate.setPrice(dictListVo.get(i).getPrice());
//            priceListTemplate.setPreferPrice(dictListVo.get(i).getPreferPrice());
//            priceListTemplate.setForeignerPrice(dictListVo.get(i).getForeignerPrice());
//            priceListTemplate.setPerformedBy(dictListVo.get(i).getPerformedBy());
//            priceListTemplate.setFeeTypeMask(dictListVo.get(i).getFeeTypeMask());
//            priceListTemplate.setClassOnInpRcpt(dictListVo.get(i).getClassOnInpRcpt());
//            priceListTemplate.setClassOnOutpRcpt(dictListVo.get(i).getClassOnOutpRcpt());
//            priceListTemplate.setClassOnReckoning(dictListVo.get(i).getClassOnReckoning());
//            priceListTemplate.setSubjCode(dictListVo.get(i).getSubjCode());
//            priceListTemplate.setClassOnMr(dictListVo.get(i).getClassOnMr());
//            priceListTemplate.setMemo(dictListVo.get(i).getMemo());
//            priceListTemplate.setStartDate(DateUtils.parseDate(dictListVo.get(i).getStartDate()));
//            priceListTemplate.setInputCode(dictListVo.get(i).getInputCode());
//            priceListTemplate.setMaterialCode(dictListVo.get(i).getMaterialCode());
//            priceListTemplate.setMasterId(dictListVo.get(i).getMasterId());
            priceListTemplate.setEnterDate(new Date());
            dao.insert(priceListTemplate);
        }
        return dictListVo.size()+"";
    }
}