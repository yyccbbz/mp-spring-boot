package com.evergrande.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.evergrande.springboot.common.http.HttpAPIService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/15
 * @Time: 11:38
 * @Description: @Resource默认是按照名称来装配注入的，只有当找不到与名称匹配的bean才会按照类型来装配注入；
 */
@RestController
public class HttpClientController {

    @Resource
    private HttpAPIService httpAPIService;

    @RequestMapping("httpclient")
    public String test() throws Exception {
//        String str = httpAPIService.doGet("http://www.baidu.com");
        String str = httpAPIService.doGet("http://ds.idc.xiwanglife.com/dataservice/getconfig.do?id=164&date=2017-06-14");
        System.err.println(str);

        JSONObject json = JSON.parseObject(str);
        JSONObject details = json.getJSONObject("details");
        System.out.println("details = " + details);

        JSONObject list = details.getJSONObject("list");
        System.out.println("list = " + list);

        JSONArray values = list.getJSONArray("values");
        System.out.println("values = " + values);

        return "hello";
    }

}
