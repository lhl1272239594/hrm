/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 输入法明细AO接口
 * @author yangruidong
 * @version 2016-05-118
 */
@MyBatisDao
public interface InputSettingDetailDao extends CrudDao<InputSettingDetail> {

    /**
     * 根据输入法主记录的id查询输入法字典明细表的信息
     * @param id
     * @return
     * @author yangruidong
     */
    public List<InputSettingDetail> findListDetail(String id);

    /**
     * 根据表名称，查询表中有什么样的字段
     * @param tableName
     * @return
     * @author yangruidong
     */
    public List<String> listTableColByTableName(String tableName);

    /**
     * 查询返回的结果集中的需要显示的字段
     * @param dictType     表名/视图名
     * @param orgId        组织机构id
     * @return
     * @author yangruidong
     */
    public List<InputSettingVo> getColumnName(String dictType,String orgId);

    /**
     * 根据传递的字段和表名来查询返回的结果集
     * @param param
     * @param dictType
     * @return
     * @author yangruidong
     */
    public List<BaseDto> findListBy(@Param("param") String param,@Param("dictType")String dictType);


    /**
     * 根据传入的信息查询并过滤相关内容
     * @param list
     * @return
     * @author yangruidong
     */
    public List<BaseDto> listInputDataByParam(@Param("param") String param,@Param("dictType")String dictType,@Param("list") List<InputParamVo> list);

    /**
     * 根据传入的信息查询并过滤相关内容
     * @param list
     * @return
     * @author yangruidong
     */
    public List<BaseDto> listInputDataBy(@Param("param") String param,@Param("dictType")String dictType,@Param("list") List<String> list);
}