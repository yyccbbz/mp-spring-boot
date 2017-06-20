package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/19
 * @Time: 15:32
 * @Description: 申请类
 */
public class Apply implements Serializable {

    private static final long serialVersionUID = -913427300369665527L;

    // 时间段
    private String hours_desc;
    // 申请笔数
    private String apply_cnt;
    // 申请金额
    private String apply_amt;
    // 优惠利率申请件数
    private String apply_reduction_cnt;
    // 优惠利率申请金额
    private String apply_reduction_amt;
    // 1月期申请笔数
    private String apply_cnt_m1;
    // 1月期申请金额
    private String apply_amt_m1;
    // 3月期申请笔数
    private String apply_cnt_m3;
    // 3月期申请金额
    private String apply_amt_m3;
    // 6月期申请笔数
    private String apply_cnt_m6;
    // 6月期申请金额
    private String apply_amt_m6;
    // 12月期申请笔数
    private String apply_cnt_m12;
    // 12月期申请金额
    private String apply_amt_m12;
    // 24月期申请笔数
    private String apply_cnt_m24;
    // 24月期申请金额
    private String apply_amt_m24;
    // 36月期申请笔数
    private String apply_cnt_m36;
    // 36月期申请金额
    private String apply_amt_m36;

}
