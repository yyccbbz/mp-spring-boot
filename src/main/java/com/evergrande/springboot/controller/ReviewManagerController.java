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
 * @Description: 信审专员 前端控制器
 */
@RestController
@RequestMapping("reviewManager")
public class ReviewManagerController extends BaseController {

    @Value(value = "${reviewManager_http_id}")
    private String reviewManager_http_id;

    @RequestMapping
    public String reviewManager() {
        return parseHttpJsonResult(reviewManager_http_id);
    }


}
