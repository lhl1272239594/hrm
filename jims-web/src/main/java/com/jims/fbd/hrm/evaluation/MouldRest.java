package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.fbd.hrm.evaluation.api.EvaluationTempletApi;
import com.jims.fbd.hrm.evaluation.api.MouldApi;
import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.fbd.hrm.evaluation.vo.TempletStandardVo;
import com.jims.fbd.hrm.evaluation.vo.TempletVo;
import com.jims.sys.entity.OrgStaff;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("mould")
public class MouldRest {

    @Reference(version = "1.0.0")
    private MouldApi MouldApi;
    /**
     * 查询模板类型
     *
     * @param request
     * @param response
     * @return
     */
    @Path("getType")
    @GET
    public List<Mould> getType(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return MouldApi.getType();
    }
    /**
     * 查询模板
     *
     * @param request
     * @param response
     * @return
     */
    @Path("getTemplet")
    @GET
    public List<Mould> getTemplet(@QueryParam("id")String id,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return MouldApi.getTemplet(id);
    }

    /**
     * 新增修改模板
     *
     * @param Mould
     * @return
     */
    @Path("Merge")
    @POST
    public StringData Merge(Mould Mould,  @Context HttpServletRequest request) {
        String num = MouldApi.Merge(Mould);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 删除模板
     * @param Moulds
     * @return
     */
    @Path("delMould")
    @POST
    public StringData delMould(List<Mould> Moulds) {

        String num = MouldApi.delMould(Moulds);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查询模板标准
     *
     * @param request
     * @param response
     * @return
     */
    @Path("findListByid")
    @GET
    public List<StandardVo> findListByid(@QueryParam("id")String id, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        return MouldApi.findListByid(id);
    }
    /**
     * 保存考评标准
     *
     * @param templetVo
     * @return
     */
    @Path("saveStandard")
    @POST
    public StringData saveStandard(TempletVo templetVo, @Context HttpServletRequest request) {
        List<StandardVo> standardVos=templetVo.getStandard();
        String num=null;
        if(standardVos.size()>0){
            TempletStandardVo t=new TempletStandardVo();
            t.setId(templetVo.getId());
            t.setStandardVos(templetVo.getStandard());
            num=MouldApi.saveStandard(t);
        }
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 删除考评标准
     *
     * @param templetVo
     * @return
     */
    @Path("delStandardById")
    @POST
    public StringData delStandardById(TempletVo templetVo, @Context HttpServletRequest request) {
        List<StandardVo> standardVos=templetVo.getStandard();
        String num=null;
        if(standardVos.size()>0){
            TempletStandardVo t=new TempletStandardVo();
            t.setId(templetVo.getId());
            t.setStandardVos(templetVo.getStandard());
            num=MouldApi.delStandardById(t);
        }
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 根据项目查询标准
     *
     * @param request
     * @param response
     * @return
     */

    @Path("standardByProject")
    @GET
    public List<StandardVo> standardByProject(@QueryParam("pcode")String pcode,@QueryParam("templetId")String templetId,@QueryParam("orgId")String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        StandardVo standardVo=new StandardVo();
        standardVo.setPcode(pcode);
        standardVo.setTempletId(templetId);
        standardVo.setOrgId(orgId);
        standardVo.setCreateBy(orgStaff.getPersionId());
        List<StandardVo> standardVos= MouldApi.standardByProject(standardVo);
        return standardVos;
    }
}
