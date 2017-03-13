package com.jims.fbd.hrm.tool;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.fbd.hrm.tool.api.ToolApi;
import com.jims.fbd.hrm.tool.entity.Tool;
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
import java.util.Date;
import java.util.UUID;
/**
 * Created by yangchen on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("upload")
public class UploadRest {

    @Reference(version = "1.0.0")
    private ToolApi toolApi;

    @Path("upload-file")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public StringData upLoad(FormDataMultiPart form,@QueryParam("fileType") String fileType,
                             @Context HttpServletRequest request, @Context HttpServletResponse response)
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

        String realSaveUrl = saveUrl+ "/" + orgName+"/"+fileType+"/"+ym+"/";
        String realSavePath = savePath+ "/" + orgName+"/"+fileType+"/"+ym+"/";
        //获取文件流
        FormDataBodyPart filePart = form.getField("myFiles");

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
        if(fileType.equals("KaoQinShuJu")){
            resultName= generateShortUuid()+ "_"+new String(filename.getBytes("ISO8859-1"), "UTF-8");

        }else{
            resultName=new String(filename.getBytes("ISO8859-1"), "UTF-8");

        }
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
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += java.lang.Character.toString(t1[i]);
            }
            // System.out.println(t4);
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
}
