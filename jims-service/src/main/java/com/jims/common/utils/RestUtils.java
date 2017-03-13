package com.jims.common.utils;

import com.jims.common.utils.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * rest客户端调用
 * @param <T>
 * @param <E>
 */

public class RestUtils<T, E> {
    private RestTemplate template = new RestTemplate();

    public RestUtils(){
        this.strurl=getConfig("printPath");
    }
    private String strurl;
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("jims.properties");

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();
    /**
     * 获取配置(配置属性)
     *
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * @param '[url, responseType, map]' 传递参数
     * @return java.util.Map<java.lang.String,java.lang.Object> 返回类型
     * @throws
     * @Title: findById
     * @Description: (返回单对象 body为返回的实体)
     * @author chengshaowei
     * @date 2015-06-24 15:45
     */
    public Map<String, Object> findById(String url, Class<T> responseType, Map<String, Object> map) {
        Map<String, Object> mapByres = new HashMap<String, Object>();
        mapByres.put("uri", strurl + url);
        if (strurl != null && !"".equals(strurl)) {
            ResponseEntity<T> entity = template.getForEntity(strurl+url, responseType, map);
            mapByres.put("statusCode", entity.getStatusCode() + "");//返回状态
            mapByres.put("body", entity.getBody());//返回对象
            return mapByres;
        }
        mapByres.put("statusCode", "-1");//返回状态
        return mapByres;
    }

    /**
     * @param '[url, responseType, t, map]' 传递参数
     * @return java.util.Map<java.lang.String,java.lang.Object> 返回类型
     * @throws
     * @Title: findById
     * @Description: (传入对象)
     * @author chengshaowei
     * @date 2015-06-25 08:49
     */
    public Map<String, Object> findById(String url, Class<T> responseType, E t, Map<String, Object> map, String hosid) {
        Map<String, Object> mapByres = new HashMap<String, Object>();
        mapByres.put("uri", strurl + url);
        if (strurl != null && !"".equals(strurl)) {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<E> requestEntity = new HttpEntity<E>(t, requestHeaders);

            ResponseEntity<T> entity = template.exchange(strurl+url, HttpMethod.POST, requestEntity, responseType, map);


            mapByres.put("statusCode", entity.getStatusCode() + "");//返回状态
            mapByres.put("body", entity.getBody());//返回对象
            return mapByres;
        }
        mapByres.put("statusCode", "-1");//返回状态
        return mapByres;
    }

    /**
     * @param '[url, responseType, map]' 传递参数
     * @return java.util.List<T> 返回类型
     * @throws
     * @Title: findByList
     * @Description: (返回list集合)
     * @author chengshaowei
     * @date 2015-06-24 15:47
     */
    public List<T> findByList(String url, Class<T> responseType, Map<String, Object> map) {
        String str = template.getForObject(url, String.class, map);
        JsonMapper ts = new JsonMapper();
        List<T> list = ts.fromJson(str, ts.createCollectionType(ArrayList.class, responseType));
        return list;
    }

    /**
     * @param '[url, responseType, t, map]' 传递参数
     * @return java.util.Map<java.lang.String,java.lang.Object> 返回类型
     * @throws
     * @Title: findByList
     * @Description: (传入对象)
     * @author chengshaowei
     * @date 2015-06-25 08:49
     */
    public Map<String, Object> findByList(String url, Class<T> responseType, E t, Map<String, Object> map) {
        Map<String, Object> mapByres = new HashMap<String, Object>();
        if (strurl != null && !"".equals(strurl)) {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<E> requestEntity = new HttpEntity<E>(t, requestHeaders);

            ResponseEntity<String> response = template.exchange(strurl+url, HttpMethod.POST, requestEntity, String.class, map);
            JsonMapper ts = new JsonMapper();
            List<T> list = ts.fromJson(response.getBody(), ts.createCollectionType(ArrayList.class, responseType));
            mapByres.put("statusCode", response.getStatusCode() + "");//返回状态
            mapByres.put("body", list);//返回对象
            return mapByres;
        }
        mapByres.put("statusCode", "-1");//返回状态
        return mapByres;
    }

    /**
     * @param '[url, t]' 传递参数
     * @return java.util.Map<java.lang.String,java.lang.Object> 返回类型
     * @throws
     * @Title: saveOrUpdate
     * @Description: (修改保存方法)
     * @author chengshaowei
     * @date 2015-06-25 10:42
     */
    public Map<String, Object> saveOrUpdate(String url, Class<T> responseType, E t, String hosid) {
        Map<String, Object> mapByres = new HashMap<String, Object>();
        if (strurl != null && !"".equals(strurl)) {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<E> requestEntity = new HttpEntity<E>(t, requestHeaders);

            ResponseEntity<T> response = template.exchange(strurl+url, HttpMethod.POST, requestEntity, responseType);

            mapByres.put("statusCode", response.getStatusCode() + "");//返回状态
            mapByres.put("body", response.getBody());//返回值
            return mapByres;
        }
        mapByres.put("statusCode", "-1");//返回状态
        return mapByres;
    }

    /**
     * @param '[url, t]' 传递参数
     * @return java.util.Map<java.lang.String,java.lang.Object> 返回类型
     * @throws
     * @Title: delete
     * @Description: (删除方法)
     * @author chengshaowei
     * @date 2015-06-25 10:46
     */
    public Map<String, Object> delete(String url, Class<T> responseType, E t, String hosid) {
        Map<String, Object> mapByres = new HashMap<String, Object>();
        if (strurl != null && !"".equals(strurl)) {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<E> requestEntity = new HttpEntity<E>(t, requestHeaders);
            ResponseEntity<T> response = template.exchange(strurl+url, HttpMethod.POST, requestEntity, responseType);
            mapByres.put("statusCode", response.getStatusCode() + "");//返回状态
            mapByres.put("body", response.getBody());//返回值
            return mapByres;
        }
        mapByres.put("statusCode", "-1");//返回状态
        return mapByres;
    }

}
