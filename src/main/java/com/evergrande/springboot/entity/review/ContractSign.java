package com.evergrande.springboot.entity.review;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/19
 * @Time: 15:32
 * @Description: 签约 面签类
 */
public class ContractSign implements Serializable {

    private static final long serialVersionUID = 934842861668052681L;

    // 日期
    private String dates;
    // 待签笔数
    private String sign_wait_cnt;
    // 待签金额
    private String sign_wait_amt;
    // 签约笔数
    private String sign_finish_cnt;
    // 签约金额
    private String sign_finish_amt;
    // 签约失效笔数
    private String sign_invalid_cnt;
    // 签约失效金额
    private String sign_invalid_amt;

}
