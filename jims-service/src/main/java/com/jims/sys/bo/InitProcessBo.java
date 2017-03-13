package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.InitProcessDao;
import com.jims.sys.entity.InitProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 2016/8/1.
 */
@Service
@Component
@Transactional(readOnly = false)
public class InitProcessBo extends CrudImplService<InitProcessDao, InitProcess> {

    @Autowired
    private InitProcessDao initProcessDao;


    /**
     * 根据orgId查询
     * @param orgId
     * @return
     * @author  zq
     */
    public List<InitProcess>findByOrgId(String orgId){
        return initProcessDao.findByOrgId(orgId);
    }

    /**
     * 保存全部
     * @return
     * @author  zq
     */
    public String saveList(List<InitProcess> initProcesses){
        int init=0;
        for (InitProcess initProcess : initProcesses){
            if("".equals(initProcess.getId())||null == initProcess.getId()){
                initProcess.setInitFlag("0");
                init=initProcessDao.insert(initProcess);
            }else {
                initProcess.setInitFlag("0");
                init=initProcessDao.update(initProcess);
            }
        }
        return String.valueOf(init);
    }

    /**
     * 根据菜单表数据生成initProcess表数据
     * @author zq
     */
    public List<InitProcess>findMenuList(){
        return initProcessDao.findMenuList();
    }

}