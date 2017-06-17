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
public class SecondReview implements Serializable {

    private static final long serialVersionUID = -5398413377888368538L;
    // 时间段
    private String hours_desc;
    // 新增待审笔数
    private String second_review_add_cnt;
    // 新增待审金额
    private String second_review_add_amt;
    // 完成笔数
    private String second_review_finish_cnt;
    // 完成金额
    private String second_review_finish_amt;
    // 拒绝笔数
    private String second_review_refuse_cnt;
    // 拒绝金额
    private String second_review_refuse_amt;
    // 待审笔数
    private String second_review_wait_cnt;

}
