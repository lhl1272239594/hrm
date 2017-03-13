package com.jims.lab.api;

import com.jims.common.vo.LoginInfo;
import com.jims.lab.entity.LabTestItems;

import java.util.List;


/**
 * Created by Administrator on 2016/4/28.
 * 检验项目Api接口
 * @author xueyx
 * @version 2016-04-28
 */
public interface LabTestItemsServiceApi {
    public List<LabTestItems> getItemName(String labMaster);

    /**
     * 护士端 检验打印
     * @param labMaster
     * @param loginInfo
     * @return
     */
    public String printNurses(String labMaster,LoginInfo loginInfo);
}
