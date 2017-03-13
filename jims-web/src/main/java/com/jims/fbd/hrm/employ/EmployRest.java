package com.jims.fbd.hrm.employ;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.employ.api.EmployApi;
import com.jims.fbd.hrm.employ.entity.Employ;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 招聘管理rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("employ")
public class EmployRest {
    @Reference(version = "1.0.0")
    private EmployApi employApi;

    /**
     * 查询招聘编辑信息
     *
     * @return
     */
    @Path("employ-list")
    @GET
    public PageData employList(@QueryParam("orgId")String orgId, @QueryParam("name") String name, @QueryParam("deptId")String deptId, @QueryParam("post") String post, @QueryParam("state") String state, @QueryParam("deptIds")String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Employ employ=new Employ();
        employ.setOrgId(orgId);
        employ.setName(name);
        employ.setDeptId(deptId);
        employ.setPost(post);
        employ.setState(state);
        employ.setDeptIds(deptIds);
        Page<Employ> page= employApi.employList(new Page<Employ>(request, response),employ);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询招聘信息
     *
     * @return
     */
    @Path("employ-search")
    @GET
    public PageData employSearch(@QueryParam("orgId")String orgId, @QueryParam("name") String name, @QueryParam("deptId")String deptId, @QueryParam("post") String post, @QueryParam("deptIds")String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Employ employ=new Employ();
        employ.setOrgId(orgId);
        employ.setName(name);
        employ.setDeptId(deptId);
        employ.setPost(post);
        employ.setDeptIds(deptIds);
        Page<Employ> page= employApi.employSearch(new Page<Employ>(request, response),employ);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增招聘重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<Employ> findEmploysame(@QueryParam("orgId") String orgId, @QueryParam("name") String name) {
        return employApi.findEmploysame(orgId,name);
    }
    /**
     * 保存和修改
     * @param employ
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(Employ employ, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String createDept = loginInfo.getDeptId();
        String num = employApi.merge(employ,createDept);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }


    /**
     * 批量删除
     *
     * @param
     * @author 
     * @version 2016-09-25
     * @return
     */
    @Path("del-employ")
    @POST
    public StringData del_employ(List<Employ> employs) {
        String num = employApi.del_employ(employs);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 发布招聘
     *
     * @param
     * @author 
     * @version 2016-09-25
     * @return
     */
    @Path("publish-employ")
    @POST
    public StringData publish_employ(List<Employ> employs, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = employApi.publish_employ(employs,userName);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 结束招聘
     *
     * @param
     * @author 
     * @version 2016-09-25
     * @return
     */
    @Path("end-employ")
    @POST
    public StringData end_employ(List<Employ> employs) {
        String num = employApi.end_employ(employs);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 重新发布
     *
     * @param
     * @author 
     * @version 2016-09-25
     * @return
     */
    @Path("redeal")
    @POST
    public StringData redeal(List<Employ> employs, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = employApi.redeal(employs,userName);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
