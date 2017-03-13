package com.jims.fbd.hrm.notice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.notice.api.NoticeApi;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 公告管理rest层
 *
 * @author 
 * @version 2016-09-22
 */
@Component
@Produces("application/json")
@Path("notice")
public class NoticeRest {
    @Reference(version = "1.0.0")
    private NoticeApi noticeApi;

    /**
     * 查询公告信息
     *
     * @return
     */
    @Path("notice-list")
    @GET
    public PageData noticeList(@QueryParam("orgId")String orgId, @QueryParam("name") String name, @QueryParam("state") String state,@QueryParam("start") String start,@QueryParam("end") String end, @QueryParam("deptIds") String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Notice notice=new Notice();
        notice.setOrgId(orgId);
        notice.setName(name);
        notice.setState(state);
        notice.setStart(start);
        notice.setEnd(end);
        notice.setDeptIds(deptIds);
        Page<Notice> page= noticeApi.noticeList(new Page<Notice>(request, response),notice);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询我的公告信息
     *
     * @return
     */
    @Path("mynotice-list")
    @GET
    public PageData mynoticeList(@QueryParam("orgId")String orgId, @QueryParam("name") String name, @QueryParam("personId") String personId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Notice notice=new Notice();
        notice.setOrgId(orgId);
        notice.setName(name);
        notice.setPersonId(personId);
        Page<Notice> page= noticeApi.mynoticeList(new Page<Notice>(request, response),notice);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 保存和修改
     * @param notice
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(Notice notice, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String createDept = loginInfo.getDeptId();
        String num = noticeApi.merge(notice,userName,createDept);
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
    @Path("del-notice")
    @POST
    public StringData del_notice(List<Notice> notices) {
        String num = noticeApi.del_notice(notices);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 发布公告
     *
     * @param
     * @author 
     * @version 2016-09-25
     * @return
     */
    @Path("publish-notice")
    @POST
    public StringData publish_notice(List<Notice> notices, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = noticeApi.publish_notice(notices,userName);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查看授权人员
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getPersonById")
    @GET
    public List<PersonVo> getPersonById(@QueryParam("id") String id, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<PersonVo> standardPersonVos= noticeApi.getPersonById(id);
        return standardPersonVos;
    }

}
