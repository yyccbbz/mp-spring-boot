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
 * @Description: 签约类 前端控制器
 */
@RestController
@RequestMapping("contractSign")
public class ContractSignController extends BaseController {

    @Value(value = "${contractSign_http_id}")
    private String contractSign_http_id;

    @RequestMapping
    public String contractSign() {
        return parseHttpJsonResult(contractSign_http_id);
    }


}
