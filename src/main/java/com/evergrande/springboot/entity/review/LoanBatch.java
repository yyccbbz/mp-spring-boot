package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/19
 * @Time: 15:32
 * @Description: 放款
 */
public class LoanBatch implements Serializable {

    private static final long serialVersionUID = -3829729953683910757L;
    // 日期
    private String dates;
    // 放款成功笔数
    private String loan_batch_success_cnt;
    // 放款成功金额
    private String loan_batch_success_amt;
    // 放款失败笔数
    private String loan_batch_fail_cnt;
    // 放款失败金额
    private String loan_batch_fail_amt;
    // 当前未结清贷款笔数
    private String loan_unfinish_cnt;
    // 当前贷款余额（剩余未还本金）
    private String loan_unfinish_amt;
    // 累计贷款结清金额
    private String loan_finish_amt;
    // 1月期放款笔数
    private String loan_batch_success_1m_cnt;
    // 1月期放款金额
    private String loan_batch_success_1m_amt;
    // 3月期放款笔数
    private String loan_batch_success_3m_cnt;
    // 3月期放款金额
    private String loan_batch_success_3m_amt;
    // 6月期放款笔数
    private String loan_batch_success_6m_cnt;
    // 6月期放款金额
    private String loan_batch_success_6m_amt;
    // 12月期放款笔数
    private String loan_batch_success_12m_cnt;
    // 12月期放款金额
    private String loan_batch_success_12m_amt;
    // 24月期放款笔数
    private String loan_batch_success_24m_cnt;
    // 24月期放款金额
    private String loan_batch_success_24m_amt;
    // 36月期放款笔数
    private String loan_batch_success_36m_cnt;
    // 36月期放款金额
    private String loan_batch_success_36m_amt;
}
