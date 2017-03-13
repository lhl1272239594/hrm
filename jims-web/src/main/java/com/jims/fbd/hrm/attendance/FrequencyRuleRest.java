package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.FrequencyRuleApi;
import com.jims.fbd.hrm.attendance.entity.FrequencyRule;
import com.jims.fbd.hrm.attendance.vo.FrequencyRuleVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yangchen on 2016/8/23 0.
 */
@Component
@Produces("application/json")
@Path("frequencyRule")
public class FrequencyRuleRest {
    @Reference(version = "1.0.0")
    private FrequencyRuleApi frequencyRuleApi;

    /**
     * 排班规则查询--无分页
     *
     * @return
     */
    @GET
    @Path("frequency-rule-all-list")
    public List<FrequencyRule> findFreRuleAllList(@QueryParam("orgId") String orgId){

        return frequencyRuleApi.findFreRuleAllList(orgId);

    }
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    @GET
    @Path("frequency-rule-data-all-list")
    public List<FrequencyRule> findFreRuleDataAllList(@QueryParam("orgId") String orgId,@QueryParam("freRuleId") String freRuleId){

        return frequencyRuleApi.findFreRuleDataAllList(orgId,freRuleId);

    }
    /**
     * 排班规则查询--分页
     *
     * @return
     */

