package com.jims.lab.api;

import com.jims.common.vo.LoginInfo;
import com.jims.lab.entity.LabTestMaster;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 * 检验确认 API接口
 * @author zhaoning
 */
public interface LabConfirmServiceApi {
    /**
     * 根据当前登录人所在的执行科室 查询 检验主记录
      * @param
     * @return
     * @author zhaoning
     */
  public List<LabTestMaster> getLabMaster(String inOrOut,String startTime,String endTime,String reqDept,String labNo,String patName,LoginInfo loginInfo);

    /**
     * 检验确认，
     * @param labTestMaster
     * @return
     * @author zhaoning
     */
    public String confrimLab(LabTestMaster labTestMaster,LoginInfo loginInfo)throws Exception;
}
