/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.sys.dao.*;
import com.jims.sys.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 个人服务BAO层
 *
 * @author yangruidong
 * @version 2016-06-02
 */
@Service
@Component
@Transactional(readOnly = false)
public class PersionServiceListBo extends CrudImplService<PersionServiceListDao, PersionServiceList> {

    @Autowired
    private PersionServiceListDao persionServiceListDao;
    @Autowired
    private OrgStaffDao orgStaffDao;
    @Autowired
    private StaffVsRoleDao staffVsRoleDao;
    @Autowired
    private OrgRoleVsServiceDao orgRoleVsServiceDao;
    @Autowired
    private SysServiceDao sysServiceDao;
    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Autowired
    private SysUserDao sysUserDao;


    /**
     * 根据persionId查询服务
     *
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag(String persionId) {
        List<SysService> services = persionServiceListDao.findListByFlag(persionId);
        String serviceType = "0";
        String serviceClass = "1";
        // 查询所有的免费服务
        List<SysService> sysServices = sysServiceDao.serviceListByTC(serviceType, serviceClass);
        services.addAll(sysServices);
        List<OrgStaff> orgStaffss = orgStaffDao.findListByPersionId(persionId);
        for (int i = 0; i < orgStaffss.size(); i++) {
            //组织机构id
            String id = orgStaffss.get(i).getOrgId();
            //根据orgId查询组织机构名称
            SysCompany sysCompany = sysCompanyDao.get(id);
            SysService sysService = new SysService();
            sysService.setServiceName(sysCompany.getOrgName());
            sysService.setId(id);
            services.add(sysService);
        }
        return services;
    }

    /**
     * @auto louhuili
     * 查询的是可以或者不可以定制的个人服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
     * @return
     */
    public List<PersionServiceList> findListByPersionId(String serviceClass,String serviceType,String persionId, String state){
        state = (state!=null&&!state.equals("")&&state.equals("1")?"":(state!=null&&!state.equals("")&&state.equals("0")?"in":state));
        return persionServiceListDao.findListByPersionId(serviceClass, serviceType, persionId, state);
    }
    /**
     * 保存个人购买的服务
     *
     * @param persionServiceList
     * @return 1 成功 ,0 失败
     */
    public void saveService(PersionServiceList persionServiceList) {
        List<PersionServiceList> services = persionServiceList.getServiceList();
        if (services != null && services.size() > 0) {
            for (PersionServiceList service : services) {
                if(service.getUpFlag()!=null&&!service.getUpFlag().equals("")&&service.getUpFlag().equals("1")){
                    PersionServiceList oldService = persionServiceListDao.get(service);
                    if(new Date().getTime() >oldService.getServiceEndDate().getTime()){//当前时间大于原有截止时间，表示重新定制，服务开始时间和结束时间都要传进去
                        oldService.setServiceStartDate(new Date());
                        oldService.setServiceEndDate(service.getServiceEndDate());
                    }else{//当前时间小于原有截止时间，表示续费，服务开始时间不变，结束时间=原有截止时间+续费时长
                        long endl = oldService.getServiceEndDate().getTime()+service.getServiceEndDate().getTime()-new Date().getTime();
                        oldService.setServiceEndDate(new Date(endl));
                    }
                    if(oldService!=null&&oldService.getId()!=null&&!oldService.getId().equals("")){
                        persionServiceListDao.update(oldService);
                    }else{
                        service.preInsert();
                        persionServiceListDao.insert(service);
                    }
                }else {
                    service.preInsert();
                    persionServiceListDao.insert(service);
                }
//                PersionServiceList ps = persionServiceListDao.get(service);
//                if(ps!=null&&ps.getId()!=null&&!ps.getId().equals("")){
//                    System.out.println(service.getId()+","+service.getIsNewRecord());
//                    persionServiceListDao.update(service);
//                }else{
//                    persionServiceListDao.insert(service);
//                }
            }
        }
    }

    /**
     * 根据组织机构id查询信息
     *
     * @param orgName
     * @return
     */
    public SysCompany getOrgName(String orgName) {
        return sysCompanyDao.getOrgName(orgName);
    }

    /**
     * 查询组织机构管理员
     * @param loginName
     * @return
     */
    public SysCompany findNameByOwner(String loginName) {
        SysCompany sysCompany = sysCompanyDao.findNameByOwner(loginName);
        return sysCompany;
    }


    /**
     * 与数据库中的用户名比对，是否正确
     *
     * @param loginName
     * @return
     */
    public SysUser selectLoginName(String loginName) {
        if (StringUtils.isNotBlank(loginName)){
            SysUser user = sysUserDao.selectLoginName(loginName);
            return user;
        }
        return null;
    }

    /**
     * 与数据库中的密码比对，是否正确
     *
     * @param loginName
     * @return
     */
    public SysUser selectPassword(String loginName) {
        if (StringUtils.isNotBlank(loginName)) {
            SysUser user = sysUserDao.selectPasswrod(loginName);
            return user;
        }
        return null;
    }
}