package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/14
 * @Time: 15:32
 * @Description: 首页类
 */
public class Homepage implements Serializable {

    private static final long serialVersionUID = -4293472557773933050L;

    // 时间段
    private String hours_desc;
    // 申请笔数
    private String apply_cnt;
    // 申请金额
    private String apply_amt;
    // 初审完成笔数
    private String first_review_finish_cnt;
    // 复审完成笔数
    private String second_review_finish_cnt;
    // 终审完成笔数
    private String final_review_finish_cnt;
    // 审批通过笔数
    private String review_accept_cnt;
    // 签约笔数
    private String sign_finish_cnt;
    // 签约金额
    private String sign_finish_amt;
    // 放款成功笔数
    private String loan_batch_success_cnt;
    // 放款成功金额
    private String loan_batch_success_amt;

}
