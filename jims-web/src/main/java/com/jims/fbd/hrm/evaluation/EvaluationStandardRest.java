package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.fbd.hrm.evaluation.api.EvaluationProjectApi;
import com.jims.fbd.hrm.evaluation.api.EvaluationStandardApi;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.exam.entity.Question;
import com.jims.sys.entity.OrgStaff;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("standard")
public class EvaluationStandardRest {

    @Reference(version = "1.0.0")
    private EvaluationStandardApi evaluationStandardApi;

    /**
     * 查询一级项目
     *
     * @param request
     * @param response
     * @return
     */

    @Path("projectList")
    @GET
    public List<ProjectVo> projectList(@QueryParam("orgId") String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        ProjectVo projectVo = new ProjectVo();
        if(!orgId.equals("")&&orgId!=null){
            projectVo.setOrgId(orgId);
        }
        List<ProjectVo> projectVos= evaluationStandardApi.projectList(projectVo);
        return projectVos;
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
    public PageData standardByProject(@QueryParam("orgId") String orgId, @QueryParam("pcode") String pcode, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        StandardVo standardVo = new StandardVo();
        if(!orgId.equals("")&&orgId!=null){
            standardVo.setOrgId(orgId);
        }
        if(!pcode.equals("")&&pcode!=null){
            standardVo.setPcode(pcode);
        }
        Page<StandardVo> page= evaluationStandardApi.standardByProject(new Page<StandardVo>(request, response), standardVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增考评标准项目
     *
     * @param standardVo
     * @return
     */

    @Path("saveStandard")
    @POST
    public StringData saveStandard(StandardVo standardVo,  @Context HttpServletRequest request) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        standardVo.setCreateDept(orgStaff.getDeptId());
        String num = evaluationStandardApi.saveStandard(standardVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 修改考评标准状态
     *
     * @param standardMergeVo
     * @return
     */

    @Path("standardMerge")
    @POST
    public StringData standardMerge(StandardMergeVo standardMergeVo) {
        String num = evaluationStandardApi.standardMerge(standardMergeVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
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
    public List<StandardPersonVo> getPersonById(@QueryParam("id") String id, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<StandardPersonVo> standardPersonVos= evaluationStandardApi.getPersonById(id);
        return standardPersonVos;
    }
    /**
     * 查看考评标准是否被占用
     * @param standardVos
     * @return
     */
    @Path("checkStandardIsUsed")
    @POST
    public StringData checkTypeIsUsed(List<StandardVo> standardVos) {

        String num = evaluationStandardApi.checkStandardIsUsed(standardVos);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


    @POST
    @Path("up-xls")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public StringData uploadXls(FormDataMultiPart form, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        System.out.println(System.currentTimeMillis());
        OrgStaff orgStaff = (OrgStaff) session.getAttribute("OrgStaff");
        String orgId = orgStaff.getOrgId();
        //获取文件流
        FormDataBodyPart filePart = form.getField("myFiles");
        //把表单内容转换成流
        InputStream fileInputStream = filePart.getValueAs(InputStream.class);
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheetAt = workbook.getSheetAt(0);
        String sheetName = null;
        String name = null;
        String answer = null;//答案
        String content = null;//选项
        String pcode=null;
        String scode=null;
        String[] contentArray = null;//选项按照分隔符转换为数组
        String itemId=null;//试题分类ID
        List<Question> questions = new ArrayList<Question>();
        Question question = null;
        Row nRow = null;
        //错误
        int repeat = 0;
        //记录第几行重复
        String num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            sheetAt = workbook.getSheetAt(i);
            int end = sheetAt.getLastRowNum();
            sheetName = sheetAt.getSheetName();

            for (int j = 1; j <= end; j++) {
                if (repeat > 0) {
                    break;
                }
                nRow = sheetAt.getRow(j);

                    String pname = getCellValue((nRow.getCell(0)));
                    pcode=evaluationStandardApi.getPcode(pname);
                    String sname=getCellValue((nRow.getCell(1)));
                    scode=evaluationStandardApi.getScode(sname,pcode);
                name=getCellValue((nRow.getCell(2)));
                String score=getCellValue((nRow.getCell(3)));
                String kpi=null;
                if (nRow.getCell(4) == null || getCellValue((nRow.getCell(4))).equals("")) {
                    kpi="0";
                }
                if(getCellValue((nRow.getCell(4))).contains("是"))
                    kpi="1";
                StandardVo s=new StandardVo();
                s.setOrgId(orgId);
                s.setPcode(scode);
                s.setScore(score);
                s.setKpi(kpi);
                s.setName(name);
                s.setCreateBy("8F98E865E006470793C6D3BBC9EA47E7");
                s.setCreateDept(orgStaff.getDeptId());
                List<StandardPersonVo> standardPersonVos=new ArrayList<StandardPersonVo>();

                String userName=getCellValue((nRow.getCell(5)));
                StandardPersonVo sp=evaluationStandardApi.getUser(userName);
                standardPersonVos.add(sp);
                s.setStandardPersonVos(standardPersonVos);
                evaluationStandardApi.saveStandard(s);


            }
            if (repeat > 0) {
                break;
            }
        }
        System.out.println(System.currentTimeMillis());
        String error = null;
        if (repeat > 0) {
            error = sb.toString();
        }
        else {
        }
        System.out.println(System.currentTimeMillis());
        StringData stringData=new StringData();
        stringData.setData(error);
        return stringData;
    }

    //判断单元格的类型
    private  String getCellValue(Cell cell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        // System
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
