package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.common.utils.TreeUtils;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.dao.ButtonDictDao;
import com.jims.sys.dao.MenuDictDao;
import com.jims.sys.dao.ServiceSelfVsSysDao;
import com.jims.sys.entity.ButtonDict;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构服务事务管理层
 * @author lgx
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class ButtonDictBo extends CrudImplService<OrgServiceListDao, OrgServiceList> {

    @Autowired
    private OrgSelfServiceListDao selfServiceDao;
    @Autowired
    private ButtonDictDao bdo;
    @Autowired
    private ServiceSelfVsSysDao vsSysDao;
    @Autowired
    private MenuDictDao menuDictDao;



    public List<OrgSelfServiceVsMenu> findSelfServiceMenu(String serviceId, String roleId,boolean isTree,String orgid) {

        List<OrgSelfServiceVsMenu> orgSelfServiceVsMenus = bdo.findSelfServiceId(serviceId, roleId,orgid);
        if(isTree && orgSelfServiceVsMenus != null && orgSelfServiceVsMenus.size() > 1){
            Map<String,String> keyMap = new HashMap<String, String>();
            keyMap.put("id","menuId");
            keyMap.put("pid","pid");
            keyMap.put("children","children");

            return TreeUtils.handleTreeList(orgSelfServiceVsMenus,keyMap);
        }
        return orgSelfServiceVsMenus;
    }
    public void upbtnrole(List<ButtonDict> buttonDict)
    {
        /**
         *  var buttonDict = {};
         buttonDict.menuName =orgId;
         buttonDict.pid = rolerow.id;
         buttonDict.permission = rolerow.roleName;
         buttonDict.menuLevel =serrow.serviceId;
         buttonDict.target=serrow.serviceName;
         buttonDict.id = arrBtns[index].id;//serrow.serviceName;
         buttonDict.href=arrBtns[index].name;
         buttonDict.icon=arrBtns[index].check;
         t.orgid='' and t.roleid='' and t.serviceid='' and t.menuid='' and t.btname=''
         */
        int n=0;//bdo.getscmbr();
        for(ButtonDict bd:buttonDict)
        {
            String orgid=bd.getMenuName();
            String roleid=bd.getPid();
            String serviceid=bd.getMenuLevel();
            String menuid=bd.getId();
            String btname=bd.getHref();
            n=bdo.getscmbr(orgid,roleid,serviceid,menuid,btname);
            if(n==0)
            {
                //添加 insert
                /**
                 *  ORGID,
                 ROLEID,
                 ROLENAME,
                 SERVICEID,
                 SERVICENAME,
                 MENUID,
                 BTNAME,
                 BTSTATE
                 */
                bdo.inscmbr(orgid,roleid,bd.getPermission(),serviceid,bd.getTarget(),menuid,btname,bd.getIcon());;
            }
            else
            {
                //更新 update
                bdo.upscmbr(orgid,roleid,serviceid,menuid,btname,bd.getIcon());
            }
        }

    }
    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAllList(String orgId, String roleid, String serid, String mid) {

        //查询出所有的科室信息
        List<DeptDict> list = bdo.findAllData(orgId,roleid,serid,mid);

        //查询出所有的科室属性的类型
        List<OrgDeptPropertyDict> listProperty = bdo.findProperty(orgId);
        if (listProperty.size() > 0) {
            //遍历所有的科室信息
            for (int i = 0; i < list.size(); i++) {
                StringBuilder sb = new StringBuilder();
                //得到每一个对象的科室属性，以；进行切割
                String[] str = list.get(i).getDeptPropertity().split(";");
                //遍历获得的数组

                for (int y = 0; y < str.length; y++) {
                    //得到每一个切割后的科室属性值
                    if (StringUtils.isNotBlank(str[y])) {
                        //拿科室属性值和科室的类型去数据库中查询科室属性名称
                        OrgDeptPropertyDict orgDeptPropertyDict=new OrgDeptPropertyDict();
                        orgDeptPropertyDict.setPropertyType(listProperty.get(y).getPropertyType());
                        orgDeptPropertyDict.setOrgId(orgId);
                        orgDeptPropertyDict.setPropertyValue(str[y]);
                        List<OrgDeptPropertyDict> listName = bdo.findByCondition(orgDeptPropertyDict);
                        if (listName.get(0) == null) {
                            sb.append(" ");
                        } else {
                            sb.append(listName.get(0).getPropertyName() + " ");
                        }
                    }
                }
                list.get(i).setDeptPropertityName(sb.toString());
            }
        }
        return list;
    }

    /**
     * 更新数据权限
     * @param buttonDict
     */
    public void upmdata(ButtonDict buttonDict)
    {
        String depids [] = buttonDict.getHref().split(",");
        bdo.delmdata(buttonDict);
        for(int i=0;i<depids.length;i++)
        {
            ButtonDict bd = buttonDict;
            bd.setHref(depids[i]);
            bdo.upmdata(bd);
        }
    }

    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenuBtn(String serviceId, String roleId, boolean isTree, String orgid,String meid) {

        List<OrgSelfServiceVsMenu> orgSelfServiceVsMenus = bdo.findSelfbtns(serviceId, roleId,orgid,meid);
       /* if(isTree && orgSelfServiceVsMenus != null && orgSelfServiceVsMenus.size() > 1){
            Map<String,String> keyMap = new HashMap<String, String>();
            keyMap.put("id","menuId");
            keyMap.put("pid","pid");
            keyMap.put("children","children");

            return TreeUtils.handleTreeList(orgSelfServiceVsMenus,keyMap);
        }*/
        return orgSelfServiceVsMenus;
    }
}