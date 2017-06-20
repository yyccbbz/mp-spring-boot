package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/19
 * @Time: 15:32
 * @Description: 审批汇总
 */
public class ReviewTotal implements Serializable {

    private static final long serialVersionUID = -6424502796535624983L;

    // 时间段
    private String hours_desc;
    // 审批通过笔数
    private String review_accept_cnt;
    // 人工审批（初审+复审+终审）拒绝笔数
    private String review_refuse_cnt;
    // 人工审批（初审+复审+终审）拒绝金额
    private String review_refuse_amt;
    // 人工审批（复审+终审）完成笔数
    private String review_finish_cnt;
    // 人工审批（复审+终审）完成金额
    private String review_finish_amt;

}
