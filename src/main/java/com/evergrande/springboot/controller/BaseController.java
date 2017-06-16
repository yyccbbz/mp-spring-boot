package com.evergrande.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.evergrande.springboot.common.http.HttpAPIService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 基础控制器
 * </p>
 *
 * @author CuiCan
 * @Date 2017-06-10
 */
@PropertySource(value = "classpath:properties/httpclient.properties")
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${base_http_url}")
    protected String base_http_url;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Resource
    private HttpAPIService httpAPIService;

    /**
     * http请求数据
     *
     * @param id
     * @return
     */
    protected String parseHttpJsonResult(String id){

        //TODO date参数

        String str = "";
        if(StringUtils.isNotEmpty(id)){

            String url = base_http_url+id+"&date=2017-06-15";
            System.err.println("usl =" + url);
            try {
                str = httpAPIService.doGet(url);
//                System.err.println(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONArray values = JSON.parseObject(str).getJSONObject("details").getJSONObject("list").getJSONArray("values");

//            System.err.println("values = " + values.toJSONString());
            return values.toJSONString();
        }
        return str;
    }

    /**
     * 重定向url
     *
     * @param url
     * @return
     */
    protected String redirectTo(String url) {
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }

}
