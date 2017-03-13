package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.sys.api.SysServiceApi;
import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 系统服务rest层
 *
 * @author txb
 * @version 2016-05-31
 */
@Component
@Produces("application/json")
@Path("sys-service")
public class SysServiceRest {
    @Reference(version = "1.0.0")
    private SysServiceApi sysServiceApi;

    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Path("service-list-by-TC")
    @GET
    public List<SysService> serviceListByTC(@QueryParam("serviceType") String serviceType,@QueryParam("serviceClass") String serviceClass) {
        return sysServiceApi.serviceListByTC(serviceType, serviceClass);
    }
    /**
     * 查询服务明细全部列表
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-01
     */
    @Path("detail-list")
    @GET
    public List<SysServicePrice> detailList(@QueryParam("serviceId") String serviceId) {
        return sysServiceApi.findDetailList(serviceId);
    }
    /**
     * 查询服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Path("service-vs-menu-list")
    @GET
    public List<ServiceVsMenu> serviceVsMenuList(@QueryParam("serviceId") String serviceId) {
        return sysServiceApi.serviceVsMenuList(serviceId);
    }
    /**
     * 查询服务全部列表
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Path("list")
    @GET
    public List<SysService> list() {
        List<SysService> list=sysServiceApi.findList();
        if(list!=null&&!list.isEmpty()){
            for(SysService sysService:list){
                try {
                    if(sysService.getServiceDescription()!=null)
                    sysService.setTranServiceDescription(new String(sysService.getServiceDescription(),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    /**
     * 修改保存服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    @Path("save-detail")
    @POST
    public StringData saveDetail(StaffGroupVo<SysServicePrice> priceBeanVo) {
        String num = sysServiceApi.saveDetail(priceBeanVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 修改保存服务菜单
     * @param menuVsServices
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Path("save-serviceVsMenu")
    @POST
    public StringData saveServiceVsMenu(List<ServiceVsMenu> menuVsServices) {
        String num = sysServiceApi.saveServiceVsMenu(menuVsServices);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 修改保存服务
     * @param form
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Path("save")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public StringData save( FormDataMultiPart form,@QueryParam("serviceDescription") String serviceDescription) {
        //获取文件流
        FormDataBodyPart filePart = form.getField("serviceImage");
        //获取表单的其他数据


        //ContentDisposition headerOfFilePart = filePart.getContentDisposition();
        //把表单内容转换成流
        InputStream fileInputStream = filePart.getValueAs(InputStream.class);

        FormDataContentDisposition formDataContentDisposition = filePart.getFormDataContentDisposition();

        String source = formDataContentDisposition.getFileName();
        String result = null;
        try {
            result = new String(source.getBytes("ISO8859-1"), "UTF-8");
            if (result.length() >= 3){//0.0
                result = generateShortUuid()  +  result.substring(result.lastIndexOf("."));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        System.out.println("formDataContentDisposition.getFileName()result " + result);
        //拼接路径
        String savePath =  SysServiceRest.class.getResource("/").getFile();
        int index = savePath.indexOf("target");
        savePath = savePath.substring(0,index);
        String path = "src/main/webapp/static/images/sysService/";

        String filePath = savePath + path + result;
//        String filePath = "static/images/sysService/" + result;
        File file = new File(filePath);
        System.out.println("file " + file.getAbsolutePath());
        try {
            //保存文件
            FileUtils.copyInputStreamToFile(fileInputStream, file);
        } catch (IOException ex) {
        }
        SysService sysService = new SysService();
        if (form.getField("id") != null){
            sysService.setId(form.getField("id").getValue());
        }
        sysService.setServiceClass(form.getField("serviceClass").getValue());
        byte[] bytes=null;
        try {
            bytes=serviceDescription.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sysService.setServiceDescription(bytes);
        sysService.setServiceType(form.getField("serviceType").getValue());
        sysService.setServiceName(form.getField("serviceName").getValue());
        if (result.length() >= 3){
            sysService.setServiceImage("/static/images/sysService/" + result);
        }


        String num = sysServiceApi.save(sysService);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    private FileItem getUploadFileItem(List<FileItem> list) {
        for (FileItem fileItem : list) {
            if (!fileItem.isFormField()) {
                return fileItem;
            }
        }
        return null;
    }

    private String getUploadFileName(FileItem item) {
        // 获取路径名
        String value = item.getName();
        // 索引到最后一个反斜杠
        int start = value.lastIndexOf("/");
        // 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
        String filename = value.substring(start + 1);

        return filename;
    }

    /**
     * 删除服务信息
     *
     * @param ids
     * @author txb
     * @version 2016-05-31
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = sysServiceApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    //生成8位随机uuid
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
     * 检索不同人群的服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
     * @param id  sys_service.id
     * @return
     */
    @Path("findServiceWithPrice")
    @GET
    public List<SysService> findServiceWithPrice(@QueryParam("serviceClass")String serviceClass,@QueryParam("serviceType")String serviceType ,
                                                 @QueryParam("persionId")String persionId ,@QueryParam("state")String state ,@QueryParam("id")String id){
        if("0".equals(serviceClass))
            serviceType = "1";
        List<SysService> list=sysServiceApi.findServiceWithPrice(serviceClass,serviceType, persionId, state,id);
        if(list!=null&&!list.isEmpty()){
            for(SysService sysService:list){
                try {
                    if(sysService.getServiceDescription()!=null)
                        sysService.setTranServiceDescription(new String(sysService.getServiceDescription(),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 根据id查询服务
     * @param id 服务ID
     * @return 服务
     * @author fengyuguang
     */
    @GET
    @Path("get")
    public SysService get(@QueryParam("id")String id){
        return sysServiceApi.get(id);
    }

}
