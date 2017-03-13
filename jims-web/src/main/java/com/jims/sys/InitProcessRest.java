package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.api.InitProcessApi;
import com.jims.sys.entity.InitProcess;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.*;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by heren on 2016/5/16.
 */
@Produces("application/json")
@Path("init-process")
@Component
public class InitProcessRest {

    @Reference(version ="1.0.0")
    private InitProcessApi initProcessApi;


    /**
     * 保存
     * @param
     * @return
     * @author zq
     */
    @Path("save-list")
    @POST
    public StringData saveList(List<InitProcess> initProcesses) {
        String newUpdateDict = initProcessApi.saveList(initProcesses);
        StringData stringData = new StringData();
        if(newUpdateDict.equals("1")){
            stringData.setData("success");
        }
        return stringData;
    }

    /**
     * 根据orgId查询菜单List集合
     * @param
     * @return
     * @author zq
     */
    @Path("findListByOrgId")
    @GET
    public List<InitProcess>findListByOrgId(@QueryParam("orgId") String orgId,@Context HttpServletRequest request){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        orgId = (orgId==null||orgId.equals("")||orgId.equals("undefined"))?loginInfo.getOrgId():orgId;
        return initProcessApi.findByOrgId(orgId);
    }

    /**
     * 根据菜单表数据生成initProcess表数据
     * @author zq
     */
    @Path("find-menu-list")
    @GET
    public List<InitProcess>findMenuList(){
        List<InitProcess> list=initProcessApi.findMenuList();
        return list;
    }
    /*
    * 删除
    */
    @Path("del")
    @POST
    public StringData del(@QueryParam("id")String id) {
        String num = initProcessApi.delete(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 根据orgId查询菜单序列，并插入到OrgSelfServiceVsMenu的lise集合中，以便菜单树的生成
     * @param
     * @return
     * @author 娄会丽
     */
     @GET
     @Path("find-by-orgId")
     public List<OrgSelfServiceVsMenu> findByOrgId(@QueryParam("orgId") String orgId,@Context HttpServletRequest request) {
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        orgId = (orgId==null||orgId.equals("")||orgId.equals("undefined"))?loginInfo.getOrgId():orgId;
        List<InitProcess> ips = initProcessApi.findByOrgId(orgId);
        List<OrgSelfServiceVsMenu> menus = new ArrayList<OrgSelfServiceVsMenu>();
        OrgSelfServiceVsMenu ossvm = new OrgSelfServiceVsMenu();
        ossvm = new OrgSelfServiceVsMenu();
        ossvm.setId("100");
        ossvm.setSelfServiceId("100");
        ossvm.setMenuId("100");
        ossvm.setMenuOperate("0");
        ossvm.setMenuSort(0);
        ossvm.setPid("");
        ossvm.setDelFlag("0");
        ossvm.setMenuName("操作向导");
        ossvm.setHref("");
        menus.add(ossvm);
        for (InitProcess ip:ips) {
            ossvm = new OrgSelfServiceVsMenu();
            ossvm.setId(ip.getId());
            ossvm.setSelfServiceId(ip.getId());//服务ID
            ossvm.setSysServiceId(ip.getInitFlag());//初始化状态,因为OrgSelfServiceVsMenu中没有此属性，sysServiceId属性暂时无用，故用sysServiceId暂代
            ossvm.setMenuId(ip.getMenuId());
            ossvm.setMenuOperate("1");//修改或访问权限，0访问，1修改
            ossvm.setMenuSort(ip.getInitSort());//顺序
            ossvm.setPid("100");//
            ossvm.setDelFlag("0");
            ossvm.setMenuName(ip.getMenuName());
            ossvm.setHref(ip.getHref());
            menus.add(ossvm);
        }
        //去掉重复的菜单
        List<OrgSelfServiceVsMenu> lists = new ArrayList<OrgSelfServiceVsMenu>();
        Map<String, OrgSelfServiceVsMenu> map = new HashMap<String, OrgSelfServiceVsMenu>();
        for (int i = 0, j = (menus != null ? menus.size() : 0); i < j; i++) {
            map.put(menus.get(i).getMenuId(), menus.get(i));
        }
        //排序
        Set<String> sets = map.keySet();
        for (int i = 0, j = (menus != null ? menus.size() : 0); i < j; i++) {
            for (String key : map.keySet()) {
                if (key == menus.get(i).getMenuId()) {
                    lists.add(map.get(key));
                }
            }
        }

        return lists;
    }
    /**
     * 保存
     * @param id
     * @param initFlag
     * @return
     * @author lhl
     */
    @Path("save")
    @POST
    public StringData save(@QueryParam("id")String id ,@QueryParam("initFlag")String initFlag) {
        InitProcess initProcess0 = new InitProcess();
        initProcess0.setIsNewRecord(false);
        initProcess0.setId(id);
        initProcess0.setInitFlag(initFlag);
        String newUpdateDict = initProcessApi.save(initProcess0);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }
    /**
     * 保存
     * @param id
     * @return
     * @author lhl
     */
    @Path("findOneById")
    @GET
    public InitProcess findOneById(@QueryParam("id") String id) {
        InitProcess newUpdateDict = initProcessApi.get(id);
        StringData stringData = new StringData();
        stringData.setData("success");
        return newUpdateDict;

    }
}
