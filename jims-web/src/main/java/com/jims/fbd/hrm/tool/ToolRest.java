package com.jims.fbd.hrm.tool;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.fbd.hrm.tool.api.ToolApi;
import com.jims.fbd.hrm.tool.entity.Tool;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.SysServiceParam;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.util.*;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("tool")
public class ToolRest {

    @Reference(version = "1.0.0")
    private ToolApi toolApi;

    /**
     * 根据类型和输入的拼音码检索字典
     * @param type  类型
     * @return
     * @author fengyuguang
     */
    @Path("find-list-by-type")
    @GET
    public List<Tool> listByType(@QueryParam("type")String type,@QueryParam("value")String value){

        Tool tool = new Tool();
        tool.setType(type);
        tool.setValue(value);
        return toolApi.listByType(tool);
    }
    /**
     *查询人员角色信息
     *
     *
     * @return
     */
    @Path("findRole")
    @GET
    public List<OrgRole> findRole(@QueryParam("staffId") String staffId,@QueryParam("orgId") String orgId) {
        List<OrgRole> role = toolApi.getRole(staffId, orgId);
        return role;

    }
//获得登录用户组织机构名称
    @Path("find-org-name")
    @GET
    public List<Tool> findOrgName(@QueryParam("orgId") String orgId,
                                  @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        Tool tool = new Tool();
        tool.setOrgId(orgId);
        Tool orgNameList =toolApi.findOrgName(tool);
        session.setAttribute("OrgNameList",orgNameList);

        return null;
    }
    @Path("find-person-list")
    @GET
    public List<Tool> findPersonList(@QueryParam("userId")String userId,@QueryParam("orgId")String orgId){

        Tool tool = new Tool();
        tool.setUserId(userId);
        tool.setOrgId(orgId);
        List<Tool> list =toolApi.findPersonList(tool);
        return list;
    }
    @Path("find-dept-list")
    @GET
    public List<Tool> findDeptList(@QueryParam("deptId")String deptId,@QueryParam("type")String type){

        Tool tool = new Tool();
        tool.setDeptId(deptId);
        tool.setType(type);
        return toolApi.findDeptList(tool);
    }
    @Path("find-user-tree")
    @GET
    public List<Tool> findUserTree(@QueryParam("orgId") String orgId) {

        Tool tool = new Tool();
        tool.setOrgId(orgId);

        List<Tool> list = toolApi.findUserTree(tool);
        return list;
    }
    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @return
     * @author fengyuguang
     */
    @GET
    @Path("getMenu")
    public List<OrgSelfServiceVsMenu> getMenu(@QueryParam("serviceId")String serviceId, @QueryParam("staffId")String staffId, @QueryParam("roleId")String roleId){
        List<OrgSelfServiceVsMenu> menus = toolApi.getMenu(serviceId,staffId,roleId);
        //去掉重复的菜单
        List<OrgSelfServiceVsMenu> lists = new ArrayList<OrgSelfServiceVsMenu>();
        Map<String, OrgSelfServiceVsMenu> map = new HashMap<String, OrgSelfServiceVsMenu>();
        for(int i = 0, j = (menus != null ? menus.size() : 0); i < j; i++){
            map.put(menus.get(i).getMenuId(),menus.get(i));
        }
        //排序
        Set<String> sets = map.keySet();
        for (int i = 0, j = (menus != null ? menus.size() : 0); i < j; i++) {
            for (String key : map.keySet()) {
                if(key == menus.get(i).getMenuId()){
                    lists.add(map.get(key));
                }
            }
        }

        return lists;
    }
}
