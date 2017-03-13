package com.jims.fbd.hrm.socialSecurity;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.socialSecurity.api.SocialSecurityItemApi;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityItem;
import com.jims.fbd.hrm.socialSecurity.vo.SocialSecurityItemVo;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by yangchen on 2016/8/23 0.
 */
@Component
@Produces("application/json")
@Path("socialSecurityItem")
public class SocialSecurityItemRest {
    @Reference(version = "1.0.0")
    private SocialSecurityItemApi socialSecurityItemApi;
    /**
     * 业务处理：查询-分页
     *
     * @return
     */
    @GET
    @Path("ss-item-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @QueryParam("userId") String userId,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        SocialSecurityItem socialSecurityItem = new SocialSecurityItem();
        socialSecurityItem.setOrgId(orgId);
        socialSecurityItem.setUserId(userId);

        Page<SocialSecurityItem> page = socialSecurityItemApi.findList(
                new Page<SocialSecurityItem>(request, response), socialSecurityItem);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    @GET
    @Path("ss-item-all-list")
    public List<SocialSecurityItem> findAllList(@QueryParam("orgId") String orgId) {


        return socialSecurityItemApi.findAllList(orgId);
    }
    /**
     * 新增重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<SocialSecurityItem> findSame(@QueryParam("orgId") String orgId,@QueryParam("editItemDes") String editItemDes,@QueryParam("id") String id) {
        return socialSecurityItemApi.findSame(orgId,editItemDes,id);
    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(SocialSecurityItemVo<SocialSecurityItem> socialSecurityItemVo,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String personId=p.getId();
        String createDept = loginInfo.getDeptId();
        int num = 0;
        int count = 0;
        String id = socialSecurityItemVo.getSsItemId();
        SocialSecurityItem socialSecurityItem = new SocialSecurityItem();

        if (id.equals("999")) {

            socialSecurityItem.setSsItemId(IdGen.uuid());
            socialSecurityItem.setSsItemDes(socialSecurityItemVo.getSsItemDes());
            socialSecurityItem.setSsItemTypeId(socialSecurityItemVo.getSsItemTypeId());
            socialSecurityItem.setOrgId(socialSecurityItemVo.getOrgId());
            socialSecurityItem.setUserId(socialSecurityItemVo.getUserId());
            socialSecurityItem.setDeptId(socialSecurityItemVo.getDeptId());
            socialSecurityItem.setCreateBy(personId);
            socialSecurityItem.setCreateDept(createDept);
            count = count + Integer.parseInt(socialSecurityItemApi.insertPrimary(socialSecurityItem));


        } else {

            socialSecurityItem.setSsItemId(id);
            socialSecurityItem.setSsItemDes(socialSecurityItemVo.getSsItemDes());
            socialSecurityItem.setSsItemTypeId(socialSecurityItemVo.getSsItemTypeId());
            socialSecurityItem.setOrgId(socialSecurityItemVo.getOrgId());
            socialSecurityItem.setUserId(socialSecurityItemVo.getUserId());
            socialSecurityItem.setDeptId(socialSecurityItemVo.getDeptId());
            socialSecurityItem.setUpdateBy(personId);
            count = count + Integer.parseInt(socialSecurityItemApi.updatePrimary(socialSecurityItem));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 删除占用判断
     *
     * @return
     */
    @POST
    @Path("if-occupy")
    public StringData findOccupy(@QueryParam("ssItemId") String ssItemId,@QueryParam("orgId") String orgId ) {
        SocialSecurityItem socialSecurityItem = new SocialSecurityItem();
        socialSecurityItem.setOrgId(orgId);
        socialSecurityItem.setSsItemId(ssItemId);
        String num = socialSecurityItemApi.findOccupy(socialSecurityItem);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null&&num =="yes") {
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Path("ss-item-del")
    @POST
    public StringData dataDel(SocialSecurityItemVo<SocialSecurityItem> socialSecurityItemVo) {

        String dataId=socialSecurityItemVo.getSsItemId();
        String num = socialSecurityItemApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

