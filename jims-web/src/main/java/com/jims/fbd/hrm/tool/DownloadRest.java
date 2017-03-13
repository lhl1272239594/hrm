package com.jims.fbd.hrm.tool;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.fbd.hrm.tool.api.ToolApi;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * Created by yangchen on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("download")
public class DownloadRest {

    @Reference(version = "1.0.0")
    private ToolApi toolApi;

    @Path("download-file")
    @POST
    public StringData download(@QueryParam("fileName") String fileName,
                               @QueryParam("path") String path,
                             @Context HttpServletRequest request, @Context HttpServletResponse response)
            throws ServletException, IOException {
        StringData stringData = new StringData();

        request.setCharacterEncoding("utf-8");
        String serverPath=request.getRealPath("\\")+path;

        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
        ServletOutputStream out;
        //通过文件路径获得File对象
        File file = new File(serverPath+fileName);

        try {
            FileInputStream in = new FileInputStream(file);

            //3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while((len=in.read(buffer))>0){

                //4.写到输出流(out)中
                out.write(buffer,0,len);
            }
            in.close();
            out.close();
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringData;

    }

}
