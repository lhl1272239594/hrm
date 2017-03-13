package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.StaffGroupClassDictDao;
import com.jims.sys.dao.StaffGroupDictDao;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.vo.StaffGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangruidong on 2016-07-19.
 */
@Service
@Component
@Transactional(readOnly = false)
public class StaffGroupClassDictBo extends CrudImplService<StaffGroupClassDictDao,StaffGroupClassDict> {


    public List<String> findTypeList(StaffGroupClassDict staffGroupClassDict) {
        return dao.findTypeList(staffGroupClassDict);
}


}
