/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 字典DAO接口
 *
 * @author zhangyao
 * @version 2016-04-18
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

    /**
     * 查询字典表的类型和描述这两个字段
     *
     * @return 类型描述的列表list集合
     * @author fengyuguang
     */
    public List<Dict> leftList();

    /**
     * 根据字典表的类型查询属于该类型的数据列表
     *
     * @param type 字典表类型
     * @return 字典表某类的列表集合
     * @author fengyuguang
     */
    public List<Dict> rightList(@Param("type")String type,@Param("q")String q);

    /**
     * 根据类型或描述模糊查询
     *
     * @param type
     * @param description
     * @return 查询到的字典表List集合
     * @author fengyuguang
     */
    public List<Dict> select(String type, String description);

    /**
     * 查询所有类型
     *
     * @param dict 字典表实体类对象
     * @return 类型列表
     * @author zhangyao
     */
    public List<String> findTypeList(Dict dict);

    /**
     * 根据类型查询标签列表
     * @param type 类型
     * @return 标签名称列表
     * @author zhangyao
     */
    public List<Dict> findListType(String type);

    /**
     * 根据类型和输入的拼音码检索字典
     * @param type 类型
     * @param inputCode 拼音码
     * @return
     * @author fengyuguang
     */
    public List<Dict> listByType(@Param(value = "type") String type, @Param(value = "inputCode") String inputCode);
    public List<Dict> listByType1(@Param(value = "type") String type, @Param(value = "inputCode") String inputCode);

    public String getLabel(@Param(value = "type") String type, @Param(value = "value") String value);

    public Dict getDictLabel(@Param(value = "type") String type, @Param(value = "value") String value);

    /**
     * 获取地域数据
     * @param area
     * @return
     */
    public List<Dict> findAreaData (String area);

    /**
     * 通过label值拿到value
     *
     * @param label
     * @return
     */
    public String getValue(@Param("type")String type,@Param("label")String label);
}
