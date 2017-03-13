package com.jims.sys.bo;


import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.dao.InputSettingDetailDao;
import com.jims.sys.dao.InputSettingMasterDao;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入法字典BO层
 *
 * @author txb
 * @version 2016-06-17
 */
@Service
@Component
@Transactional(readOnly = false)
public class InputSettingBo extends CrudImplService<InputSettingMasterDao, InputSettingMaster> {
    @Autowired
    private InputSettingDetailDao inputSettingDetailDao;
    /**
     * 根据字典类型，查询字典设置，然后返回本字典的结果集
     *
     * @param dictType 字典类型
     * @param orgId    组织机构
     * @return
     * @Author ztq
     */
    public List<BaseDto> listInputDataBy(String dictType, String orgId) {
        List<InputSettingVo> columnName = inputSettingDetailDao.getColumnName(dictType, orgId);
        StringBuilder sb = new StringBuilder();
        if (columnName.size() == 1) {
            sb.append(columnName.get(0).getColumnName());

        } else {

            for (int i = 0; i < columnName.size(); i++) {
                sb.append(columnName.get(i).getColumnName() + ",");
            }
        }

        if (columnName.size() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        String param = sb.toString();
        List<BaseDto> baseDtos = inputSettingDetailDao.findListBy(param, dictType);

        return baseDtos;
    }

    /**
     * 根据表名称，查询表中有什么样的字段
     *
     * @param tableName
     * @return
     */
    public List<String> listTableColByTableName(String tableName) {
        return inputSettingDetailDao.listTableColByTableName(tableName);
    }

    /**
     * 根据组织机构id查询输入法字典的全部信息
     *
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    public List<InputSettingMaster> findAllListByOrgId(String orgId) {
        return dao.findAllListByOrgId(orgId);
    }


    /**
     * 保存  增删改
     *
     * @param inputSettingMasterVo
     * @return
     * @author yangruidong
     */
    public List<InputSettingMaster> saveAll(InputSettingVo<InputSettingMaster> inputSettingMasterVo) {
        List<InputSettingMaster> newUpdateDict = new ArrayList<InputSettingMaster>();
        List<InputSettingMaster> inserted = inputSettingMasterVo.getInserted();
        List<InputSettingMaster> updated = inputSettingMasterVo.getUpdated();
        List<InputSettingMaster> deleted = inputSettingMasterVo.getDeleted();
        //插入
        for (InputSettingMaster inputSettingMaster : inserted) {
            inputSettingMaster.preInsert();
            inputSettingMaster.setOrgId(inputSettingMasterVo.getOrgId());
            int num = dao.insert(inputSettingMaster);
        }
        //更新
        for (InputSettingMaster inputSettingMaster : updated) {
            inputSettingMaster.preUpdate();
            int num = dao.update(inputSettingMaster);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (InputSettingMaster inputSettingMaster : deleted) {
            ids.add(inputSettingMaster.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 根据输入法主记录的id查询输入法字典明细表的信息
     *
     * @param id
     * @return
     * @author yangruidong
     */
    public List<InputSettingDetail> findListDetail(String id) {
        return inputSettingDetailDao.findListDetail(id);
    }

    /**
     * 保存  增删改  输入法字典明细表
     *
     * @param inputSettingDetailVo
     * @return
     * @author yangruidong
     */
    public List<InputSettingDetail> saveDetail(InputSettingVo<InputSettingDetail> inputSettingDetailVo) {
        List<InputSettingDetail> newUpdateDict = new ArrayList<InputSettingDetail>();
        List<InputSettingDetail> inserted = inputSettingDetailVo.getInserted();
        List<InputSettingDetail> updated = inputSettingDetailVo.getUpdated();
        List<InputSettingDetail> deleted = inputSettingDetailVo.getDeleted();
        //插入
        for (InputSettingDetail inputSettingDetail : inserted) {
            inputSettingDetail.preInsert();
            //inputSettingDetail.setInputSettingMasterId(inputSettingDetailVo.getInput_setting_master_id());
            int num = inputSettingDetailDao.insert(inputSettingDetail);
        }
        //更新
        for (InputSettingDetail inputSettingDetail : updated) {
            inputSettingDetail.preUpdate();
            int num = inputSettingDetailDao.update(inputSettingDetail);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (InputSettingDetail inputSettingDetail : deleted) {
            ids.add(inputSettingDetail.getId());
        }
        for (String id : ids) {
            inputSettingDetailDao.delete(id);
        }
        return newUpdateDict;
    }


    /**
     * 根据传入的信息查询并过滤相关内容
     *
     * @param inputInfoVo
     * @return
     */
    public List<BaseDto> listInputDataByParam(InputInfoVo inputInfoVo) {
        String dictType = inputInfoVo.getDictType();
        String orgId = inputInfoVo.getOrgId();
        List<InputSettingVo> columnName = inputSettingDetailDao.getColumnName(dictType, orgId);
        StringBuilder sb = new StringBuilder();
        if (columnName.size() == 1) {
            sb.append(columnName.get(0).getColumnName());

        } else {

            for (int i = 0; i < columnName.size(); i++) {
                sb.append(columnName.get(i).getColumnName() + ",");
            }
        }

        if (columnName.size() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }

        String param = sb.toString();
        List<InputParamVo> list = inputInfoVo.getInputParamVos();
        List<String> paramList=new ArrayList<String>();
        if(StringUtils.isNotEmpty(orgId)){
            InputParamVo inputParamVo=new InputParamVo();
            inputParamVo.setColName("org_id");
            inputParamVo.setColValue(orgId);
            inputParamVo.setOperateMethod("=");
            list.add(inputParamVo);
        }
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.equalsIgnoreCase(list.get(i).getOperateMethod(), "like")) {
                    if(!list.get(i).getColValue().startsWith("%") && !list.get(i).getColValue().endsWith("%")) {
                        list.get(i).setColValue("%" + list.get(i).getColValue() + "%");
                    }
                }
                if("input_code".equalsIgnoreCase(list.get(i).getColName())){
                    list.get(i).setColValue(list.get(i).getColValue().toUpperCase());
                }
                String p;
                if(list.get(i).getOperateMethod().equalsIgnoreCase("null"))  {
                    p= list.get(i).getColName()+" is not null";
                } else{
                    if("in".equals(list.get(i).getOperateMethod())){
                        p=list.get(i).getColName()+" "+list.get(i).getOperateMethod()+" "+ list.get(i).getColValue() ;
                    }else{
                        p=list.get(i).getColName()+" "+list.get(i).getOperateMethod()+ "'" + list.get(i).getColValue() +"'";
                    }

                }
                paramList.add(p);
            }
        }
        List<BaseDto> baseDtos = inputSettingDetailDao.listInputDataBy(param, dictType, paramList);

        return baseDtos;
    }
}