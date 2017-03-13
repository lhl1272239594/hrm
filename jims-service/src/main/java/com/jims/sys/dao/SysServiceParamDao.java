package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysServiceParam;
import com.jims.sys.vo.ParamVo;
import com.jims.sys.vo.SqlAdapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by heren on 2016/7/4.
 */
@MyBatisDao
public interface SysServiceParamDao extends CrudDao<SysServiceParam>{
    /**
     *
     * 根据服务ID获取该服务对应的所有参数
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> findSysServiceParamDao(String serviceId);

    /**
     *
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams);


    /**
     * 根据自定义服务ID查询改自定义服务所有的的参数
     * @param selfServiceId
     * @return
     */
    public List<SysServiceParam> findSysServiceParamBySelfServiceId(String selfServiceId);

    /**
     * 根据SQL查询结果
     * @param sqlAdapter
     * @return
     */
    public List<ParamVo> execuSql(SqlAdapter sqlAdapter);
}
