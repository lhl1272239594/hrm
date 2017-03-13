package com.jims.test.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.utils.PrintPdfUtils;
import com.jims.test.api.PrintTestApi;
import com.jims.test.entity.JavaBeanPerson;

import java.util.HashMap;
import java.util.Map;


@Service(version = "1.0.0")
public class PrintPdfServiceImpl implements PrintTestApi {


    @Override
    public String print() {
        Map map=new HashMap();
        map.put("test","test");
       return PrintPdfUtils.previewPdf("zhangsan", "MvcIReportChsExample.jasper", map, JavaBeanPerson.getListChs(), "ee");
    }
}