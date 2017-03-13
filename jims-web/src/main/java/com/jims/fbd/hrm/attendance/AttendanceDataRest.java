package com.jims.fbd.hrm.attendance;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.AttendanceDataApi;
import com.jims.fbd.hrm.attendance.entity.AttendanceData;
import com.jims.fbd.hrm.attendance.vo.AttendanceDataVo;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangchen on 2016/8/23 0.
 */
@Component
@Produces("application/json")
@Path("attendanceData")
public class AttendanceDataRest {
    @Reference(version = "1.0.0")
    private AttendanceDataApi attendanceDataApi;

    /**
     * 打卡数据查询
     *
     * @return
     */
    @GET
    @Path("attendanceData-list")
    public PageData findAttList(@QueryParam("orgId") String orgId,
                                @QueryParam("userName") String userName,
                                @Context HttpServletRequest request,
                                @Context HttpServletResponse response){


        AttendanceData attendanceData = new AttendanceData();
        attendanceData.setOrgId(orgId);
        attendanceData.setUserName(userName);

        Page<AttendanceData> page= attendanceDataApi.findAttList( new Page<AttendanceData>(request, response), attendanceData);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     *考勤综合查询--日数据
     *
     * @return
     */
    @GET
    @Path("attendance-day-report")
    public PageData findDayReport(@QueryParam("orgId") String orgId,
                                @QueryParam("userId") String userId,
                                @Context HttpServletRequest request,
                                @Context HttpServletResponse response){

        AttendanceData attendanceData = new AttendanceData();
        attendanceData.setCreateOrg(orgId);
        attendanceData.setUserId(userId);

        Page<AttendanceData> page= attendanceDataApi.findDayReport( new Page<AttendanceData>(request, response), attendanceData);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }

    /**
     *考勤综合查询--日数据-汇总
     *
     * @return
     */
    @GET
    @Path("attendance-day-report-all")
    public List<AttendanceData> findDayReportAll(@QueryParam("orgId") String orgId,
                                  @QueryParam("userId") String userId,
                                  @Context HttpServletRequest request,
                                  @Context HttpServletResponse response){

        AttendanceData attendanceData = new AttendanceData();
        attendanceData.setCreateOrg(orgId);
        attendanceData.setUserId(userId);

        List<AttendanceData> list = attendanceDataApi.findDayReportAll(attendanceData);
        return list;

    }
    /**
     * 打卡数据综合查询-月汇总
     *
     * @return
     */
    @GET
    @Path("attendance-month-report")
    public PageData findMonthReport(@QueryParam("orgId") String orgId,
                                 @QueryParam("userName") String userName,
                                    @QueryParam("attMonth") String attMonth,
                                 @Context HttpServletRequest request,
                                 @Context HttpServletResponse response){

        AttendanceData attendanceData = new AttendanceData();
        attendanceData.setCreateOrg(orgId);
        attendanceData.setUserName(userName);
        attendanceData.setAttMonth(attMonth);
        Page<AttendanceData> page= attendanceDataApi.findMonthReport( new Page<AttendanceData>(request, response), attendanceData);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 打卡记录导入
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(AttendanceDataVo<AttendanceData> attendanceDataVo,
                            @Context HttpServletRequest request, @Context HttpServletResponse response){
        HttpSession session = request.getSession();
        StringData stringData = new StringData();
        DateFormat fmtDate=new SimpleDateFormat("YYYY-MM-DD");//
        DateFormat fmtTime=new SimpleDateFormat("HH:mm:ss");//

        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String deptId=loginInfo.getDeptId();
        String orgId=attendanceDataVo.getOrgId();
        int num = 0;
        int count = 0;
        String id = attendanceDataVo.getAttDataId();
        //获得电子表格数据
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        String realUrl=attendanceDataVo.getFileUrl();
        String url=basePath+realUrl;
        String saveDirectory = session.getServletContext().getRealPath(realUrl);
        //System.out.println(saveDirectory);
        Date check_date=null;
        int tempNum=0;
        AttendanceData attDate = new AttendanceData();

        List<AttendanceData> listExcel=getAllByExcel(url);
        if(listExcel==null||listExcel.size()==0)
        {
            tempNum=1;
            stringData.setCode("2");
            stringData.setData("上传了空模版或者错误模版！");
        }
        else {
            //校验导入的数据是否重复
            for (int i = 0; i < listExcel.size() - 1; i++)
            {
                String temp1=listExcel.get(i).getUserCode();
                String temp2=listExcel.get(i).getDeptCode();
                String temp3=listExcel.get(i).getDeptName();
                String temp4=listExcel.get(i).getCheckDate();
                String temp5=listExcel.get(i).getCheckInTime();
                String temp6=listExcel.get(i).getCheckOutTime();
                String temp7=listExcel.get(i).getUserName();
                //校验是否为空
                if(temp1=="")
                {
                    tempNum=1;
                    stringData.setCode("2");
                    stringData.setData("第：<" +(i+1)+ ">行未输入员工编号，请重新检查");
                    break;
                }
                if(temp2=="")
                {
                    tempNum=1;
                    stringData.setCode("2");
                    stringData.setData("第：<" +(i+1)+ ">行未输入工科室编号，请重新检查");
                    break;
                }
                if(temp3=="")
                {
                    tempNum=1;
                    stringData.setCode("2");
                    stringData.setData("第：<" +(i+1)+ ">行未输入科室名称，请重新检查");
                    break;
                }
                if(temp4=="")
                {
                    tempNum=1;
                    stringData.setCode("2");
                    stringData.setData("第：<" +(i+1)+ ">行未输入打卡日期，请重新检查");
                    break;
                }
                if(temp5=="")
                {
                    tempNum=1;
                    stringData.setCode("2");
                    stringData.setData("第：<" +(i+1)+ ">行未输入上班打卡时间，请重新检查");
                    break;
                }
                if(temp6=="")
                {
                    tempNum=1;
                    stringData.setCode("2");
                    stringData.setData("第：<" +(i+1)+ ">行未输下班打卡时间，请重新检查");
                    break;
                }
                if(temp7=="")
                {
                    tempNum=1;
                    stringData.setCode("2");
                    stringData.setData("第：<" +(i+1)+ ">行未输员工姓名，请重新检查");
                    break;
                }
                //校验数据是否重复
                for (int j = i + 1; j < listExcel.size(); j++)
                {
                    String temp21=listExcel.get(j).getUserCode();
                    String temp22=listExcel.get(j).getDeptCode();
                    String temp23=listExcel.get(j).getDeptName();
                    String temp24=listExcel.get(j).getCheckDate();
                    String temp25=listExcel.get(j).getCheckInTime();
                    String temp26=listExcel.get(j).getCheckOutTime();
                    String temp27=listExcel.get(j).getRowId();

                    if (temp1.equals(temp21)&&
                        temp2.equals(temp22)&&
                        temp3.equals(temp23)&&
                        temp4.equals(temp24)&&
                        temp5.equals(temp25)&&
                        temp6.equals(temp26))
                    {
                        tempNum=1;
                        stringData.setCode("2");
                        stringData.setData("第" + (i + 1) + "行跟第" + (j + 1) + "行重复,请重新检查！");
                        break;
                    }

                    //校验日期格式是否正确

                    try {
                        fmtDate.parse(temp4);
                    } catch (ParseException e) {
                        tempNum=1;
                        stringData.setCode("2");
                        stringData.setData("第：<" +(i+1)+ "> 行的打卡日期不是模版格式！");
                        break;
                    }
                    try {
                        fmtTime.parse(temp5);
                    } catch (ParseException e) {
                        tempNum=1;

                        stringData.setCode("2");
                        stringData.setData("第：<" +(i+1)+ "> 行的上班打卡时间不是模版格式！");
                        break;
                    }
                    try {
                        fmtTime.parse(temp6);
                    } catch (ParseException e) {
                        tempNum=1;
                        stringData.setCode("2");
                        stringData.setData("第：<" +(i+1)+ "> 行的下班打卡时间不是模版格式！");
                        break;
                    }
                    //判断与已经导入数据库的数据是否重复

                    attDate.setOrgId(orgId);
                    attDate.setUserCode(temp1);
                    attDate.setDeptCode(temp2);
                    attDate.setCheckDate(temp4);
                    String  num1= attendanceDataApi.findOccupy(attDate);
                    if ( num1.equals("yes")) {
                        tempNum=1;
                        stringData.setCode("2");
                        stringData.setData("第：<" +(i+1)+ "> 行的打卡数据已导入，请重新检查！");
                        break;
                    }
                }


            }
            if(tempNum==0){
            for (AttendanceData attendanceData : listExcel) {
                try {
                    Thread.sleep(100);
                    attendanceData.setAttDataId(IdGen.uuid());
                    attendanceData.setCreateBy(userId);
                    attendanceData.setCreateDept(deptId);
                    attendanceData.setCreateOrg(orgId);
                    count = count + Integer.parseInt(attendanceDataApi.insertPrimary(attendanceData));
                } catch (InterruptedException e) {
                    stringData.setCode("2");
                    stringData.setData("您导入的考勤数据格式错误！请参考表格内模版数据修改！");

                }
             }
            }
        }
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    /**
     *读取电子表格模版
     *
     * @return
     */
    public static List<AttendanceData> getAllByExcel(String url){
        List<AttendanceData> list=new ArrayList<AttendanceData>();
        try {

              String dir = "c:/temp";
              URL httpUrl = new URL(url);
            System.out.println(httpUrl);

            InputStream in = httpUrl.openStream();
            Workbook attDate=Workbook.getWorkbook(in);
            Sheet sheet=attDate.getSheet(0);//获得工作簿
            int columns=sheet.getColumns();//得到所有的列
            int rows=sheet.getRows();//得到所有的行

            System.out.println(columns+" rows:"+rows);
            for (int i = 2; i < rows; i++) { //从第二行开始
                for (int j = 0; j < 4; j++) {
                    //第一个是列数，第二个是行数
                    String row_id =sheet.getCell(j++, i).getContents();//
                    String user_code =sheet.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String user_name =sheet.getCell(j++, i).getContents();//
                    String dept_code =sheet.getCell(j++, i).getContents();//
                    String dept_name =sheet.getCell(j++, i).getContents();//
                    String check_date =sheet.getCell(j++, i).getContents();//
                    String check_in_time =sheet.getCell(j++, i).getContents();//
                    String check_out_time =sheet.getCell(j++, i).getContents();//


                   //System.out.println("用户编号:"+user_code+"用户姓名:"+user_code+"部门编号:"+dept_code+"部门名称:"+dept_name+"考勤日期:"+check_date+" 打卡时间:"+check_time);
                    if(user_code.trim().length()==0 &&
                            user_name.trim().length()==0 &&
                            row_id.trim().length()==0 &&
                            dept_code.trim().length()==0 &&
                            dept_name.trim().length()==0 &&
                            check_in_time.trim().length()==0 &&
                            check_out_time.trim().length()==0 &&
                            check_date.trim().length()==0)
                    {
                        break;
                    }
                    AttendanceData attendanceData=new AttendanceData();
                    attendanceData.setUserCode(user_code);
                    attendanceData.setUserName(user_name);
                    attendanceData.setDeptCode(dept_code);
                    attendanceData.setDeptName(dept_name);
                    attendanceData.setRowId(row_id);
                    attendanceData.setCheckDate(check_date);
                    attendanceData.setCheckInTime(check_in_time);
                    attendanceData.setCheckOutTime(check_out_time);
                    list.add(attendanceData);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            list=null;
        }
        return list;

    }

    /**
     *打卡记录删除
     *
     * @return
     */
    @Path("del")
    @POST
    public StringData del(List<AttendanceData> attendanceData) {
        String num = attendanceDataApi.del(attendanceData);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
