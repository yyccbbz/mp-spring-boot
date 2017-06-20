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
 * @Description: 申请类 前端控制器
 */
@RestController
@RequestMapping("apply")
public class ApplyController extends BaseController {

    @Value(value = "${apply_http_id}")
    private String apply_http_id;

    @RequestMapping
    public String apply() {
        return parseHttpJsonResult(apply_http_id);
    }


}
