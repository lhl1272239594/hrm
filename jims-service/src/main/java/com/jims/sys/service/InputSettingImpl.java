package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.api.InputSettingServiceApi;
import com.jims.sys.bo.InputSettingBo;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.dao.InputSettingDetailDao;
import com.jims.sys.dao.InputSettingMasterDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

/**
 * 输入法字典Service
 * Created by yangruidong on 2016/5/18 .
 */
@Service(version = "1.0.0")

public class InputSettingImpl extends CrudImplService<InputSettingMasterDao, InputSettingMaster> implements InputSettingServiceApi {


    @Autowired
    private InputSettingBo inputSettingBo;


    /**
     * 根据字典类型，查询字典设置，然后返回本字典的结果集
     *
     * @param dictType 字典类型
     * @param orgId    组织机构
     * @return
     * @Author ztq
     */
    @Override
    public List<BaseDto> listInputDataBy(String dictType, String orgId) {
        return inputSettingBo.listInputDataBy(dictType,orgId);
    }

    /**
     * 根据表名称，查询表中有什么样的字段
     *
     * @param tableName
     * @return
     */
    @Override
    public List<String> listTableColByTableName(String tableName) {
        return inputSettingBo.listTableColByTableName(tableName);
    }

    /**
     * 根据组织机构id查询输入法字典的全部信息
     *
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    @Override
    public List<InputSettingMaster> findAllListByOrgId(String orgId) {
        return inputSettingBo.findAllListByOrgId(orgId);
    }


    /**
     * 保存  增删改
     *
     * @param inputSettingMasterVo
     * @return
     * @author yangruidong
     */
    @Override
    public List<InputSettingMaster> saveAll(InputSettingVo<InputSettingMaster> inputSettingMasterVo) {
        return inputSettingBo.saveAll(inputSettingMasterVo);
    }

    /**
     * 根据输入法主记录的id查询输入法字典明细表的信息
     *
     * @param id
     * @return
     * @author yangruidong
     */
    @Override
    public List<InputSettingDetail> findListDetail(String id) {
        return inputSettingBo.findListDetail(id);
    }

    /**
     * 保存  增删改  输入法字典明细表
     *
     * @param inputSettingDetailVo
     * @return
     * @author yangruidong
     */
    @Override

    public List<InputSettingDetail> saveDetail(InputSettingVo<InputSettingDetail> inputSettingDetailVo) {
        return inputSettingBo.saveDetail(inputSettingDetailVo);
    }


    /**
     * 根据传入的信息查询并过滤相关内容
     *
     * @param inputInfoVo
     * @return
     */
    @Override
    public List<BaseDto> listInputDataByParam(InputInfoVo inputInfoVo) {
        return inputSettingBo.listInputDataByParam(inputInfoVo);
    }
}
