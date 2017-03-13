package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.InputSettingServiceApi;
import com.jims.sys.api.StaffGroupApi;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputSettingVo;
import com.jims.sys.vo.StaffGroupVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangruidong on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("staff-group")
public class StaffGroupRest {
    @Reference(version = "1.0.0")
    private StaffGroupApi staffGroupApi;

    /**
     * 根据组织机构id查询工作组类
     *
     * @param orgId
     * @return
     */
    @Path("findAllListByOrgId")
    @GET
    public List<StaffGroupClassDict> findAllListByOrgId(@QueryParam("orgId") String orgId,@QueryParam("q")String q) {
        return staffGroupApi.findAllListByOrgId(orgId,q);
    }



    /**
     * 保存  增删改
     *
     * @param staffGroupClassDictVo
     * @return
     * @author yangruidong
     */
    @Path("saveGroupClass")
    @POST
    public StringData saveGroupClass(StaffGroupVo<StaffGroupClassDict> staffGroupClassDictVo) {
        List<StaffGroupClassDict> newUpdateDict = new ArrayList<StaffGroupClassDict>();
        newUpdateDict = staffGroupApi.saveGroupClass(staffGroupClassDictVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 根据工作组类的id查询工作组的信息
     *
     * @param id
     * @return
     * @author yangruidong
     */
    @Path("findListGroupDict")
    @GET
    public List<StaffGroupDict> findListGroupDict(@QueryParam("id") String id,@QueryParam("q")String q) {
        return staffGroupApi.findListGroupDict(id,q);
    }

    /**
     * 保存  增删改      工作组
     *
     * @param staffGroupDictlVo
     * @return
     * @author yangruidong
     */
    @Path("saveGroup")
    @POST
    public StringData saveGroup(StaffGroupVo<StaffGroupDict> staffGroupDictlVo) {
        List<StaffGroupDict> newUpdateDict = new ArrayList<StaffGroupDict>();
        newUpdateDict = staffGroupApi.saveGroup(staffGroupDictlVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }


}
