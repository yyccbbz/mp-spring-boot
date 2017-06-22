package com.evergrande.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/14
 * @Time: 18:31
 * @Description: 统计汇总 前端控制器
 */
@RestController
public class CountController extends BaseController {

    @Value(value = "${daily_http_id}")
    private String daily_http_id;

    @RequestMapping("daily")
    public String daily() {
        return parseHttpJsonResult(daily_http_id);
    }


}
