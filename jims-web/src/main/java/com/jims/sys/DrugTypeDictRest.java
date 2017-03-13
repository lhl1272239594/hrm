package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.api.DrugTypeDictApi;
import com.jims.sys.entity.DrugTypeDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by zhu on 2016/5/14.
 */
@Component
@Produces("application/json")
@Path("drug-type-dict")
public class DrugTypeDictRest {
    @Reference(version = "1.0.0")
    private DrugTypeDictApi drugTypeDictApi;

    @Path("list")
    @GET
    public List<DrugTypeDict> list() {
        List<DrugTypeDict> list = drugTypeDictApi.findAllList();
        return list;
    }
}
