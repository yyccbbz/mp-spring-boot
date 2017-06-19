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
 * @Description: 首页报表 前端控制器
 */
@RestController
@RequestMapping("homepage")
public class HomepageController extends BaseController {

    @Value(value = "${homepage_http_id}")
    private String homepage_http_id;

    @RequestMapping
    public String mobileReview() {
        return parseHttpJsonResult(homepage_http_id);
    }


}
