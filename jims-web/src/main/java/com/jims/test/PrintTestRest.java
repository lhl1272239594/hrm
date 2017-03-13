package com.jims.test;

import com.alibaba.dubbo.config.annotation.Reference;


import com.jims.common.data.StringData;
import com.jims.test.api.PrintTestApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("printPdf")
public class PrintTestRest {

    @Reference(version = "1.0.0")
    private PrintTestApi printTestApi;

    @GET
    @Path("pdf")
    public StringData pdf() {
        StringData stringData=new StringData();
        stringData.setCode("1");
        stringData.setData(printTestApi.print());
        return  stringData;
    }

}