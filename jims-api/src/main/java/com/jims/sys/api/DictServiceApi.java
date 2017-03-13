package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;


/**
 * 字典表Service
 *
 * @author fengyuguang
 * @version 2016-04-18
 */

public interface DictServiceApi {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public Dict get(String id);

    /**
     * 查询字典类型列表
     *
     * @return
     */
    public Page<Dict> findPage(Page<Dict> page, Dict dict);

    /**
     * 查询字典表的类型和描述这两个字段
     *
     * @return 字典表type和description字段的集合
     * @author fengyuguang
     */
    public List<Dict> leftList();

    /**
     * 根据字典表的类型查询属于该类型的数据列表
     *
     * @param type 字典表类型
     * @return 字典表list集合
     * @author fengyuguang
     */
    public List<Dict> rightList(String type,String q);

    /**
     * 保存增删改多条数据
     *
     * @param beanChangeVo 多条增删改数据的集合
     * @return 操作的数据条数
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<Dict> beanChangeVo);

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
     * 查询html类型列表
     *
     * @return
     */
    public List<String> findTypeList();

    /**
     * 保存修改方法
     *
     * @param dict
     */
    public String save(Dict dict);

    /**
     * 删除方法
     *
     * @param ids
     */
    public String delete(String ids);

    /**
     * 获取指定类型列表
     *
     * @param type
     * @return
     */
    public List<Dict> findListType(String type);

    /**
     * 根据类型和输入的拼音码检索字典
     * @param type 类型
     * @param inputCode 拼音码
     * @return
     * @author fengyuguang
     */
    public List<Dict> listByType(String type,String inputCode);
    public List<Dict> listByType1(String type,String inputCode);
    /**
     * 通过value值拿到label
     *
     * @param value
     * @return
     */
    public String getLabel(String value, String type);

    /**
     * 根据类型查询字典
     *
     * @param type
     * @return
     */
    public List<Dict> findList(String type);

    public List<Dict> findList(Dict dict);

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
    public String getValue(String type,String label);
}