    @GET
    @Path("frequency-rule-list")
    public PageData findFreRuleList(@QueryParam("orgId") String orgId,
                                    @QueryParam("freRuleDes") String freRuleDes,
                                    @Context HttpServletRequest request,
                                    @Context HttpServletResponse response){

        FrequencyRule frequencyRule = new FrequencyRule();
        frequencyRule.setOrgId(orgId);
        frequencyRule.setFreRuleDes(freRuleDes);

        Page<FrequencyRule> page= frequencyRuleApi.findFreRuleList(new Page<FrequencyRule>(request, response), frequencyRule);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    @GET
    @Path("frequency-data-list")
    public List<FrequencyRule> findFreRuleDataList(@QueryParam("orgId") String orgId,@QueryParam("freRuleId") String freRuleId){

        return frequencyRuleApi.findFreRuleDataList(orgId,freRuleId);

    }
    /**
     * 排班规则重复判断
     *
     * @return
     */

    @Path("frequency-rule-boolean")
    @GET
    public List<FrequencyRule>  findFreRuleBoolean(@QueryParam("orgId") String orgId,
                                           @QueryParam("freRuleDes") String freRuleDes,
                                                   @QueryParam("freRuleId") String freRuleId) {

        List<FrequencyRule> list= frequencyRuleApi.findFreRuleBoolean(orgId,freRuleDes,freRuleId);
        return list;
    }
    /**
     * 排班规则业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(FrequencyRuleVo<FrequencyRule> frequencyRuleVo,@Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String freRuleId =frequencyRuleVo.getFreRuleId();
        FrequencyRule frequencyRule = new FrequencyRule();

        if ( freRuleId.equals("999")) {
            //保存班次规律头信息
            freRuleId=IdGen.uuid();
            frequencyRule.setFreRuleId(freRuleId);
            frequencyRule.setFreRuleDes(frequencyRuleVo.getFreRuleDes());
            frequencyRule.setFreLoopDays(frequencyRuleVo.getFreLoopDays());
            frequencyRule.setOrgId(frequencyRuleVo.getOrgId());
            frequencyRule.setCreateBy(userId);
            count = count + Integer.parseInt(frequencyRuleApi.insertPrimary(frequencyRule));

            //保存节日设置行信息
            for (int i = 0; i < frequencyRuleVo.getInserted().size(); i++) {

                frequencyRule = (FrequencyRule) frequencyRuleVo.getInserted().get(i);
                frequencyRule.setFreRuleId(freRuleId);
                frequencyRule.setFreRuleDataId(IdGen.uuid());
                frequencyRule.setFreItemId(frequencyRule.getFreItemId());
                frequencyRule.setCreateBy(userId);
                frequencyRule.setOrgId(frequencyRuleVo.getOrgId());
                count = count + Integer.parseInt(frequencyRuleApi.insertForeign(frequencyRule));
            }


        } else {

            frequencyRule.setFreRuleId(freRuleId);
            frequencyRule.setFreRuleDes(frequencyRuleVo.getFreRuleDes());
            frequencyRule.setFreLoopDays(frequencyRuleVo.getFreLoopDays());
            frequencyRule.setUpdateBy(userId);
            count = count + Integer.parseInt(frequencyRuleApi.updatePrimary(frequencyRule));

            for (int i = 0; i < frequencyRuleVo.getInserted().size(); i++) {
                frequencyRule = (FrequencyRule) frequencyRuleVo.getInserted().get(i);
                frequencyRule.setFreRuleId(freRuleId);
                frequencyRule.setFreRuleDataId(IdGen.uuid());
                frequencyRule.setFreItemOrder(frequencyRule.getFreItemOrder());
                frequencyRule.setFreItemId(frequencyRule.getFreItemId());
                frequencyRule.setOrgId(frequencyRuleVo.getOrgId());
                frequencyRule.setCreateBy(userId);
                count = count + Integer.parseInt(frequencyRuleApi.insertForeign(frequencyRule));
            }

            for (int i = 0; i < frequencyRuleVo.getUpdated().size(); i++) {
                frequencyRule = (FrequencyRule) frequencyRuleVo.getUpdated().get(i);
                frequencyRule.setFreRuleId(freRuleId);
                frequencyRule.setFreRuleDataId(frequencyRule.getFreRuleDataId());
                frequencyRule.setFreItemDes(frequencyRule.getFreItemDes());
                frequencyRule.setFreItemOrder(frequencyRule.getFreItemOrder());
                frequencyRule.setOrgId(frequencyRuleVo.getOrgId());
                frequencyRule.setUpdateBy(userId);
                count = count + Integer.parseInt(frequencyRuleApi.updateForeign(frequencyRule));
            }


        }


        StringData stringData = new StringData();
        if (count == (frequencyRuleVo.getInserted().size() + frequencyRuleVo.getUpdated().size())) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }

    /**
     * 删除占用判断
     *
     * @return
     */
    @POST
    @Path("if-occupy")
    public StringData findOccupy(@QueryParam("freRuleId") String freRuleId,@QueryParam("orgId") String orgId ) {
        FrequencyRule frequencyRule = new FrequencyRule();
        frequencyRule.setOrgId(orgId);
        frequencyRule.setFreRuleId(freRuleId);
        String num = frequencyRuleApi.findOccupy(frequencyRule);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null&&num =="yes") {
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 排班规则业务处理：主表信息删除
     *
     * @return
     */
    @Path("frequency-rule-del")
    @POST
    public StringData frequencyRuleDel(FrequencyRuleVo<FrequencyRule> frequencyRuleVo,@Context HttpServletRequest request) {
        String freRuleId =frequencyRuleVo.getFreRuleId();
        String flag =frequencyRuleVo.getFlag();
        String num="";
        num = frequencyRuleApi.delPrimary(freRuleId, flag);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 排班规则业务处理：从表信息删除
     *
     * @return
     */
    @Path("frequency-rule-data-del")
    @POST
    public StringData frequencyRuleDataDel(FrequencyRuleVo<FrequencyRule> frequencyRuleVo,@Context HttpServletRequest request) {

        FrequencyRule frequencyRule = new FrequencyRule();
        String freRuleDataId =frequencyRuleVo.getFreRuleDataId();
        String freRuleId =frequencyRuleVo.getFreRuleId();
        String freLoopDays =frequencyRuleVo.getFreLoopDays();

        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();

        frequencyRule.setUpdateBy(userId);
        String flag =frequencyRuleVo.getFlag();

        String num1="";
        String num2="";

        num1 = frequencyRuleApi.delForeign(freRuleDataId,flag);
        num2 =frequencyRuleApi.updateLoopDays(freRuleId,freLoopDays);

        StringData stringData = new StringData();
        stringData.setCode(num1);
        stringData.setData("success");
        return stringData;
    }
}
