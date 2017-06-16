package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/14
 * @Time: 15:32
 * @Description: 终审类
 */
public class FinialReview implements Serializable {

    private static final long serialVersionUID = -4910610656023065903L;

    // 时间段(小时）
    private String hours_desc;
    // 终审新增待审笔数
    private String finial_review_add_cnt;
    // 终审新增待审金额
    private String finial_review_add_amt;
    // 终审完成笔数
    private String finial_review_finish_cnt;
    // 终审完成金额
    private String finial_review_finish_amt;
    // 终审拒绝笔数
    private String finial_review_refuse_cnt;
    // 终审拒绝金额
    private String finial_review_refuse_amt;

}
