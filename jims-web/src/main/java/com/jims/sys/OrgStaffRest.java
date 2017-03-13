package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.entity.*;
import com.jims.sys.vo.OrgStaffVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.*;


/**
 * Created by yangruidong on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("orgStaff")
public class OrgStaffRest {
    @Reference(version = "1.0.0")
    private OrgStaffApi orgStaffApi;

    @Reference(version = "1.0.0")
    private PersionInfoApi persionInfoApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        String orgId = request.getParameter("orgId");
        String deptId = request.getParameter("deptId");
        OrgStaffVo orgStaffVo = new OrgStaffVo();

        orgStaffVo.setOrgId(orgId);
        orgStaffVo.setDeptId(deptId);

        Page<OrgStaffVo> page = orgStaffApi.findPageByVo(new Page<OrgStaffVo>(request, response), orgStaffVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }


    /**
     * 根据身份证号查询相关的信息
     *
     * @param cardNo
     * @return
     */
    @Path("findInfoByCardNo")
    @GET
    public PersionInfo findInfoByCardNo(@QueryParam("cardNo") String cardNo) {
        PersionInfo persionInfo = persionInfoApi.findInfoByCardNo(cardNo);
        return persionInfo;
    }

    /**
     * 通过persionId查询密码  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Path("findPasswordByPersionId")
    @GET
    public SysUser findPasswordByPersionId(@QueryParam("persionId") String persionId)
    {
        return  orgStaffApi.findPasswordByPersionId(persionId);
    }

    /**
     * 根据人员ID和组织机构ID查询该人员在某家组织机构的员工信息
     * @param personId 人员ID
     * @param orgId 组织机构ID
     * @return 员工信息
     * @author fengyuguang
     */
    @GET
    @Path("find-staff-by-orgId-personId")
    public OrgStaff findStaffByPersonIdOrgId(@QueryParam("persionId")String personId, @QueryParam("orgId")String orgId){
        return orgStaffApi.findStaffByPersonIdOrgId(personId,orgId);
    }

    /**
     * 通过persionId查询密码  ，用于回显数据
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Path("findTitleByPersionId")
    @GET
    public OrgStaff findStaffByPersionId(@QueryParam("persionId") String persionId, @QueryParam("orgId") String orgId) {
        return orgStaffApi.findStaffByPersionId(persionId,orgId);
    }


    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public OrgStaff get(String id) {
        OrgStaff orgStaff = orgStaffApi.get(id);
        return orgStaff;
    }

    /**
     * 保存修改方法
     *
     * @param orgStaffVo
     * @return
     */
    @Path("save")
    @POST
    public StringData save(OrgStaffVo orgStaffVo) {

        PersionInfo persionInfo = new PersionInfo();
        SysUser sysUser = new SysUser();
        if (orgStaffVo.getPassword() != "") {
            sysUser.setPassword(orgStaffVo.getPassword());
        }
        OrgStaff orgStaff = new OrgStaff();
        String[] array=orgStaffVo.getRole();


        persionInfo.setPhoneNum(orgStaffVo.getPhoneNum());
        persionInfo.setNickName(orgStaffVo.getNickName());
        persionInfo.setEmail(orgStaffVo.getEmail());
        persionInfo.setCardNo(orgStaffVo.getCardNo());
        persionInfo.setName(orgStaffVo.getName());
        persionInfo.setNation(orgStaffVo.getNation());
        persionInfo.setSex(orgStaffVo.getSex());
        persionInfo.setId(orgStaffVo.getId());
        persionInfo.setInputCode(orgStaffVo.getInputCode());
        persionInfo.setType(orgStaffVo.getType());
        persionInfo.setClassify(orgStaffVo.getClassify());
        persionInfo.setSkill(orgStaffVo.getSkill());
        persionInfo.setSkillLevel(orgStaffVo.getSkillLevel());
        persionInfo.setTitle(orgStaffVo.getTitle());
        persionInfo.setTitle_level(orgStaffVo.getTitle_level());
        persionInfo.setTitleLevel(orgStaffVo.getTitleLevel());
        persionInfo.setEducation(orgStaffVo.getEducation());
        persionInfo.setEducationFinal(orgStaffVo.getEducationFinal());
        persionInfo.setEducationTime(orgStaffVo.getEducationTime());
        persionInfo.setEducationFinalTime(orgStaffVo.getEducationFinalTime());
        persionInfo.setWorkTime(orgStaffVo.getWorkTime());
        persionInfo.setComeTime(orgStaffVo.getComeTime());
        persionInfo.setPolitic(orgStaffVo.getPolitic());
        persionInfo.setAge(orgStaffVo.getAge());
        persionInfo.setMarry(orgStaffVo.getMarry());
        persionInfo.setAddress(orgStaffVo.getAddress());
        persionInfo.setExp(orgStaffVo.getExp());
        persionInfo.setRemark(orgStaffVo.getRemark());
        persionInfo.setPic(orgStaffVo.getPic());
        persionInfo.setDeptId(orgStaffVo.getDeptId());
        persionInfo.setOrgId(orgStaffVo.getOrgId());
        persionInfo.setRoleName(orgStaffVo.getRoleName());
        orgStaff.setDeptId(orgStaffVo.getDeptId());
        orgStaff.setOrgId(orgStaffVo.getOrgId());
        orgStaff.setTitle(orgStaffVo.getTitle());
        orgStaff.setPersionId(orgStaffVo.getId());
        String num = orgStaffApi.insertOrgStaffAndPersion(persionInfo, sysUser, orgStaff, array);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }


        return null;

    }


    /**
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = orgStaffApi.deleteByAll(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null) {
            stringData.setData("success");
        }
        return stringData;
    }

    /**
     *查询人员角色信息
     *
     *
     * @return
     */
    @Path("findRole")
    @GET
    public List<OrgRole> findRole(@QueryParam("staffId") String staffId) {
        List<OrgRole> role = orgStaffApi.getRole(staffId);
        return role;

    }

    /**
     * 根据员工ID查询员工拥有的角色下所有的服务
     * @param staffId 员工ID
     * @return 角色对应服务的list集合
     * @author fengyuguang
     */
    @GET
    @Path("find-serviceId-by-staffId")
    public List<OrgRoleVsService> findServiceId(@QueryParam("staffId")String staffId){
        return orgStaffApi.findServiceId(staffId);
    }

    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @return
     * @author fengyuguang
     */
    @GET
    @Path("find-list-by-serviceId")
    public List<OrgSelfServiceVsMenu> findByServiceId(@QueryParam("serviceId")String serviceId, @QueryParam("staffId")String staffId){
        List<OrgSelfServiceVsMenu> menus = orgStaffApi.findByServiceId(serviceId,staffId);
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

    /**
     * 检索机构科室的员工
     * @param orgId
     * @param deptId
     * @return
     */
    @Path("findList")
    @GET
    public List<OrgStaffVo> findList(@QueryParam("orgId") String orgId, @QueryParam("deptId") String deptId) {
        OrgStaffVo orgStaffVo = new OrgStaffVo();

        orgStaffVo.setOrgId(orgId);
        orgStaffVo.setDeptId(deptId);

        Page<OrgStaffVo> page = orgStaffApi.findPageByVo(new Page<OrgStaffVo>(), orgStaffVo);
        return page.getList();
    }

}
