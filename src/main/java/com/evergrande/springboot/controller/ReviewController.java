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
 * @Description: 审批类 前端控制器
 */
@RestController
@RequestMapping("review")
public class ReviewController extends BaseController {

    @Value(value = "${finalReview_http_id}")
    private String finalReview_http_id;

    @Value(value = "${mobileReview_http_id}")
    private String mobileReview_http_id;

    @Value(value = "${firstReview_http_id}")
    private String firstReview_http_id;

    @Value(value = "${secondReview_http_id}")
    private String secondReview_http_id;

    @Value(value = "${reviewTotal_http_id}")
    private String reviewTotal_http_id;

    @RequestMapping("/mobileReview")
    public String mobileReview() {
        return parseHttpJsonResult(mobileReview_http_id);
    }

    @RequestMapping("/firstReview")
    public String firstReview() {
        return parseHttpJsonResult(firstReview_http_id);
    }

    @RequestMapping("/secondReview")
    public String secondReview() {
        return parseHttpJsonResult(secondReview_http_id);
    }

    @RequestMapping("/finalReview")
    public String finalReview() {
        return parseHttpJsonResult(finalReview_http_id);
    }

    @RequestMapping("/reviewTotal")
    public String reviewTotal() {
        return parseHttpJsonResult(reviewTotal_http_id);
    }


}
