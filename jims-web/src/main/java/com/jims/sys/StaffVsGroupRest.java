package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.api.StaffVsGroupApi;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.StaffVsGroup;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.OrgStaffVo;
import com.jims.sys.vo.StaffVsGroupVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangruidong on 2016/5/25 0024.
 */
@Component
@Produces("application/json")
@Path("staff-vs-group")
public class StaffVsGroupRest {

    @Reference(version = "1.0.0")
    private StaffVsGroupApi staffVsGroupApi;


    /**
     * 根据组织机构查询用户组的全部信息
     *
     * @param orgId
     * @return
     * @author yangruidong
     */
    @Path("list")
    @GET
    public List<StaffVsGroupVo>  findAllListByOrgId(@QueryParam("orgId") String orgId)
    {
        List<StaffVsGroupVo> list=staffVsGroupApi.findAllListByOrgId(orgId);
        return list;
    }

    /**
     * 保存  增删改      工作组
     *
     * @param staffVsGroupVo
     * @return
     * @author yangruidong
     */
    @Path("saveVsGroup")
    @POST
    public StringData saveVsGroup(StaffVsGroupVo<StaffVsGroup> staffVsGroupVo) throws Exception {
        List<StaffVsGroup> newUpdateDict = new ArrayList<StaffVsGroup>();
        newUpdateDict = staffVsGroupApi.saveVsGroup(staffVsGroupVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     *
     *根据组织机构id查询组织机构下的人员
     * @param orgId
     * @return
     */
    @Path("listName")
    @GET
    public List<StaffVsGroupVo>  findOrgStaffByOrgId(@QueryParam("orgId") String orgId)
    {
        List<StaffVsGroupVo> list=staffVsGroupApi.findOrgStaff(orgId);
        return list;
    }

    /**
     *
     *根据组id查询组类名称
     * @param groupId
     * @return
     */
    @Path("getGroupClass")
    @GET
    public List<StaffVsGroupVo> findGroupClass(@QueryParam("groupId")String groupId) {
       List<StaffVsGroupVo> list= staffVsGroupApi.findGroupClass(groupId);
        return list;
    }

    /**
     *
     *根据组id查询组下的人员
     * @param groupId
     * @return
     */
    @Path("listStaff")
    @GET
    public List<StaffVsGroupVo> findStaffByGroupId(@QueryParam("groupId")String groupId,@QueryParam("orgId")String orgId) {
        List<StaffVsGroupVo> list= staffVsGroupApi.findStaffByGroupId(groupId,orgId);
        return list;
    }

    /**
     *
     *根据组id查询组下的人员
     * @param staffId
     * @return
     */
    @Path("getStaff")
    @GET
    public StaffVsGroup findStaffByStaffId(@QueryParam("staffId")String staffId) {
        StaffVsGroup list= staffVsGroupApi.findStaffByStaffId(staffId);
        return list;
    }
}
