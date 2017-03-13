package com.jims.sys.api;

import com.jims.common.web.impl.BaseDto;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputSettingVo;

import java.util.List;

/**
 * 收入法设置相关接口
 * Created by heren on 2016/5/18.
 */
public interface InputSettingServiceApi {

    /**
     * 根据字典类型，查询字典设置，然后返回本字典的结果集
     * @param dictType 字典类型
     * @param orgId 组织机构
     * @return
     * @Author ztq
     */
    public List<BaseDto> listInputDataBy(String dictType,String orgId) ;


    /**
     * 根据传入的信息查询并过滤相关内容
     * @param inputInfoVo
     * @return
     *
     */
    public List<BaseDto> listInputDataByParam(InputInfoVo inputInfoVo) ;

    /**
     * 根据表名称，查询表中有什么样的字段
     * @param tableName
     * @return
     */
    public List<String> listTableColByTableName(String tableName) ;

    /**
     * 根据组织机构id查询输入法字典的全部信息
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    public List<InputSettingMaster> findAllListByOrgId(String orgId);


    /**
     * 保存  增删改
     * @param inputSettingMasterVo
     * @return
     *  @author  yangruidong
     */
    public List<InputSettingMaster> saveAll(InputSettingVo<InputSettingMaster> inputSettingMasterVo);


    /**
     * 根据输入法主记录的id查询输入法字典明细表的信息
     * @param id
     * @return
     * @author yangruidong
     */
    public List<InputSettingDetail> findListDetail(String id);

    /**
     * 保存  增删改  输入法字典明细表
     * @param inputSettingDetailVo
     * @return
     *  @author  yangruidong
     */
    public List<InputSettingDetail> saveDetail(InputSettingVo<InputSettingDetail> inputSettingDetailVo);


}
