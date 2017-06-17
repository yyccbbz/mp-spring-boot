package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/14
 * @Time: 15:32
 * @Description: 初审类
 */
public class FirstReview implements Serializable {

    private static final long serialVersionUID = -6800514970467753933L;
    // 时间段
    private String hours_desc;
    // 初审新增待审笔数
    private String first_review_add_cnt;
    // 初审新增待审金额
    private String first_review_add_amt;
    // 初审完成笔数
    private String first_review_finish_cnt;
    // 初审完成金额
    private String first_review_finish_amt;
    // 初审拒绝笔数
    private String first_review_refuse_cnt;
    // 初审拒绝金额
    private String first_review_refuse_amt;
    // 初审待审笔数
    private String first_review_wait_cnt;

}
