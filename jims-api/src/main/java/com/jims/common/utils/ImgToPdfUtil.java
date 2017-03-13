package com.jims.common.utils;

import com.jims.common.vo.LoginInfo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImgToPdfUtil {

    public static String imgToPdf(String imagePath,String userId,HttpServletRequest request) {
        String webPath="pdfPath";
        try {
            String generateFilePath = getGenerateFilePath(request,userId, "pdfP","pdf");       //pdf生成路径(添加页眉页脚后)
               //临时文件(从url导出，未添加页眉页脚)
            String folderPath = generateFilePath.substring(0,generateFilePath.lastIndexOf("\\")+1);
            String imageFolderPath = imagePath.substring(0,imagePath.lastIndexOf("\\")+1);
            webPath=generateFilePath.substring(generateFilePath.indexOf("pdfP"),generateFilePath.length());
            if("".equals(request.getContextPath())){
                webPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+webPath;
            }else{
                webPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/"+webPath;
            }
            webPath=webPath.replace("\\", "/");
            //生成文件
            File generateFile= new File(folderPath);
            deleteFile(generateFile);
            BufferedImage img = ImageIO.read(new File(imagePath));
            FileOutputStream fos = new FileOutputStream(generateFilePath);
            Document doc = new Document(null, 0, 0, 0, 0);
            doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
            Image image = Image.getInstance(imagePath);
            PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.add(image);
            doc.close();
            File imageFolderPathFile= new File(imageFolderPath);
            deleteFile(imageFolderPathFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return webPath;
    }

    /**
     * 获取生成的打印文件(pdf)路径
     *
     * @param zhixingId
     * @return
     */
    public static String getGenerateFilePath(HttpServletRequest request,String userId, String zhixingId,String type) {
        String printBasePath = getPrintBasePath(request);                              //获取打印文件根路径
        // String printBasePath = request.getSession().getServletContext().getRealPath("/") + "static" + File.separator + "emr"+  File.separator+"print";
        File baseDir = new File(printBasePath);
        if (!baseDir.exists()) {
            baseDir.mkdir();
        }

        // 根据userId创建目录
        String userPrintPath = printBasePath +userId;
        File userDir = new File(userPrintPath);
        if (!userDir.exists()) {
            userDir.mkdir();
        }

        // 根据zhixingId创建目录
        String patientPrintPath = userPrintPath + File.separator + zhixingId;
        File patientDir = new File(patientPrintPath);
        if (!patientDir.exists()) {
            patientDir.mkdir();
        }
        String fileName ="";
        if("img".equals(type)){
            fileName=UUID.randomUUID()+ ".jpg";    //文件名;
        }else{
            fileName=UUID.randomUUID()+ ".pdf";    //文件名;
        }
        String generateFilePath = patientPrintPath + File.separator + fileName;     //最终生成打印文件路径
        return generateFilePath;
    }
    /**
     * 获取打印文件存放根路径
     *
     * @return
     */
    public static String getPrintBasePath(HttpServletRequest request) {;
        //String path =request.getSession().getServletContext().getRealPath("/")+File.separator+"pdfPath"+File.separator;
        String path ="D://pdfPath"+File.separator;
        return path;
    }
    /**
     * 删除文件
     * @param oldPath
     */
    public static void deleteFile(File oldPath) {
        if (oldPath.isDirectory()) {
            System.out.println(oldPath + "是文件夹--");
            File[] files = oldPath.listFiles();
            for (File file : files) {
                deleteFile(file);
            }
        }else{
            oldPath.delete();
        }
    }
    public static void main(String args[]) {
       // imgToPdf("D:\\twd.jpg","D:\\test1.pdf");
    }


}
