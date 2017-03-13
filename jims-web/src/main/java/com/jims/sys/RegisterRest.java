package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.RegisterVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
@Component
@Produces("application/json")
@Path("register")
public class RegisterRest {

    @Reference(version = "1.0.0")
    private PersionInfoApi persionInfoApi;

    /**
     * 用户注册
     *
     * @param registerVo
     * @return
     */
    @POST
    @Path("add-info")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData register(RegisterVo registerVo) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setName(registerVo.getName());
        persionInfo.setCardNo(registerVo.getCardNo());
        persionInfo.setPhoneNum(registerVo.getPhoneNum());
        persionInfo.setNickName(registerVo.getNickName());
        persionInfo.setEmail(registerVo.getEmail());
        persionInfo.setCreateDate(new Date());
        persionInfo.setInputCode(registerVo.getInputCode());

        SysUser sysUser = new SysUser();
        sysUser.setPassword(registerVo.getPassword());
        String persion=persionInfoApi.register(persionInfo, sysUser);
        StringData stringData=new StringData();
        if(persion!=null)
        {
            stringData.setData("success");
            return stringData;
        }
        return null;
    }


    /**
     * 校验身份证号是否存在
     *
     * @param
     * @return
     */
    @GET
    @Path("getCard")
    public PersionInfo selectCard(@QueryParam("cardNo") String cardNo,@QueryParam("persionId") String persionId) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setCardNo(cardNo);
        persionInfo.setId(persionId);
        return persionInfoApi.getCard(persionInfo);
    }

    /**
     * 检验用户名是否存在
     * @param
     * @return
     */

    @GET
    @Path("getNick")
    public PersionInfo selectNick(@QueryParam("nickName") String nickName,@QueryParam("persionId") String persionId) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setNickName(nickName);
        persionInfo.setId(persionId);
        return persionInfoApi.getNick(persionInfo);
    }

    /**
     * 检验邮箱是否存在
     * @param
     * @return
     */
    @GET
    @Path("getEmail")
    public PersionInfo selectEmail(@QueryParam("email") String email,@QueryParam("persionId") String persionId) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setEmail(email);
        persionInfo.setId(persionId);
        return persionInfoApi.getEmail(persionInfo);
    }

    /**
     * 检验手机号是否存在
     * @param
     * @return
     */
    @GET
    @Path("getPhone")
    public PersionInfo selectPhone(@QueryParam("phoneNum") String phoneNum,@QueryParam("persionId") String persionId) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setPhoneNum(phoneNum);
        return persionInfoApi.getPhone(persionInfo);
    }

}
