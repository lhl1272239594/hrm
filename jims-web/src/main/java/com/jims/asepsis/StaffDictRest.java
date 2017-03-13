package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.asepsis.api.StaffDictServiceApi;
import com.jims.asepsis.entity.StaffDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Auser on 2016/6/27.
 * Created by louhuili
 * 处方医嘱明细记录
 */
@Component
//@Produces("application/json")
@Produces(MediaType.APPLICATION_JSON)
@Path("staffDict")
public class StaffDictRest {

    @Reference(version = "1.0.0")
    StaffDictServiceApi staffDictServiceApi;//公用字典API



    /**
     * @Description: (根据消毒包状态获取某状态下的消毒包)
     * @author LHL
     * @date 2016/4/25
     */
    @GET
    @Path("listStaffDict")
    public List<StaffDict> listStaffDict(){
        List<StaffDict> list = Lists.newArrayList();
        try {  list = staffDictServiceApi.listStaffDict();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }













}

