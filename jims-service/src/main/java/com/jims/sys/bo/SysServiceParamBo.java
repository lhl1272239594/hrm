package com.jims.sys.bo;

import com.jims.sys.dao.OrgServiceParamDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.dao.SysServiceParamDao;
import com.jims.sys.entity.OrgServiceParam;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServiceParam;
import com.jims.sys.vo.BeanChangeVo;
import com.jims.sys.vo.ParamVo;
import com.jims.sys.vo.SqlAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务参数Bo
 * Created by heren on 2016/7/4.
 */
@Service
@Component
@Transactional(readOnly = false)
public class SysServiceParamBo {

    @Autowired
    private SysServiceParamDao sysServiceParamDao ;//系统服务参数的DAO

    @Autowired
    private OrgServiceParamDao orgServiceParamDao ;//机构服务的参数的DAO


    /**
     * 根据服务的ID获取所有的服务
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> findSysServiceParamByServiceId(String serviceId) {
        return sysServiceParamDao.findSysServiceParamDao(serviceId) ;
    }


    /**
     * 更新服务的ID
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams) {
        int i=0 ;
        for(SysServiceParam param :sysServiceParams){
            if("".equals(param.getId())||null==param.getId()){
                param.preInsert();
               i+=sysServiceParamDao.insert(param) ;
            }else{
                i+=sysServiceParamDao.update(param) ;
            }
        }
        return i ;
    }

    public int mergeSysServiceParam(BeanChangeVo<SysServiceParam> sysServiceParamBeanChangeVo){
        List<SysServiceParam> inserted = sysServiceParamBeanChangeVo.getInserted() ;
        List<SysServiceParam> updated = sysServiceParamBeanChangeVo.getUpdated() ;
        List<SysServiceParam> deleted = sysServiceParamBeanChangeVo.getDeleted() ;

        int i= 0 ;
        for(SysServiceParam param:inserted){
            param.preInsert();
            i+=sysServiceParamDao.insert(param) ;
        }

        for(SysServiceParam param:updated){
            i+=  sysServiceParamDao.update(param) ;
        }

        for(SysServiceParam param:deleted){
            i+=sysServiceParamDao.delete(param.getId());
        }
        return i ;
    }

    /**
     * 删除服务参数
     * @param sysServiceParams
     * @return
     */
    public int delSysServiceParam(List<SysServiceParam> sysServiceParams) {
        int i = 0 ;
        for(SysServiceParam param:sysServiceParams){
            i+=sysServiceParamDao.delete(param) ;
        }
        return i;
    }


    /**
     * 更新数据
     * @param orgServiceParam
     */
    public int insert(OrgServiceParam orgServiceParam) {
        return orgServiceParamDao.insert(orgServiceParam) ;
    }

    /**
     * 更新OrgserviceParam
     * @param orgServiceParam
     * @return
     */
    public int meregeOrgServiceParam(OrgServiceParam orgServiceParam) {
        int i = 0 ;
        if(orgServiceParam.getId()==null){
            orgServiceParam.preInsert();
            i+=this.insert(orgServiceParam);
        }else{
            i+=orgServiceParamDao.update(orgServiceParam) ;
        }
        return i ;
    }

    public List<SysServiceParam> findSysServiceParamBySelfServiceId(String selfServiceId,String orgId) {
        List<SysServiceParam> sysServiceParams = sysServiceParamDao.findSysServiceParamBySelfServiceId(selfServiceId) ;
        for(SysServiceParam param:sysServiceParams){
            if("".equals(param.getValueRange())||null==param.getValueRange()){
                sysServiceParams.remove(param);
            }else{
                String sql = param.getValueRange() ;
                sql=sql.replace("${orgId}",orgId);
                List<ParamVo> values = sysServiceParamDao.execuSql(new SqlAdapter(sql)) ;
                StringBuffer stringBuffer = new StringBuffer() ;
                for(ParamVo vo:values){
                    stringBuffer.append(vo.getLabel());
                    stringBuffer.append("|") ;
                    stringBuffer.append(vo.getValue()) ;
                    stringBuffer.append(";");
                }
                param.setValueRange(stringBuffer.toString());
            }
        }
        return sysServiceParams;
    }
}
