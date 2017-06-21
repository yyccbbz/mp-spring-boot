package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/19
 * @Time: 15:32
 * @Description: 信审专员
 */
public class ReviewManager implements Serializable {

    private static final long serialVersionUID = -2424449365293481948L;

    // 日期
    private String dates;
    // 审批人员ID
    private String userid;
    // 岗位
    private String rolename;
    // 当日新增待审笔数
    private String review_add_cnt;
    // 当日新增待审金额
    private String review_add_amt;
    // 当日审批完成笔数
    private String review_finish_cnt;
    // 当日审批完成金额
    private String review_finish_amt;
    // 历史审批完成笔数
    private String review_finish_all_cnt;
    // 历史审批完成金额
    private String review_finish_all_amt;
    // 当日审批拒绝笔数
    private String review_refuse_cnt;
    // 当日审批拒绝金额
    private String review_refuse_amt;
    // 历史审批拒绝笔数
    private String review_refuse_all_cnt;
    // 历史审批拒绝金额
    private String review_refuse_all_amt;
    // 当前剩余待审笔数
    private String review_wait_cnt;
    // 当前剩余待审金额
    private String review_wait_amt;

}
