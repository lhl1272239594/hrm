package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryTaxApi;
import com.jims.fbd.hrm.salary.entity.SalaryTax;
import com.jims.fbd.hrm.salary.vo.SalaryTaxVo;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 个人所得税税率rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-tax")
public class SalaryTaxRest {
    @Reference(version = "1.0.0")
    private SalaryTaxApi salaryTaxApi;

    /**
     * 查询税率信息
     * @return
     */
    @Path("tax-list")
    @GET
    public PageData taxList(@QueryParam("orgId")String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTax salaryTax = new SalaryTax();
        PageData pageData=new PageData();
        if(!orgId.equals("")&&orgId!=null) {
            salaryTax.setOrgId(orgId);
        }
        Page<SalaryTax> page= salaryTaxApi.taxList(new Page<SalaryTax>(request, response),salaryTax);

        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增重复判断
     * @return
     */
    @GET
    @Path("if-tax-exist")
    public List<SalaryTax> findTaxsame(@QueryParam("orgId") String orgId, @QueryParam("baseNum") String baseNum, @QueryParam("rate") String rate, @QueryParam("lowLimit") String lowLimit, @QueryParam("highLimit") String highLimit, @QueryParam("decuteNum") String decuteNum, @QueryParam("taxId") String taxId) {

        return salaryTaxApi.findTaxsame(orgId,baseNum,rate,lowLimit,highLimit,decuteNum,taxId);
    }
    /**
     * 税率保存和修改
     * @param salaryTaxVo
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryTaxVo<SalaryTax> salaryTaxVo, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String createDept = loginInfo.getDeptId();
        String userName=p.getId();
        int num = 0;
        int count = 0;
        SalaryTax salaryTax = new SalaryTax();
        salaryTax.setTaxId(salaryTaxVo.getTaxId());
        salaryTax.setBaseNum(salaryTaxVo.getBaseNum());
        salaryTax.setLowLimit(salaryTaxVo.getLowLimit());
        salaryTax.setHighLimit(salaryTaxVo.getHighLimit());
        salaryTax.setRate(salaryTaxVo.getRate());
        salaryTax.setDecuteNum(salaryTaxVo.getDecuteNum());
        salaryTax.setOrgId(salaryTaxVo.getOrgId());
        salaryTax.setCreateBy(userName);
        salaryTax.setUpdateBy(userName);
        salaryTax.setCreateDept(createDept);
        if(salaryTaxVo.getTaxId().equals("")){
            salaryTax.preInsert();
            salaryTaxApi.save(salaryTax);
            salaryTaxApi.formateBase(salaryTax);
        }
        else{
            salaryTax.preUpdate();
            salaryTaxApi.update(salaryTax);
            salaryTaxApi.formateBase(salaryTax);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }

    /**
     * 启用
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("changeUp-enableFlag")
    @POST
    public StringData changeup(@QueryParam("taxId") String taxId) {
        salaryTaxApi.changeup(taxId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 停用
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("changeDown-enableFlag")
    @POST
    public StringData changedown(@QueryParam("taxId") String taxId) {
        salaryTaxApi.changedown(taxId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 启用停用
     * @param taxes
     * @return
     * @author 
     */
    @Path("enableFlag")
    @POST
    public StringData enableFlag(List<SalaryTax> taxes) {

        String num = salaryTaxApi.enableFlag(taxes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 删除
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del-tax")
    @POST
    public StringData del_tax(List<SalaryTax> taxes) {
        String num = salaryTaxApi.del_tax(taxes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
