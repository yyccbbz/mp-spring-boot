package com.evergrande.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.Feature;
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
//            System.err.println("url =" + url);
            try {
                str = httpAPIService.doGet(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONArray values = JSON.parseObject(str, Feature.OrderedField).getJSONObject("details")
                                    .getJSONObject("list").getJSONArray("values");
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
