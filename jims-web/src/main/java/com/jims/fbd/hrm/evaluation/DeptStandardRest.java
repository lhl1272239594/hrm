package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.fbd.hrm.evaluation.api.DeptConfigApi;
import com.jims.fbd.hrm.evaluation.api.DeptStandardApi;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import com.jims.sys.entity.OrgStaff;
import org.apache.ibatis.annotations.Param;
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
@Path("deptStandard")
public class DeptStandardRest {

    @Reference(version = "1.0.0")
    private DeptStandardApi deptStandardApi;

    /**
     * 查询模板分类
     *
     * @param request
     * @param response
     * @return
     */
    @Path("getMouldType")
    @GET
    public List<Mould>  getMouldType(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return deptStandardApi.getMouldType();
    }
    /**
     * 查询模板名称
     *
     * @param request
     * @param response
     * @return
     */
    @Path("getMouldName")
    @GET
    public List<Mould>  getMouldName(@QueryParam("pid") String pid,
                                     @Context HttpServletRequest request,
                                     @Context HttpServletResponse response) {
        return deptStandardApi.getMouldName(pid);
    }
    /**
     * 导入模板
     *
     * @param Mould
     * @return
     */
    @Path("importMould")
    @POST
    public StringData importMould(Mould Mould) {
        String num=null;
        num=deptStandardApi.importMould(Mould);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 查询标准
     *
     * @param request
     * @param response
     * @return
     */
    @Path("findListByid")
    @GET
    public List<StandardVo> findListByid(@QueryParam("id")String id, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        return deptStandardApi.findListByid(id);
    }
    /**
     * 根据科室查询标准
     *
     * @param request
     * @param response
     * @return
     */
    @Path("standardByProject")
    @GET
    public List<StandardVo> standardByProject(@QueryParam("pcode")String pcode,@QueryParam("deptId")String deptId,@QueryParam("orgId")String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        StandardVo standardVo=new StandardVo();
        standardVo.setPcode(pcode);
        standardVo.setTempletId(deptId);
        standardVo.setOrgId(orgId);
        List<StandardVo> standardVos= deptStandardApi.standardByProject(standardVo);
        return standardVos;
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
            num=deptStandardApi.saveStandard(t);
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
        String deptId=templetVo.getId();
        int num=0;
        for(StandardVo s:standardVos){
            deptStandardApi.delStandard(s.getId(),deptId);
            num++;
        }
        if (num>0) {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 修改标准
     *
     * @param standardVo
     * @return
     */
    @Path("editStandard")
    @POST
    public StringData editStandard(StandardVo standardVo,  @Context HttpServletRequest request) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        standardVo.setCreateDept(orgStaff.getDeptId());
        String num = deptStandardApi.editStandard(standardVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
}
