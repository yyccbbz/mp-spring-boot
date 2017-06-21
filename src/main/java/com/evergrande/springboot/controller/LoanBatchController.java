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
 * @Description: 放款 前端控制器
 */
@RestController
@RequestMapping("loanBatch")
public class LoanBatchController extends BaseController {

    @Value(value = "${loanBatch_http_id}")
    private String loanBatch_http_id;

    @RequestMapping
    public String loanBatch() {
        return parseHttpJsonResult(loanBatch_http_id);
    }


}
