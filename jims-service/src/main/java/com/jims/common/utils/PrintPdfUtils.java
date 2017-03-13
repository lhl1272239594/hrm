package com.jims.common.utils;


import com.jims.test.entity.JavaBeanPerson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打印调用
 */

public class PrintPdfUtils{

    /**
     *
     * @param userId 用户Id
     * @param jasperName ireport 文件名
     * @param paramMap 打印Page Header 内容
     * @param lists 打印 detail 内容
     * @param defaultFilename 生成文件的名称
     * @return
     */
    public static String previewPdf(String userId,String jasperName,Map paramMap,
                                    List lists, String defaultFilename) {
        HashMap<String, Object> params= new HashMap<String, Object>();
        RestUtils<Map,HashMap> restUtils =new RestUtils<Map ,HashMap>();
        params.put("userId",userId);
        params.put("type","pdf");
        params.put("jasperName",jasperName);
        params.put("isPrint",true);
        params.put("paramMap",paramMap);
        params.put("lists",lists);
        params.put("defaultFilename",defaultFilename);
        Map<String ,Object> map=restUtils.findById("/ireport/view", Map.class, params,params,"");
        HashMap retMap=(HashMap)map.get("body");
        return (String)retMap.get("data");
    }

    public static void main(String[] args) throws Exception{
        Map map=new HashMap();
        map.put("test","test");
        String path=previewPdf("123456", "MvcIReportChsExample.jasper", map, JavaBeanPerson.getListChs(),"ee");
        System.out.print(path);
    }
}
