package com.jims.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形结构工具类
 * Created by lgx on 2016/6/13.
 */
public class TreeUtils {

    /**
     *  处理集合，使成为树形结构
     * @param nodes ，需要处理的集合
     * @param keyMap 集合中的对象的关键字段
     *               id  ->   对象的主键字段（例如：id）
     *               pid  ->   对象的父ID字段（例如：pid）
     *               children  ->   对象的子节点字段（例如：children）
     * @param <T>
     * @return 树形结构的集合
     */
    public static <T> List<T> handleTreeList(List<T> nodes,Map<String,String> keyMap){
        List<T> treeList = new ArrayList<T>();
        Map<Object,T> nodeMap = new HashMap<Object, T>();
        List<Object> rootList = new ArrayList<Object>();

        try {
            if(nodes != null && nodes.size() > 1){
                Class c = nodes.get(0).getClass();
                PropertyDescriptor id = new PropertyDescriptor(keyMap.get("id"), c);
                PropertyDescriptor parentId = new PropertyDescriptor(keyMap.get("pid"), c);
                PropertyDescriptor children = new PropertyDescriptor(keyMap.get("children"), c);
                Method getId = id.getReadMethod();
                Method getParentId = parentId.getReadMethod();
                Method getChildren = children.getReadMethod();
                Method setChildren = children.getWriteMethod();

                for(int i=0,j=nodes.size();i<j;i++){
                    nodeMap.put(getId.invoke(nodes.get(i)),nodes.get(i));
                }
                for(int i=0,j=nodes.size();i<j;i++){
                    Object key = getId.invoke(nodes.get(i));
                    Object pid = getParentId.invoke(nodeMap.get(key));
                    if(nodeMap.get(pid) != null){
                        if(getChildren.invoke(nodeMap.get(pid)) == null){
                            setChildren.invoke(nodeMap.get(pid),new ArrayList<T>());
                        }
                        ((List)getChildren.invoke(nodeMap.get(pid))).add(nodeMap.get(key));
                    } else {
                        rootList.add(key);
                    }
                }
                for(int i=0;i<rootList.size();i++){
                    treeList.add(nodeMap.get(rootList.get(i)));
                }
                return treeList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodes;
    }

    /**
     *  处理集合，使成为树形结构
     * @param nodes ，需要处理的集合
     * @param <T>
     * @return 树形结构的集合
     */
    public static <T> List<T> handleTreeList(List<T> nodes){
        // Map参数 默认值
        Map<String,String> keyMap = new HashMap<String, String>();
        keyMap.put("id","id");
        keyMap.put("pid","pid");
        keyMap.put("children","children");

        return handleTreeList(nodes,keyMap);
    }
}
