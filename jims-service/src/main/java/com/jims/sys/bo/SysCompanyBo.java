package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.TreeUtils;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.dao.*;
import com.jims.sys.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.processing.RoundEnvironment;
import java.util.Date;
import java.util.List;

/**
 * 组织机构事务层
 *
 * @author lgx
 * @version 2016-6-3
 */
@Service
@Component
@Transactional(readOnly = false)
public class SysCompanyBo extends CrudImplService<SysCompanyDao, SysCompany> {

    @Autowired
    private OrgServiceListDao serviceDao;
    @Autowired
    private SysServiceDao sysServiceDao;    //系统服务
    @Autowired
    private OrgSelfServiceListDao orgSelfServiceListDao;    //自定义服务
    @Autowired
    private ServiceVsMenuDao serviceVsMenuDao;      //服务于菜单对照
    @Autowired
    private OrgSelfServiceVsMenuDao orgSelfServiceVsMenuDao;    //自定义服务与菜单对照
    @Autowired
    private OrgRoleDao orgRoleDao;      //角色
    @Autowired
    private StaffVsRoleDao staffVsRoleDao;      //员工对应角色
    @Autowired
    private OrgRoleVsServiceDao roleVsServiceDao;   //角色对应服务
    @Autowired
    private OrgStaffDao orgStaffDao;
    @Autowired
    private InitProcessDao initProcessDao;

    @Autowired
    private ServiceSelfVsSysDao serviceSelfVsSysDao ;
    @Autowired
    private SysServiceParamBo sysServiceParamBo ;

    /**
     * 查询orgId和其子机构对应的机构信息
     * @param orgId
     * @return 组织机构list集合
     * @author zhuqi
     */
    public List<SysCompany> findListByParentId(String orgId){
        return dao.findListByParentId(orgId);
    }

    /**
     * 查询用户注册的DEL_FLAG='0'的所有机构List
     * @param owner
     * @return 组织机构list集合
     * @author 娄会丽
     */
    public List<SysCompany> findAllByOwner(String owner){
        return dao.findAllByOwner(owner);
    }
    /**
     * 根据机构所属者和组织机构名称查询信息
     * @param sysCompany
     * @author 娄会丽
     * @return
     */
    public List<SysCompany> findIsNoByOwner(SysCompany sysCompany){
        return dao.findIsNoByOwner(sysCompany);
    }

    /**
     * 保存注册信息以及选择的服务
     *
     * @param company
     */

    public void saveCompanyAndService(SysCompany company) {
        company.preInsert();
        List<OrgServiceList> services = company.getServiceList();
        if (services != null && services.size() > 0) {
            for (OrgServiceList service : services) {
                service.setOrgId(company.getId());
                service.preInsert();
                serviceDao.insert(service);
            }
        }
        List<SysService> sysServices = sysServiceDao.serviceListByTC("0", "3");
        if (sysServices != null && sysServices.size() > 0) {
            for (SysService service : sysServices) {
                OrgServiceList orgService = new OrgServiceList();
                orgService.setServiceId(service.getId());
                orgService.setServiceStartDate(new Date());
                orgService.setOrgId(company.getId());
                orgService.preInsert();
                serviceDao.insert(orgService);
            }
        }

        dao.insert(company);
        String id = company.getId();
        OrgStaff orgStaff = new OrgStaff();
        orgStaff.preInsert();
        orgStaff.setPersionId(company.getOwner());
        orgStaff.setDelFlag("0");
        orgStaff.setOrgId(id);
        orgStaffDao.insert(orgStaff);

    }

