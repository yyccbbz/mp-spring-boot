package com.evergrande.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.evergrande.springboot.common.http.HttpAPIService;
import com.evergrande.springboot.common.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedHashMap;

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
    protected String parseHttpJsonResult(String id) {

        String day = request.getParameter("day");
        String today = DateUtil.thisDate();
        String str = "";

        if (StringUtils.isEmpty(day)) {
            day = today;
        }

        if (StringUtils.isNotEmpty(id)) {
            String url = base_http_url + id + "&date=" + day;
            System.err.println("url =" + url);
            try {
                str = httpAPIService.doGet(url);
//                str = "{\"status\":\"200\",\"message\":\"\",\"id\":164,\"configName\":\"终审\",\"configType\":\"1\",\"isInterface\":1,\"isTask\":1,\"remark\":null,\"details\":{\"list\":{\"id\":1129,\"values\":[{\"hours_desc\":\"23-24时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"22-23时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"21-22时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"20-21时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"19-20时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"18-19时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"17-18时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"16-17时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"15-16时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"14-15时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"13-14时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"12-13时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"11-12时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"10-11时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"9-10时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"8-9时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"7-8时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"6-7时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"5-6时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"4-5时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"3-4时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"2-3时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"1-2时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"0-1时\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"当天累计\",\"final_review_add_cnt\":0,\"final_review_add_amt\":0,\"final_review_finish_cnt\":0,\"final_review_finish_amt\":0,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000},{\"hours_desc\":\"历史累计\",\"final_review_add_cnt\":2,\"final_review_add_amt\":100000,\"final_review_finish_cnt\":2,\"final_review_finish_amt\":100000,\"final_review_refuse_cnt\":0,\"final_review_refuse_amt\":0,\"final_review_wait_cnt\":0,\"report_time\":1497433030000}],\"errorMessage\":\"\"}}}";
                System.err.println(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            JSONArray values = JSON.parseObject(str).getJSONObject("details").getJSONObject("list").getJSONArray("values");

            LinkedHashMap<String, String> jsonMap = JSON.parseObject(str, new TypeReference<LinkedHashMap<String, String>>() {
            });

            JSONObject jsonObject = JSON.parseObject(str);
            System.out.println("jsonObject = " + jsonObject);
            JSONObject details = jsonObject.getJSONObject("details");
            System.out.println("details = " + details);
            JSONObject list = details.getJSONObject("list");
            System.out.println("list = " + list);
            JSONArray values = list.getJSONArray("values");
            System.out.println("values = " + values);
            // 首页，申请，电审，初审，复审，终审，审批汇总--24+2
            String[] strarr1 = {"171","172","165","166","167","164","173"};
            // 签约，放款--24+1
            //String[] strarr2 = {"174","101"};
            // 信审专员--24+0
            //String[] strarr3 = {"100"};

            // 如果当天，那么要看小时数
            if (today.equals(day) && Arrays.asList(strarr1).contains(id) && values != null && values.size() > 0) {
                String thisTime = DateUtil.thisTime();
                String s = thisTime.split(":")[0];
                int num = 24 - Integer.parseInt(s);
                for (int i = 1 ; i < num; i++){
                    values.remove(0);
                }
            }
            System.err.println("values = " + values);
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
