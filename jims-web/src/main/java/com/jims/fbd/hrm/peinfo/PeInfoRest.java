package com.jims.fbd.hrm.peinfo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.peinfo.api.PeInfoApi;
import com.jims.fbd.hrm.peinfo.entity.HumanSkill;
import com.jims.fbd.hrm.peinfo.entity.MyInfo;
import com.jims.fbd.hrm.peinfo.vo.MyInfoVo;
import com.jims.fbd.hrm.peinfo.vo.MyInfoVo1;
import com.jims.fbd.hrm.tool.entity.Tool;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 人员管理rest层
 *
 * @author 
 * @version 2016-08-17
 */
@Component
@Produces("application/json")
@Path("peinfo")
public class PeInfoRest {

    @Reference(version = "1.0.0")
    private PeInfoApi peinfoapi;

    /**
     * 异步加载表格
     * @return
     */
    @Path("getmyinfo")
    @GET
    public List<MyInfo> getmyinfo(@Context HttpServletRequest request, @Context HttpServletResponse response, @QueryParam("personId") String personId, @QueryParam("orgId") String orgId) {
        List<MyInfo> list = new ArrayList<MyInfo>();
        list = peinfoapi.getmyinfo(personId,orgId);
        request.setAttribute("list",list);
        return list;
    }

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
        String name = request.getParameter("name");
        String cardNo = request.getParameter("cardNo");
        String roleName = request.getParameter("roleName");
        String sex = request.getParameter("sex");
        String nation = request.getParameter("nation");
        String title = request.getParameter("title");
        String title_level = request.getParameter("title_level");
        String age1 = request.getParameter("age1");
        String age2 = request.getParameter("age2");
        String education = request.getParameter("education");
        String educationFinal = request.getParameter("education_final");
        String politic = request.getParameter("politic");
        String type = request.getParameter("type");
        String classify = request.getParameter("classify");
        String skill = request.getParameter("skill");
        String level = request.getParameter("level");
        String salaryLevel = request.getParameter("salaryLevel");
        String marry = request.getParameter("marry");
        String pic = request.getParameter("pic");
        String address = request.getParameter("address");
        String exp = request.getParameter("exp");
        String remark = request.getParameter("remark");
        String deptIds = request.getParameter("deptIds");
        MyInfo myInfo = new MyInfo();
        myInfo.setOrgId(orgId);
        myInfo.setDeptId(deptId);
        myInfo.setName(name);
        myInfo.setCardNo(cardNo);
        myInfo.setRoleName(roleName);
        myInfo.setSex(sex);
        myInfo.setNation(nation);
        myInfo.setTitle(title);
        myInfo.setTitleLevel(title_level);
        myInfo.setAge1(age1);
        myInfo.setAge2(age2);
        myInfo.setEducation(education);
        myInfo.setEducationFinal(educationFinal);
        myInfo.setPolitic(politic);
        myInfo.setType(type);
        myInfo.setClassify(classify);
        myInfo.setSkill(skill);
        myInfo.setLevel(level);
        myInfo.setSalaryLevel(salaryLevel);
        myInfo.setMarry(marry);
        myInfo.setPic(pic);
        myInfo.setAddress(address);
        myInfo.setExp(exp);
        myInfo.setRemark(remark);
        myInfo.setDeptIds(deptIds);
        Page<MyInfo> page = peinfoapi.findPageByVo(new Page<MyInfo>(request, response), myInfo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 只检索登录人的信息
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list1")
    @GET
    public List<MyInfo> list1(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("personId") String personId, @QueryParam("orgId") String orgId) {
        List<MyInfo> list = new ArrayList<MyInfo>();
        list = peinfoapi.findPageByVo1(personId,orgId);
        request.setAttribute("list",list);
        return list;
    }

    /**
     * 保存修改方法
     *
     * @param myInfo
     * @return
     */
    @Path("save")
    @POST
    public StringData save(MyInfo myInfo) {

        PersionInfo persionInfo = new PersionInfo();
        SysUser sysUser = new SysUser();
        MyInfo myinfo = new MyInfo();
        String[] array=myInfo.getRole();
        persionInfo.setPhoneNum(myInfo.getPhoneNum());
        persionInfo.setEmail(myInfo.getEmail());
        persionInfo.setCardNo(myInfo.getCardNo());
        persionInfo.setName(myInfo.getName());
        persionInfo.setNation(myInfo.getNation());
        persionInfo.setSex(myInfo.getSex());
        persionInfo.setId(myInfo.getId());
        persionInfo.setInputCode(myInfo.getInputCode());
        
        myinfo.setDeptId(myInfo.getDeptId());
        myinfo.setOrgId(myInfo.getOrgId());
        myinfo.setTitle(myInfo.getTitle());
        myinfo.setPersionId(myInfo.getId());
        String num = peinfoapi.insertOrgStaffAndPersion(persionInfo, sysUser, myinfo, array);
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
        String num = peinfoapi.deleteByAll(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null) {
            stringData.setData("success");
        }
        return stringData;
    }

    @Path("upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public StringData upLoad(FormDataMultiPart form, @Context HttpServletRequest request, @Context HttpServletResponse response)
            throws ServletException, IOException {
        StringData stringData = new StringData();
        HttpSession session = request.getSession();
        Tool tool = (Tool) session.getAttribute("OrgNameList");
        //获得组织机构对应的拼音
        String orgName = getPinYinHeadChar(tool.getOrgName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String ym = sdf.format(new Date());
        //得到上传文件的保存目录，将上传的文件放于单独文件夹
        String savePath = "d:/upload";
        //文件保存目录URL
        String saveUrl  =  "/upload_file";
        String realSaveUrl = saveUrl+ "/"+orgName+"/StaffPic/"+ym+"/";
        String realSavePath = savePath+ "/"+orgName+"/StaffPic/"+ym+"/";
        //获取文件流
        FormDataBodyPart filePart = form.getField("up_img");
        //把表单内容转换成流
        InputStream fileInputStream = filePart.getValueAs(InputStream.class);
        FormDataContentDisposition formDataContentDisposition = filePart.getFormDataContentDisposition();
        String filename = formDataContentDisposition.getFileName();
        //创建文件夹
        File file = new File(realSavePath);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        String resultName="";
        resultName= generateShortUuid()+ "&"+new String(filename.getBytes("ISO8859-1"), "UTF-8");
        String resultUrl = realSaveUrl+resultName;
        try {
            String filePath = realSavePath + resultName;
            File upFile = new File(filePath);
            //保存文件
            FileUtils.copyInputStreamToFile(fileInputStream, upFile);
        } catch (IOException ex) {
        }
        stringData.setFileName(resultName);
        stringData.setFileUrl(resultUrl);
        return stringData;
    }


    //将医院中文名称转化为拼音首写字母
    public static String getPinYinHeadChar(String str) {
        char[] t1 = null;
        t1 = str.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += Character.toString(t1[i]);
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    private  String generateShortUuid() {
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 重置密码
     * @param staffId
     * @return
     * @author 
     */
    @Path("back-password")
    @POST
    public StringData back(@QueryParam("staffId") String staffId, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = peinfoapi.back(staffId,userName);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查询
     *
     * @return
     */
    @Path("skill-list")
    @GET
    public PageData skillList(@QueryParam("orgId")String orgId,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HumanSkill humanSkill=new HumanSkill();
        humanSkill.setOrgId(orgId);
        Page<HumanSkill> page= peinfoapi.skillList(new Page<HumanSkill>(request, response),humanSkill);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<HumanSkill> findSkillsame(@QueryParam("orgId") String orgId, @QueryParam("skill") String skill, @QueryParam("skillId") String skillId) {
        return peinfoapi.findSkillsame(orgId,skill,skillId);
    }

    /**
     * 保存和修改
     * @param humanSkill
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(HumanSkill humanSkill, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userName=p.getId();
        String createDept = loginInfo.getDeptId();
        String num = peinfoapi.merge(humanSkill,userName,createDept);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 删除
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del-skill")
    @POST
    public StringData del_skill(List<HumanSkill> skills) {
        String num = peinfoapi.del_skill(skills);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 删除占用判断
     *
     * @return
     */
    @POST
    @Path("if-occupy")
    public StringData findOccupy(List<HumanSkill> parts) {
        String num = peinfoapi.findOccupy(parts);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null&&num =="yes") {
            stringData.setData("success");
        }
        return stringData;
    }

    /**
     * 人员技能下拉框带回
     *
     * @return
     */
    @Path("skill-downlist")
    @GET
    public List<HumanSkill> skillDownlist(@QueryParam("orgId")String orgId) {
        return peinfoapi.skillDownlist(orgId);
    }
    @Path("skill-downlist1")
    @GET
    public List<HumanSkill> skillDownlist1(@QueryParam("orgId")String orgId) {
        return peinfoapi.skillDownlist1(orgId);
    }
    @Path("level-downlist")
    @GET
    public List<HumanSkill> levelDownlist(@QueryParam("orgId")String orgId,@QueryParam("skillId")String skillId) {
        return peinfoapi.levelDownlist(orgId,skillId);
    }
    @Path("level-downlist1")
    @GET
    public List<HumanSkill> levelDownlist1(@QueryParam("orgId")String orgId,@QueryParam("skillId")String skillId) {
        return peinfoapi.levelDownlist1(orgId,skillId);
    }
    @GET
    @Path("level-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {
        HumanSkill humanSkill = new HumanSkill();
        humanSkill.setOrgId(orgId);
        Page<HumanSkill> page = peinfoapi.findList(
                new Page<HumanSkill>(request, response), humanSkill);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 新增重复判断
     *
     * @return
     */
    @GET
    @Path("if-level-exist")
    public List<HumanSkill> findSame(@QueryParam("orgId") String orgId,@QueryParam("level") String level,@QueryParam("levelId") String levelId,@QueryParam("skillId") String skillId) {
        return peinfoapi.findSame(orgId,level,levelId,skillId);
    }
    @POST
    @Path("level-merge")
    public StringData merge(HumanSkill humanSkill,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String personId=p.getId();
        String createDept = loginInfo.getDeptId();
        int num = 0;
        int count = 0;
        String id = humanSkill.getLevelId();
        if (id.equals("999")) {
            humanSkill.setLevel(humanSkill.getLevel());
            humanSkill.setSkillId(humanSkill.getSkillId());
            humanSkill.setOrgId(humanSkill.getOrgId());
            humanSkill.setCreateBy(personId);
            humanSkill.setCreateDept(createDept);
            count = count + Integer.parseInt(peinfoapi.insertPrimary(humanSkill));
        } else {
            humanSkill.setLevelId(id);
            humanSkill.setLevel(humanSkill.getLevel());
            humanSkill.setSkillId(humanSkill.getSkillId());
            humanSkill.setOrgId(humanSkill.getOrgId());
            humanSkill.setUpdateBy(personId);
            count = count + Integer.parseInt(peinfoapi.updatePrimary(humanSkill));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    @Path("level-del")
    @POST
    public StringData dataDel(HumanSkill humanSkill) {
        String dataId=humanSkill.getLevelId();
        String num = peinfoapi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    //工作经验保存
    @POST
    @Path("exp-merge")
    public StringData merge(MyInfoVo<MyInfo> myInfoVo,
                            @Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String orgId = loginInfo.getOrgId();
        String deptId=loginInfo.getDeptId();
        MyInfo myInfo = new MyInfo();
        String personId = myInfoVo.getPersonId();
            for (int i = 0; i < myInfoVo.getInserted().size(); i++) {
                myInfo = myInfoVo.getInserted().get(i);
                myInfo.setStartTime(myInfo.getStartTime());
                myInfo.setEndTime(myInfo.getEndTime());
                myInfo.setOrgId(myInfo.getOrgId());
                myInfo.setUnit(myInfo.getUnit());
                myInfo.setPost(myInfo.getPost());
                myInfo.setPersonId(myInfoVo.getPersonId());
                myInfo.setOrgId(orgId);
                myInfo.setCreateBy(userId);
                count = count + Integer.parseInt(peinfoapi.insertForeign(myInfo));
            }
        for (int i = 0; i < myInfoVo.getUpdated().size(); i++) {
            myInfo = myInfoVo.getUpdated().get(i);
            myInfo.setExpId(myInfo.getExpId());
            myInfo.setStartTime(myInfo.getStartTime());
            myInfo.setEndTime(myInfo.getEndTime());
            myInfo.setOrgId(myInfo.getOrgId());
            myInfo.setUnit(myInfo.getUnit());
            myInfo.setPost(myInfo.getPost());
            myInfo.setPersonId(myInfoVo.getPersonId());
            myInfo.setOrgId(orgId);
            myInfo.setCreateBy(userId);
            count = count + Integer.parseInt(peinfoapi.updateForeign(myInfo));
        }

        StringData stringData = new StringData();
        if (count == (myInfoVo.getInserted().size())) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }
    @Path("exp-del")
    @GET
    public StringData detailDel(@QueryParam("expId") String expId) {
        String num = peinfoapi.delForeign(expId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    @Path("workExp-list")
    @GET
    public List<MyInfo> workExplist(@QueryParam("orgId")String orgId,@QueryParam("personId")String personId) {
        return peinfoapi.workExplist(orgId,personId);
    }
    //社会关系保存
    @POST
    @Path("rel-merge")
    public StringData mergeRel(MyInfoVo1<MyInfo> myInfoVo1,
                            @Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String orgId = loginInfo.getOrgId();
        String deptId=loginInfo.getDeptId();
        MyInfo myInfo = new MyInfo();
        String personId = myInfoVo1.getPersonId();
        for (int i = 0; i < myInfoVo1.getInserted().size(); i++) {
            myInfo = myInfoVo1.getInserted().get(i);
            myInfo.setRelationship(myInfo.getRelationship());
            myInfo.setHealth(myInfo.getHealth());
            myInfo.setRelAge(myInfo.getRelAge());
            myInfo.setRelName(myInfo.getRelName());
            myInfo.setRelUnit(myInfo.getRelUnit());
            myInfo.setRelPost(myInfo.getRelPost());
            myInfo.setPersonId(myInfoVo1.getPersonId());
            myInfo.setOrgId(orgId);
            myInfo.setCreateBy(userId);
            count = count + Integer.parseInt(peinfoapi.insertForeign1(myInfo));
        }
        for (int i = 0; i < myInfoVo1.getUpdated().size(); i++) {
            myInfo = myInfoVo1.getUpdated().get(i);
            myInfo.setRelId(myInfo.getRelId());
            myInfo.setRelationship(myInfo.getRelationship());
            myInfo.setHealth(myInfo.getHealth());
            myInfo.setRelAge(myInfo.getRelAge());
            myInfo.setRelName(myInfo.getRelName());
            myInfo.setRelUnit(myInfo.getRelUnit());
            myInfo.setRelPost(myInfo.getRelPost());
            myInfo.setPersonId(myInfoVo1.getPersonId());
            myInfo.setOrgId(orgId);
            myInfo.setCreateBy(userId);
            count = count + Integer.parseInt(peinfoapi.updateForeign1(myInfo));
        }
        StringData stringData = new StringData();
        if (count == (myInfoVo1.getInserted().size())) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }
    @Path("rel-del")
    @GET
    public StringData detailDel1(@QueryParam("relId") String relId) {
        String num = peinfoapi.delForeign1(relId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    @Path("rel-list")
    @GET
    public List<MyInfo> rellist(@QueryParam("orgId")String orgId,@QueryParam("personId")String personId) {
        return peinfoapi.rellist(orgId,personId);
    }
}
