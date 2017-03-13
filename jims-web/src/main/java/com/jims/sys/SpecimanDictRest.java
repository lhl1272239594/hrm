package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.api.SpecimanDictServiceApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.SpecimanDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by xueyx on 2016/5/5 0024.
 */
@Component
@Produces("application/json")
@Path("speciman")
public class SpecimanDictRest {

    @Reference(version = "1.0.0")
    private SpecimanDictServiceApi specimanDictServiceApi;
    /**
     * 查询科室代码下的检验标本
     * @param检验科室编码 code
     * @return
     */
    @Path("findListByDeptCode")
    @POST
    public List<SpecimanDict> findListByDeptCode(String code) {
        /*int index = code.indexOf("=");
        code =code.substring(index+1);*/
        List<SpecimanDict> list = specimanDictServiceApi.findListByDeptCode(code);
        return list;
    }

}