    /**
     * 组织机构通过审核
     *
     * @param sysCompany
     * @return
     * @author fengyuguang
     */
    public int update(SysCompany sysCompany) {
        sysCompany.preUpdate();

        String orgId = sysCompany.getId();

        //创建默认管理角色
        OrgRole role = new OrgRole(orgId, "超级管理员");
        role.preInsert();
        orgRoleDao.insert(role);
        //员工与角色对照表
        StaffVsRole staffVsRole = new StaffVsRole();
        staffVsRole.preInsert();
        staffVsRole.setRoleId(role.getId());
        String owner = sysCompany.getOwner();
        String staffId = orgStaffDao.findStaffByPersonIdOrgId(owner, orgId).getId();    //根据人员ID和机构ID查询员工信息，获取员工ID
        staffVsRole.setStaffId(staffId);
        staffVsRoleDao.insert(staffVsRole);

        //查询机构服务列表
        OrgServiceList serviceParam = new OrgServiceList();
        serviceParam.setOrgId(orgId);
        List<OrgServiceList> services = serviceDao.findList(serviceParam);

        //插入向导数据
        List<InitProcess> linp = initProcessDao.findInitProcessByOrgId(orgId);//查出可以插入的向导数据
        int initSort = 1;
        for(InitProcess p:linp){//逐条插入
            p.setIsNewRecord(false);
            p.preInsert();
            p.setInitSort(initSort++);
            p.setOrgId(orgId);
            initProcessDao.insert(p);
        }
        for (OrgServiceList service : services) {

            //自定义服务
            OrgSelfServiceList orgSelfServiceList = new OrgSelfServiceList();
            orgSelfServiceList.preInsert();     //设置主键ID
            orgSelfServiceList.setOrgId(orgId);
            orgSelfServiceList.setServiceName(service.getServiceName());
            orgSelfServiceListDao.insert(orgSelfServiceList);   //添加自定义服务
            String roleVsServiceId = null;

            ServiceSelfVsSys serviceSelfVsSys = new ServiceSelfVsSys() ;
            serviceSelfVsSys.preInsert();
            serviceSelfVsSys.setSysServiceId(service.getServiceId());
            serviceSelfVsSys.setSelfServiceId(orgSelfServiceList.getId());
            serviceSelfVsSysDao.insert(serviceSelfVsSys) ;
            if ("0".equals(service.getServiceType()) && "3".equals(service.getServiceClass())) {
                List<MenuDict> menus = service.getMenus();//服务对应菜单集合
                //角色对应服务
                for (MenuDict menu : menus) {
                    OrgRoleVsService roleVsService = new OrgRoleVsService();
                    roleVsService.preInsert();//主键ID
                    roleVsService.setRoleId(role.getId());
                    roleVsService.setServiceId(orgSelfServiceList.getId());
                    roleVsService.setMenuId(menu.getId());
                    roleVsService.setMenuOperate("1");
                    roleVsServiceDao.insert(roleVsService);
                }
            }

            //saveMenus(TreeUtils.handleTreeList(service.getMenus()), null,
            //        orgSelfServiceList.getId(), service.getServiceEndDate(), roleVsServiceId);
            List<MenuDict> menuDicts = service.getMenus() ;
            for(MenuDict menuDict :menuDicts){
                OrgSelfServiceVsMenu orgSelfServiceVsMenu = new OrgSelfServiceVsMenu();
                orgSelfServiceVsMenu.preInsert();
                orgSelfServiceVsMenu.setMenuId(menuDict.getId());
                orgSelfServiceVsMenu.setPid(menuDict.getPid());
                orgSelfServiceVsMenu.setHref(menuDict.getHref());
                //orgSelfServiceVsMenu.setMenuSort(Integer.parseInt(menuDict.getSort().toString()));
                orgSelfServiceVsMenu.setSelfServiceId(orgSelfServiceList.getId());
                orgSelfServiceVsMenu.setSysServiceId(service.getServiceId());
                orgSelfServiceVsMenuDao.insert(orgSelfServiceVsMenu);
            }

        }


        int i = dao.update(sysCompany);

        return i;
    }

    /**
     * 驳回组织机构审核
     *
     * @param sysCompany
     * @return
     */
    public int failPass(SysCompany sysCompany) {
        return dao.update(sysCompany);
    }

    private void saveMenus(List<MenuDict> menus, String parentId, String serviceId, Date endDate, String roleServiceId) {
        if (menus != null && menus.size() > 0) {
            for (int i = 0; i < menus.size(); i++) {
                MenuDict menu = menus.get(i);
                //自定义服务于菜单对照
                OrgSelfServiceVsMenu orgSelfServiceVsMenu = new OrgSelfServiceVsMenu();
                orgSelfServiceVsMenu.preInsert();   //设置主键ID
                orgSelfServiceVsMenu.setPid(parentId);
                orgSelfServiceVsMenu.setSelfServiceId(serviceId);
                orgSelfServiceVsMenu.setMenuId(menu.getId());
                orgSelfServiceVsMenu.setMenuSort(i + 1);
                orgSelfServiceVsMenu.setMenuEndDate(endDate);
                orgSelfServiceVsMenu.setDelFlag("0");
                orgSelfServiceVsMenuDao.insert(orgSelfServiceVsMenu);   //添加自定义服务于菜单对照数据

                saveMenus(menu.getChildren(), orgSelfServiceVsMenu.getMenuId(), serviceId, endDate, roleServiceId);
            }
        }
    }
}