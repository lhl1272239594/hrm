package com.jims.fbd.hrm.exam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.exam.api.ExamApi;
import com.jims.fbd.hrm.exam.api.ExamTestApi;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("exam")
public class ExamRest {

    @Reference(version = "1.0.0")
    private ExamApi ExamApi;
    @Reference(version = "1.0.0")
    private ExamTestApi ExamTestApi;


    /**
     * 根据组织机构查询试题分类
     *
     * @param orgId
     * @return
     * @author wangzhiming
     */
    @Path("itemList")
    @GET
    public List<QuestionItemVo> itemList(@QueryParam("orgId") String orgId) {
        List<QuestionItemVo> list = ExamApi.itemList(orgId);
        return list;
    }

    /**
     * 手工选题查询试题分类
     *
     * @param orgId
     * @return
     * @author wangzhiming
     */
    @Path("examItemList")
    @GET
    public List<QuestionItemVo> examItemList(@QueryParam("orgId") String orgId, @QueryParam("itemId") String itemId) {
        List<QuestionItemVo> list = ExamApi.examItemList(orgId, itemId);
        return list;
    }

    /**
     * 查询试题类型
     *
     * @return
     */
    @Path("findItemByParent")
    @GET
    public PageData projectList(@QueryParam("orgId") String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        QuestionItem questionItem = new QuestionItem();
        if (!orgId.equals("") && orgId != null) {
            questionItem.setOrgId(orgId);
        }
        Page<QuestionItem> page = ExamApi.findItemByParent(new Page<QuestionItem>(request, response), questionItem);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 新增修改试题类型
     *
     * @param questionItem
     * @return
     */
    @Path("itemMerge")
    @POST
    public StringData itemMerge(QuestionItem questionItem, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        OrgStaff orgStaff = (OrgStaff) session.getAttribute("OrgStaff");
        questionItem.setCreateBy(orgStaff.getPersionId());
        questionItem.setCreateDept(orgStaff.getDeptId());
        String num = ExamApi.itemMerge(questionItem);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 查看试题类型是否被占用
     *
     * @param questionItems
     * @return
     */
    @Path("checkItemIsUsed")
    @POST
    public StringData checkItemIsUsed(List<QuestionItem> questionItems) {

        String num = ExamApi.checkItemIsUsed(questionItems);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查看试题是否被占用
     *
     * @param questions
     * @return
     */
    @Path("checkQuestionIsUsed")
    @POST
    public StringData checkQuestionIsUsed(List<Question> questions) {

        String num = ExamApi.checkQuestionIsUsed(questions);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 启用停用试题类型
     *
     * @param questionItems
     * @return
     */
    @Path("itemState")
    @POST
    public StringData itemState(List<QuestionItem> questionItems) {

        String num = ExamApi.itemState(questionItems);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 删除试题类型
     *
     * @param questionItems
     * @return
     */
    @Path("delItem")
    @POST
    public StringData delItem(List<QuestionItem> questionItems) {

        String num = ExamApi.delItem(questionItems);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查询全部试题
     *
     * @param request
     * @param response
     * @return
     */

    @Path("questionList")
    @GET
    public PageData questionList(@QueryParam("orgId") String orgId, @QueryParam("itemId") String itemId, @QueryParam("state") String state, @QueryParam("type") String type, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        QuestionVo QuestionVo = new QuestionVo();

        if (!orgId.equals("") && orgId != null) {
            QuestionVo.setOrgId(orgId);
        }
        if (!itemId.equals("") && itemId != null) {
            QuestionVo.setItemId(itemId);
        }
        if (!state.equals("") && state != null) {
            QuestionVo.setState(state);
        }
        if (!type.equals("") && type != null) {
            QuestionVo.setTypeId(type);
        }
        Page<QuestionVo> page = ExamApi.questionList(orgId, new Page<QuestionVo>(request, response), QuestionVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 查询题型
     *
     * @return
     */
    @GET
    @Path("questionType")
    public List<QuestionType> questionType() {
        return ExamApi.questionType();
    }

    /**
     * 新增修改试题
     *
     * @param question
     * @return
     */
    @Path("questionMerge")
    @POST
    public StringData questionMerge(Question question, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        OrgStaff orgStaff = (OrgStaff) session.getAttribute("OrgStaff");
        question.setCreateBy(orgStaff.getPersionId());
        question.setCreateDept(orgStaff.getDeptId());
        String num = ExamApi.questionMerge(question);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 删除试题
     *
     * @param question
     * @return
     * @author wangzhiming
     */
    @Path("questionRemove")
    @POST
    public StringData questionRemove(List<Question> question) {

        String num = ExamApi.questionRemove(question);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 启用停用试题
     *
     * @param question
     * @return
     * @author wangzhiming
     */
    @Path("questionStatus")
    @POST
    public StringData questionStatus(List<Question> question) {

        String num = ExamApi.questionStatus(question);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查询试卷
     *
     * @param request
     * @param response
     * @return
     */

    @Path("examList")
    @GET
    public PageData examList(@QueryParam("orgId") String orgId, @QueryParam("state") String state, @QueryParam("type") String type, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamVo ExamVo = new ExamVo();
        if (!orgId.equals("") && orgId != null) {
            ExamVo.setOrgId(orgId);
        }
        if (!state.equals("") && state != null) {
            ExamVo.setState(state);
        }
        if (!type.equals("") && type != null) {
            ExamVo.setTypeId(type);
        }
        Page<ExamVo> page = ExamApi.examList(new Page<ExamVo>(request, response), ExamVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 考试查询
     *
     * @param request
     * @param response
     * @return
     */
    @Path("testList")
    @GET
    public PageData testList(@QueryParam("orgId") String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        TestVo TestVo = new TestVo();
        if (!orgId.equals("") && orgId != null) {
            TestVo.setOrgId(orgId);
        }
        HttpSession session = request.getSession();
        OrgStaff orgStaff = (OrgStaff) session.getAttribute("OrgStaff");
        TestVo.setUserId(orgStaff.getPersionId());
        Page<TestVo> page = ExamApi.testList(new Page<TestVo>(request, response), TestVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 查询出题方式
     *
     * @return
     */
    @GET
    @Path("examMethod")
    public List<ExamMethod> examMethod() {
        return ExamApi.examMethod();
    }

    /**
     * 发布试卷
     *
     * @param exam
     * @return
     */
    @Path("examPublish")
    @POST
    public StringData examPublish(Exam exam) {
        String num = ExamApi.examPublish(exam);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 新增修改试卷
     *
     * @param exam
     * @return
     */
    @Path("examMerge")
    @POST
    public StringData examMerge(Exam exam, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        exam.setCreateDept(loginInfo.getDeptId());
        String num = ExamApi.examMerge(exam);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 删除试卷
     *
     * @param exam
     * @return
     */
    @Path("examDelete")
    @POST
    public StringData examDelete(Exam exam) {
        String num = ExamApi.examDelete(exam);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 新增修改试卷题型
     *
     * @param ExamQuestionType
     * @return
     */
    @Path("examQueTypeMerge")
    @POST
    public StringData examQueTypeMerge(ExamQuestionType ExamQuestionType) {
        String num = ExamApi.examQueTypeMerge(ExamQuestionType);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 删除试卷题型
     *
     * @param ExamQuestionType
     * @return
     */
    @Path("delExamQueType")
    @POST
    public StringData delExamQueType(ExamQuestionType ExamQuestionType) {
        String num = ExamApi.delExamQueType(ExamQuestionType.getExamQueId());
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 随机出题
     *
     * @param examQuestionType
     * @return
     */
    @Path("randomQuestion")
    @POST
    public StringData randomQuestion(ExamQuestionType examQuestionType) {
        String num = ExamApi.randomQuestion(examQuestionType);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 手工选题
     *
     * @param examQuestionType
     * @return
     */
    @Path("selectQuestion")
    @POST
    public StringData selectQuestion(ExamQuestionType examQuestionType) {
        String a = ExamApi.selectQuestion(examQuestionType);
        StringData stringData = new StringData();
        stringData.setCode(a);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 删除手工选题
     *
     * @param examQuestionType
     * @return
     */
    @Path("delSelectQuestion")
    @POST
    public StringData delSelectQuestion(ExamQuestionType examQuestionType) {
        String a = ExamApi.delSelectQuestion(examQuestionType);
        StringData stringData = new StringData();
        stringData.setCode(a);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查询试卷
     *
     * @param request
     * @param response
     * @return
     */

    @Path("planList")
    @GET
    public PageData planList(@QueryParam("orgId") String orgId, @QueryParam("deptIds") String deptIds, @QueryParam("state") String state, @QueryParam("type") String type, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        PlanVo PlanVo = new PlanVo();
        if (!orgId.equals("") && orgId != null) {
            PlanVo.setOrgId(orgId);
        }
        if (!state.equals("") && state != null) {
            PlanVo.setState(state);
        }
        if (!type.equals("") && type != null) {
            PlanVo.setTypeId(type);
        }
        if (!deptIds.equals("") && deptIds != null) {
            PlanVo.setCreateDept(deptIds);
        }
        Page<PlanVo> page = ExamApi.planList(new Page<PlanVo>(request, response), PlanVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 题目设置-查询题型
     *
     * @param request
     * @param response
     * @return
     */

    @Path("examQuestionType")
    @GET
    public PageData examQuestionType(@QueryParam("orgId") String orgId, @QueryParam("examId") String examId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamQuestionTypeVo ExamQuestionTypeVo = new ExamQuestionTypeVo();
        if (!orgId.equals("") && orgId != null) {
            ExamQuestionTypeVo.setOrgId(orgId);
        }
        if (!examId.equals("") && examId != null) {
            ExamQuestionTypeVo.setExamId(examId);
        }
        Page<ExamQuestionTypeVo> page = ExamApi.examQuestionType(new Page<ExamQuestionTypeVo>(request, response), ExamQuestionTypeVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 查询已选试题
     *
     * @param request
     * @param response
     * @return
     */

    @Path("examQuestion")
    @GET
    public PageData examQuestion(@QueryParam("examQueTypeId") String examQueTypeId, @QueryParam("examId") String examId, @QueryParam("type") String type, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamQuestionVo ExamQuestionVo = new ExamQuestionVo();
        ExamQuestionVo.setExamQueTypeId(examQueTypeId);
        ExamQuestionVo.setExamId(examId);
        Page<ExamQuestionVo> page = ExamApi.examQuestion(new Page<ExamQuestionVo>(request, response), ExamQuestionVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 查询未选试题
     *
     * @param request
     * @param response
     * @return
     */

    @Path("examQuestionByItem")
    @GET
    public PageData examQuestionByItem(@QueryParam("typeId") String typeId, @QueryParam("itemId") String itemId, @QueryParam("examQueTypeId") String examQueTypeId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamQuestionVo examQuestionVo = new ExamQuestionVo();
        examQuestionVo.setExamQueTypeId(examQueTypeId);
        String[] sourceStrArray = itemId.split(",");
        String item = "";
        for (int i = 0; i < sourceStrArray.length; i++) {
            if (i == sourceStrArray.length - 1) {
                item += "'" + sourceStrArray[i] + "'";
            } else {
                item += "'" + sourceStrArray[i] + "',";
            }
        }
        examQuestionVo.setItemId(item);
        examQuestionVo.setTypeId(typeId);
        Page<ExamQuestionVo> page = ExamApi.examQuestionByItem(new Page<ExamQuestionVo>(request, response), examQuestionVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 新增修改试卷
     *
     * @param plan
     * @return
     */
    @Path("planMerge")
    @POST
    public StringData planMerge(Plan plan, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        plan.setCreateDept(loginInfo.getDeptId());
        String num = ExamApi.planMerge(plan);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 发布考试计划
     *
     * @param plan
     * @return
     */
    @Path("planPublish")
    @POST
    public StringData planPublish(Plan plan, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String num = ExamApi.planPublish(plan, loginInfo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 删除考试计划
     *
     * @param plan
     * @return
     */
    @Path("planRemove")
    @POST
    public StringData planRemove(Plan plan, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String num = ExamApi.planRemove(plan, loginInfo);
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
    public List<PersonVo> getPersonById(@QueryParam("id") String id, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<PersonVo> standardPersonVos = ExamApi.getPersonById(id);
        return standardPersonVos;
    }

    /**
     * 导出试卷
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getPaper")
    @POST
    public void getPaper(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("lx") String lx, @QueryParam("totalScore") String totalScore, @QueryParam("time") String time, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        boolean w = false;
        String path = "d:/";
        try {
            if (!"".equals(path)) {
                // 检查目录是否存在
                File fileDir = new File(path);
                if (fileDir.exists()) {
                    // 生成临时文件名称
                    String content = "<html xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                            "xmlns:w=\"urn:schemas-microsoft-com:office:word\"\n" +
                            "xmlns:st1=\"urn:schemas-microsoft-com:office:smarttags\"\n" +
                            "xmlns=\"http://www.w3.org/TR/REC-html40\">" +
                            "<head><!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val=\"Cambria Math\"/><m:brkBin m:val=\"before\"/><m:brkBinSub m:val=\"--\"/><m:smallFrac m:val=\"off\"/><m:dispDef/><m:lMargin m:val=\"0\"/> <m:rMargin m:val=\"0\"/><m:defJc m:val=\"centerGroup\"/><m:wrapIndent m:val=\"1440\"/><m:intLim m:val=\"subSup\"/><m:naryLim m:val=\"undOvr\"/></m:mathPr></w:WordDocument></xml><![endif]--></head>" +
                            "<div style=\"text-align: center\"><span style=\"font-size: 28px;font-family: 黑体\">" +
                            "" + name + "<br /> <br /> </span></div>" +
                            "<div style=\"text-align: center\"><span style=\"font-size: 14px;font-family: 黑体\">" +
                            "本试卷满分" + totalScore + "分，考试时间" + time + "分钟<br /> <br /> </span></div>";
                    if (lx.equals("0")) {
                        content += "<div  style=\"text-align: left\"><span style=\"font-size: 14px\"><span style=\"font-family: 黑体\">" +
                                "考生姓名：<br /> <br /> </span></span></div>";
                    }
                    List<ExamQuestionType> ExamQuestionType = ExamApi.getExamQuestionType(id);
                    int typeSort = 1;
                    int sort = 1;
                    int num = 1;
                    String typeName = null;
                    for (ExamQuestionType e : ExamQuestionType) {
                        List<ExamDetail> examDetails = ExamApi.getExamQuestion(e.getExamQueId());
                        if (examDetails.size() > 0) {
                            if (typeSort == 1)
                                typeName = "一";
                            if (typeSort == 2)
                                typeName = "二";
                            if (typeSort == 3)
                                typeName = "三";
                            if (typeSort == 4)
                                typeName = "四";
                            content += "<div  style=\"text-align: left\"><span style=\"font-size: 14px;font-family: 黑体\">" +
                                    "" + typeName + "、" + e.getTypeName() + "(共" + e.getNum() + "道题，每题" + e.getScore() + "分)<br /> <br /></span></div>";
                            typeSort++;
                        }
                        for (ExamDetail ed : examDetails) {
                            String bracket = "";
                            String anwser = ed.getQueAnswer();
                            if (ed.getTypeId().equals("1")) {
                                bracket = "（&#12288;&#12288;&#12288;&#12288;&#12288;）";
                                if (lx.equals("1")) {
                                    if (anwser.equals("0"))
                                        bracket = "（&#12288;&#12288;×&#12288;&#12288;）";
                                    if (anwser.equals("1"))
                                        bracket = "（&#12288;&#12288;√&#12288;&#12288;）";
                                }
                                content += "<div  style=\"text-align: left\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                        "" + sort + "、" + ed.getQueName() + "" + bracket + "</p></div>";
                            }
                            if (ed.getTypeId().equals("2")) {
                                String queName = ed.getQueName();
                                if (queName.contains("()")) {
                                    queName = queName.replace("()", "（&#12288;&#12288;&#12288;&#12288;&#12288;）");
                                }
                                if (queName.contains("（）")) {
                                    queName = queName.replace("（）", "（&#12288;&#12288;&#12288;&#12288;&#12288;）");
                                }
                                content += "<div  style=\"width:90%;margin:0 auto;\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                        "" + sort + "、" + queName + "" + bracket + "</p></div>";
                                String[] sourceStrArray = ed.getQueContent().split("\\^\\&\\*");
                                for (int i = 1; i <= Integer.valueOf(ed.getQueNum()); i++) {
                                    content += "<div  style=\"text-align: left\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                            "" + String.valueOf((char) (64 + i)) + "、" + sourceStrArray[i - 1] + "</p></div>";
                                }
                                if (lx.equals("1")) {
                                    bracket = "试题答案：" + String.valueOf((char) (64 + Integer.valueOf(anwser)));
                                    content += "<div  style=\"width:90%;margin:0 auto;\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                            "" + bracket + "</p></div>";
                                }
                            }
                            if (ed.getTypeId().equals("3")) {
                                String queName = ed.getQueName();
                                if (queName.contains("()")) {
                                    queName = queName.replace("()", "（&#12288;&#12288;&#12288;&#12288;&#12288;）");
                                }
                                if (queName.contains("（）")) {
                                    queName = queName.replace("（）", "（&#12288;&#12288;&#12288;&#12288;&#12288;）");
                                }
                                content += "<div  style=\"text-align: left;width:90%;margin:0 auto;\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                        "" + sort + "、" + queName + "" + bracket + "</p></div>";
                                String[] sourceStrArray = ed.getQueContent().split("\\^\\&\\*");
                                for (int i = 1; i <= Integer.valueOf(ed.getQueNum()); i++) {
                                    content += "<div  style=\"text-align: left\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                            "" + String.valueOf((char) (64 + i)) + "、" + sourceStrArray[i - 1] + "</p></div>";
                                }
                                if (lx.equals("1")) {
                                    bracket = "试题答案：";
                                    for (int i = 1; i <= anwser.length(); i++) {
                                        bracket += String.valueOf((char) (64 + Integer.valueOf(anwser.substring(i - 1, i))));
                                    }
                                    content += "<div  style=\"width:90%;margin:0 auto;\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                            "" + bracket + "</p></div>";
                                }
                            }
                            if (ed.getTypeId().equals("4")) {
                                content += "<div  style=\"width:90%;margin:0 auto;height:400px;\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                        "" + sort + "、" + ed.getQueName() + "</p>";
                                if (lx.equals("0")) {
                                    content += "<div  style=\"width:90%;margin:0 auto;height:400px;\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p>" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p>" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p>" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p>" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p>" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p>" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p>" +
                                            "<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%'><o:p>&nbsp;</o:p></span></p></p>";
                                }
                                if (lx.equals("1")) {
                                    content += "<div  style=\"width:90%;margin:0 auto;height:400px;\"><p style=\"font-size: 14px;font-family: 黑体\">" +
                                            "" + anwser + "</p>";
                                }
                                num++;
                            }
                            sort++;
                        }
                    }
                    content += "</html>";
                    byte b[] = content.getBytes();
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                    //输出文件
                    response.reset();

                    response.setHeader("Content-Disposition",
                            "attachment;filename=" +
                                    new String((name + ".doc").getBytes(),
                                            "iso-8859-1"));
                    response.setContentType("application/msword");
                    OutputStream ostream = response.getOutputStream();
                    //输出文件的话，new一个文件流
                    //FileOutputStream ostream = new FileOutputStream(path+ fileName);
                    poifs.writeFilesystem(ostream);
                    ostream.flush();
                    ostream.close();
                    bais.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 试题模板
     *
     * @param orgId
     * @param response
     * @param request
     */
    @Path("exportXls")
    @POST
    public void exportXls(@QueryParam("orgId") String orgId, @Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet pdt = workbook.createSheet("判断题");
        HSSFSheet danxt = workbook.createSheet("单选题");
        HSSFSheet duoxt = workbook.createSheet("多选题");
        HSSFSheet jdt = workbook.createSheet("简答题");
        List<QuestionItem> Items = ExamApi.getItems(orgId);
        String[] itemNames = new String[Items.size()];
        String[] itemIds = new String[Items.size()];
        for (int i = 0; i < Items.size(); i++) {
            itemNames[i] = i + 1 + "." + Items.get(i).getItemName();
            itemIds[i] = Items.get(i).getItemId();
        }
        HSSFRow row = null;
        HSSFCell cell = null;
        //标题样式
        HSSFCellStyle headStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);//字号
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平布局：居中
        headStyle.setFont(font);
        //自动换行样式
        HSSFCellStyle newlineStyle = workbook.createCellStyle();
        font.setFontName("宋体");
        newlineStyle.setWrapText(true);
        font.setFontHeightInPoints((short) 11);//字号
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        newlineStyle.setFont(font);
        int x = 5;
        int y = 100;
        int pdtCol = 10;//判断题3列
        int danxtCol = 10;//单选题4列
        int duoxtCol = 10;//多选题4列
        int jdtCol = 10;//简答题3列
        //添加单元格注释
        //创建判断题HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.
        HSSFPatriarch pdtPart = pdt.createDrawingPatriarch();
        //判断题注释
        HSSFComment pdtComment = pdtPart.createComment(new HSSFClientAnchor(0, 0, 50, 30, (short) 4, 0, (short) 7, 5));
        //设置注释内容
        pdtComment.setString(new HSSFRichTextString("对填写1，错误填写0"));

        //创建单选题HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.
        HSSFPatriarch danxtPart = danxt.createDrawingPatriarch();
        //题目注释
        HSSFComment danxtName = danxtPart.createComment(new HSSFClientAnchor(0, 0, 50, 30, (short) 4, 0, (short) 7, 5));
        //设置注释内容
        danxtName.setString(new HSSFRichTextString("请在答案处用相连的括号代替，例如：人的呼吸系统包括呼吸道和（）"));
        //选项注释
        HSSFComment danxtContent = danxtPart.createComment(new HSSFClientAnchor(0, 0, 50, 30, (short) 4, 0, (short) 7, 5));
        //设置注释内容
        danxtContent.setString(new HSSFRichTextString("选项之间以#间隔，例如：选项1#选项2#选项3#选项4"));
        //答案注释
        HSSFComment danxtAnswer = danxtPart.createComment(new HSSFClientAnchor(0, 0, 50, 30, (short) 4, 0, (short) 7, 5));
        //设置注释内容
        danxtAnswer.setString(new HSSFRichTextString("答案A填写1,B填写2，单选题答案只能有一个。"));

        //创建多选题HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.
        HSSFPatriarch duoxtPart = duoxt.createDrawingPatriarch();
        //题目注释
        HSSFComment duoxtName = duoxtPart.createComment(new HSSFClientAnchor(0, 0, 50, 30, (short) 4, 0, (short) 7, 5));
        //设置注释内容
        duoxtName.setString(new HSSFRichTextString("请在答案处用相连的括号代替，例如：人的呼吸系统包括呼吸道和（）"));
        //选项注释
        HSSFComment duoxtContent = duoxtPart.createComment(new HSSFClientAnchor(0, 0, 50, 30, (short) 4, 0, (short) 7, 5));
        //设置注释内容
        duoxtContent.setString(new HSSFRichTextString("选项之间以#间隔,例如：选项1#选项2#选项3#选项4"));
        //答案注释
        HSSFComment duoAnswer = duoxtPart.createComment(new HSSFClientAnchor(0, 0, 50, 30, (short) 4, 0, (short) 7, 5));
        //设置注释内容
        duoAnswer.setString(new HSSFRichTextString("答案A填写1,B填写2，以此类推并且按照顺序填写。例如答案为ABC，那么就填写123"));
        //按照样式生成x*y行
        //生成判断题sheet
        for (int i = 0; i < x * y; i++) {
            row = pdt.createRow(i);
            for (int j = 0; j <= pdtCol; j++) {
                cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellStyle(headStyle);
                }
                if (j == 1) {
                    if (i == 0) {
                        cell.setCellStyle(headStyle);
                    } else {
                        cell.setCellStyle(newlineStyle);
                    }
                }
                if (j == 2) {
                    cell.setCellStyle(headStyle);
                }
            }
        }
        pdt = setHSSFValidation(pdt, itemNames, 0, 500, 0, 0);// 第一列的前501行都设置为选择列表形
        row = pdt.getRow(0);
        row.getCell(0).setCellValue("试题分类");
        row.getCell(1).setCellValue("试题题目");
        row.getCell(2).setCellValue("试题答案");
        row.getCell(2).setCellComment(pdtComment);//添加注释
        pdt.setColumnWidth(0, 20 * 256);
        pdt.setColumnWidth(1, 100 * 256);
        pdt.setColumnWidth(2, 20 * 256);
        //设置隐藏列存储试题分类ID
        for (int pdt_i = 1; pdt_i < itemIds.length; pdt_i++) {
            row = pdt.getRow(pdt_i);
            row.getCell(9).setCellValue(itemIds[pdt_i]);
        }
        pdt.setColumnHidden(9, true);
        //生成单选题sheet
        for (int i = 0; i < x * y; i++) {
            row = danxt.createRow(i);
            for (int j = 0; j <= danxtCol; j++) {
                cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellStyle(headStyle);
                }
                if (j == 1 || j == 2) {
                    if (i == 0) {
                        cell.setCellStyle(headStyle);
                    } else {
                        cell.setCellStyle(newlineStyle);
                    }
                }
                if (j == 3) {
                    cell.setCellStyle(headStyle);
                }
            }
        }
        danxt = setHSSFValidation(danxt, itemNames, 0, 500, 0, 0);// 第一列的前501行都设置为选择列表形
        row = danxt.getRow(0);
        row.getCell(0).setCellValue("试题分类");
        row.getCell(1).setCellValue("试题题目");
        row.getCell(1).setCellComment(danxtName);//添加注释
        row.getCell(2).setCellValue("选项");
        row.getCell(2).setCellComment(danxtContent);//添加注释
        row.getCell(3).setCellValue("试题答案");
        row.getCell(3).setCellComment(danxtAnswer);//添加注释

        danxt.setColumnWidth(0, 20 * 256);
        danxt.setColumnWidth(1, 60 * 256);
        danxt.setColumnWidth(2, 60 * 256);
        danxt.setColumnWidth(3, 20 * 256);
        //设置隐藏列存储试题分类ID
        for (int danxt_i = 1; danxt_i < itemIds.length; danxt_i++) {
            row = danxt.getRow(danxt_i);
            row.getCell(9).setCellValue(itemIds[danxt_i]);
        }
        danxt.setColumnHidden(9, true);
        //生成多选题sheet
        for (int i = 0; i < x * y + 1; i++) {
            row = duoxt.createRow(i);
            for (int j = 0; j <= duoxtCol; j++) {
                cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellStyle(headStyle);
                }
                if (j == 1 || j == 2) {
                    if (i == 0) {
                        cell.setCellStyle(headStyle);
                    } else {
                        cell.setCellStyle(newlineStyle);
                    }
                }
                if (j == 3) {
                    cell.setCellStyle(headStyle);
                }
            }
        }
        duoxt = setHSSFValidation(duoxt, itemNames, 0, 500, 0, 0);// 第一列的前501行都设置为选择列表形
        row = duoxt.getRow(0);
        row.getCell(0).setCellValue("试题分类");
        row.getCell(1).setCellValue("试题题目");
        row.getCell(1).setCellComment(duoxtName);//添加注释
        row.getCell(2).setCellValue("选项");
        row.getCell(2).setCellComment(duoxtContent);//添加注释
        row.getCell(3).setCellValue("试题答案");
        row.getCell(3).setCellComment(duoAnswer);//添加注释

        duoxt.setColumnWidth(0, 20 * 256);
        duoxt.setColumnWidth(1, 60 * 256);
        duoxt.setColumnWidth(2, 60 * 256);
        duoxt.setColumnWidth(3, 20 * 256);
        //设置隐藏列存储试题分类ID
        for (int duoxt_i = 1; duoxt_i < itemIds.length; duoxt_i++) {
            row = duoxt.getRow(duoxt_i);
            row.getCell(9).setCellValue(itemIds[duoxt_i]);
        }
        duoxt.setColumnHidden(9, true);
        //生成简答题sheet
        for (int i = 0; i < x * y; i++) {
            row = jdt.createRow(i);
            for (int j = 0; j <= jdtCol; j++) {
                cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellStyle(headStyle);
                }
                if (j == 1 || j == 2) {
                    if (i == 0) {
                        cell.setCellStyle(headStyle);
                    } else {
                        cell.setCellStyle(newlineStyle);
                    }
                }
                if (j == 3) {
                    cell.setCellStyle(headStyle);
                }
            }
        }
        jdt = setHSSFValidation(jdt, itemNames, 0, 500, 0, 0);// 第一列的前501行都设置为选择列表形
        row = jdt.getRow(0);
        row.getCell(0).setCellValue("试题分类");
        row.getCell(1).setCellValue("试题题目");
        row.getCell(2).setCellValue("试题答案");

        jdt.setColumnWidth(0, 20 * 256);
        jdt.setColumnWidth(1, 80 * 256);
        jdt.setColumnWidth(2, 80 * 256);
        //设置隐藏列存储试题分类ID
        for (int jdt_i = 1; jdt_i < itemIds.length; jdt_i++) {
            row = jdt.getRow(jdt_i);
            row.getCell(9).setCellValue(itemIds[jdt_i]);
        }
        jdt.setColumnHidden(9, true);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=question.xls");
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
                                              String[] textlist, int firstRow, int endRow, int firstCol,
                                              int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
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
        String[] contentArray = null;//选项按照分隔符转换为数组
        String itemId = null;//试题分类ID
        List<Question> questions = new ArrayList<Question>();
        Question question = null;
        Row nRow = null;
        //错误
        int repeat = 0;
        //记录第几行重复
        String num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sheetAt = workbook.getSheetAt(i);
            int end = sheetAt.getLastRowNum();
            sheetName = sheetAt.getSheetName();
            //判断
            if (i == 0) {
                if (!sheetName.equals("判断题")) {
                    sb.append("第" + i + "个工作簿应为判断题");
                    repeat++;
                    break;
                }
            }
            if (i == 1) {
                if (!sheetName.equals("单选题")) {
                    sb.append("第" + i + "个工作簿应为单选题");
                    repeat++;
                    break;
                }
            }
            if (i == 2) {
                if (!sheetName.equals("多选题")) {
                    sb.append("第" + i + "个工作簿应为多选题");
                    repeat++;
                    break;
                }
            }
            if (i == 3) {
                if (!sheetName.equals("简答题")) {
                    sb.append("第" + i + "个工作簿应为简答题");
                    repeat++;
                    break;
                }
            }
            if (repeat > 0) {
                break;
            }
            for (int j = 1; j <= end; j++) {
                if (repeat > 0) {
                    break;
                }
                nRow = sheetAt.getRow(j);
                //判断第一个单元格是否为空，如果为空  整行都不插入
                if (nRow.getCell(0) == null || getCellValue((nRow.getCell(0))).equals("") || nRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    if (!getCellValue((nRow.getCell(1))).equals("")) {
                        sb.append(sheetName + ":第" + j + "行没有选择试题分类");
                        repeat++;
                        break;
                    } else {
                        break;
                    }
                }
                if (!getCellValue((nRow.getCell(0))).contains(".")) {
                    sb.append(sheetName + ":第" + j + "行没有选择试题分类");
                    repeat++;
                    break;
                }
                String itemName = getCellValue((nRow.getCell(0)));
                int index = Integer.valueOf(itemName.substring(0, itemName.indexOf(".")));
                if (sheetAt.getRow(index).getCell(9) == null) {
                    sb.append(sheetName + ":第" + j + "行没有选择试题分类");
                    repeat++;
                    break;
                }
                itemId = getCellValue((sheetAt.getRow(index).getCell(9)));
                name = getCellValue((nRow.getCell(1)));
                //判断是否填写题目
                if (nRow.getCell(1) == null || name.equals("")) {
                    sb.append(sheetName + ":第" + j + "行没有填写试题题目");
                    repeat++;
                    break;
                }
                //判断是否填写题目
                if (name.getBytes("utf-8").length > 1000) {
                    sb.append(sheetName + ":第" + j + "行题目长度过长");
                    repeat++;
                    break;
                }
                //如果为判断题
                if (i == 0) {
                    answer = getCellValue((nRow.getCell(2)));
                    //判断答案的长度
                    if (nRow.getCell(2) == null || answer.length() != 1 || !isInteger(answer)) {
                        sb.append(sheetName + ":第" + j + "行填写答案格式错误,请按照注释填写");
                        repeat++;
                        break;
                    }
                    //如果答案长度为1，判断答案是否为0或1
                    if (answer.length() == 1) {
                        if (Integer.valueOf(answer) != 0 && Integer.valueOf(answer) != 1) {
                            sb.append(sheetName + ":第" + j + "行填写答案错误,请按照注释填写");
                            repeat++;
                            break;
                        }
                    }
                }
                //如果为单选题
                if (i == 1) {
                    content = getCellValue((nRow.getCell(2)));
                    contentArray = content.split("#");
                    //判断选项的长度
                    if (nRow.getCell(2) == null || content.equals("")) {
                        sb.append(sheetName + ":第" + j + "行没有填写试题选项,请按照注释填写");
                        repeat++;
                        break;
                    }
                    if (!content.contains("#")) {
                        sb.append(sheetName + ":第" + j + "行填写试题选项格式错误,请按照注释填写");
                        repeat++;
                        break;
                    }
                    //判断选项过长
                    if (getCellValue((nRow.getCell(1))).getBytes("utf-8").length > 1000) {
                        sb.append(sheetName + ":第" + j + "行选项长度过长");
                        repeat++;
                        break;
                    }
                    if (contentArray.length <= 1) {
                        sb.append(sheetName + ":第" + j + "行填写试题选项格式错误,请按照注释填写");
                        repeat++;
                        break;
                    }
                    answer = getCellValue((nRow.getCell(3)));
                    //判断答案的长度
                    if (nRow.getCell(3) == null || answer.length() != 1 || !isInteger(answer)) {
                        sb.append(sheetName + ":第" + j + "行填写答案格式错误,请按照注释填写");
                        repeat++;
                        break;
                    }
                    //如果答案长度为1，判断答案是否正确
                    if (answer.length() == 1) {
                        if (Integer.valueOf(answer) < 1 || Integer.valueOf(answer) > contentArray.length) {
                            sb.append(sheetName + ":第" + j + "行填写答案错误,请按照注释填写");
                            repeat++;
                            break;
                        }
                    }
                }
                //如果为多选题
                if (i == 2) {
                    content = getCellValue((nRow.getCell(2)));
                    contentArray = content.split("#");
                    //判断选项的长度
                    if (nRow.getCell(2) == null || content.equals("")) {
                        sb.append(sheetName + ":第" + j + "行没有填写试题选项,请按照注释填写");
                        repeat++;
                        break;
                    }
                    if (!content.contains("#")) {
                        sb.append(sheetName + ":第" + j + "行填写试题选项格式错误,请按照注释填写");
                        repeat++;
                        break;
                    }
                    //判断选项过长
                    if (content.getBytes("utf-8").length > 1000) {
                        sb.append(sheetName + ":第" + j + "行选项长度过长");
                        repeat++;
                        break;
                    }
                    if (contentArray.length <= 1) {
                        sb.append(sheetName + ":第" + j + "行填写试题选项格式错误,请按照注释填写");
                        repeat++;
                        break;
                    }
                    answer = getCellValue((nRow.getCell(3)));
                    //判断答案的长度
                    if (nRow.getCell(3) == null || answer.length() < 1 || answer.length() > contentArray.length) {
                        sb.append(sheetName + ":第" + j + "行填写答案格式错误,请按照注释填写");
                        repeat++;
                        break;
                    }
                    //如果答案长度正确，判断答案格式是否正确
                    if (answer.length() <= contentArray.length && answer.length() >= 1 || !isInteger(answer)) {
                        int answerRepeat = 0;
                        for (int x = 0; x < answer.length(); x++) {
                            if (Integer.valueOf(answer.substring(x, x + 1)) < 1 || Integer.valueOf(answer.substring(x, x + 1)) > contentArray.length) {
                                answerRepeat++;
                            }
                            if (x != answer.length() - 1) {
                                if (Integer.valueOf(answer.substring(x, x + 1)) > Integer.valueOf(answer.substring(x + 1, x + 2))) {
                                    answerRepeat++;
                                }
                            }
                        }
                        if (answerRepeat > 0) {
                            sb.append(sheetName + ":第" + j + "行填写答案填写错误,请按照注释填写");
                            repeat++;
                            break;
                        }
                    }
                }
                //如果为简答题
                if (i == 3) {
                    answer = getCellValue((nRow.getCell(2)));
                    //判断答案的长度
                    if (nRow.getCell(2) == null || answer.equals("")) {
                        sb.append(sheetName + ":第" + j + "行没有填写答案");
                        repeat++;
                        break;
                    }
                    //判断选项过长
                    if (answer.getBytes("utf-8").length > 1000) {
                        sb.append(sheetName + ":第" + j + "行答案长度过长");
                        repeat++;
                        break;
                    }
                }
                question = new Question();
                question.setQueId(IdGen.uuid());
                question.setOrgId(orgId);
                question.setTypeId(String.valueOf(i + 1));
                question.setQueName(name);
                int n = ExamApi.getQueName(question);
                if (n > 0) {
                    sb.append(sheetName + ":第" + j + "行题目已存在");
                    repeat++;
                    break;
                }
                question.setItemId(itemId);
                if (i == 1 || i == 2) {
                    question.setQueNum(String.valueOf(contentArray.length));
                    content = content.replace("#", "^&*");
                    question.setQueContent(content);
                }
                question.setQueAnswer(answer);
                question.setCreateBy(orgStaff.getPersionId());
                question.setCreateOrg(orgId);
                question.setCreateDept(orgStaff.getDeptId());
                questions.add(question);
            }
            if (repeat > 0) {
                break;
            }
        }
        System.out.println(System.currentTimeMillis());
        String error = null;
        if (repeat > 0) {
            error = sb.toString();
        } else {
            String flag = ExamApi.insertByExcel(questions);
            error = "成功插入<font color='red' > " + flag + " </font>条数据 失败<font color='red'> " + String.valueOf(Integer.valueOf(questions.size()) - Integer.valueOf(flag)) + " </font>条";
        }
        System.out.println(System.currentTimeMillis());
        StringData stringData = new StringData();
        stringData.setData(error);
        return stringData;
    }

    //判断单元格的类型
    private String getCellValue(Cell cell) {
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