package com.evergrande.springboot.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 员工贷报表（贷前日报）
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-08
 */
@TableName("employee_credit_report")
public class EmployeeCreditReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 日期
     */
    private Date today;
    /**
     * 时间段(小时）格式：[0,1)
     */
    private String period;
    /**
     * 当前时间段内申请笔数
     */
    @TableField("per_application_num")
    private Integer perApplicationNum;
    /**
     * 当日申请笔数
     */
    @TableField("day_application_num")
    private Integer dayApplicationNum;
    /**
     * 历史申请笔数
     */
    @TableField("his_application_num")
    private Integer hisApplicationNum;
    /**
     * 当前时间段内HR审批完成笔数
     */
    @TableField("per_hr_approval_num")
    private Integer perHrApprovalNum;
    /**
     * 当日HR审批完成笔数
     */
    @TableField("day_hr_approval_num")
    private Integer dayHrApprovalNum;
    /**
     * 历史HR审批完成笔数
     */
    @TableField("his_hr_approval_num")
    private Integer hisHrApprovalNum;
    /**
     * 当前HR剩余未审批笔数
     */
    @TableField("cur_hr_unapproved_num")
    private Integer curHrUnapprovedNum;
    /**
     * 当前时间段内初审完成笔数
     */
    @TableField("per_first_trial_num")
    private Integer perFirstTrialNum;
    /**
     * 当日初审完成笔数
     */
    @TableField("day_first_trial_num")
    private Integer dayFirstTrialNum;
    /**
     * 历史初审完成笔数
     */
    @TableField("his_first_trial_num")
    private Integer hisFirstTrialNum;
    /**
     * 当前剩余初审待审笔数
     */
    @TableField("cur_pending_first_trial_num")
    private Integer curPendingFirstTrialNum;
    /**
     * 当前时间段内复审完成笔数
     */
    @TableField("per_review_num")
    private Integer perReviewNum;
    /**
     * 当日复审完成笔数
     */
    @TableField("day_review_num")
    private Integer dayReviewNum;
    /**
     * 历史复审完成笔数
     */
    @TableField("his_review_num")
    private Integer hisReviewNum;
    /**
     * 当前复审待审笔数
     */
    @TableField("cur_pending_review_num")
    private Integer curPendingReviewNum;
    /**
     * 当前时间段内审批通过笔数
     */
    @TableField("per_passes_num")
    private Integer perPassesNum;
    /**
     * 当日审批通过笔数
     */
    @TableField("day_passes_num")
    private Integer dayPassesNum;
    /**
     * 历史审批通过笔数
     */
    @TableField("his_passes_num")
    private Integer hisPassesNum;
    /**
     * 当前时间段内签约笔数
     */
    @TableField("per_signed_num")
    private Integer perSignedNum;
    /**
     * 当日签约笔数
     */
    @TableField("day_signed_num")
    private Integer daySignedNum;
    /**
     * 历史签约笔数
     */
    @TableField("his_signed_num")
    private Integer hisSignedNum;
    /**
     * 当前时间段内放款成功笔数
     */
    @TableField("per_success_loan_num")
    private Integer perSuccessLoanNum;
    /**
     * 当日放款成功笔数
     */
    @TableField("day_success_loan_num")
    private Integer daySuccessLoanNum;
    /**
     * 历史放款成功笔数
     */
    @TableField("his_success_loan_num")
    private Integer hisSuccessLoanNum;
    /**
     * 当前时间段内放款成功金额
     */
    @TableField("per_success_loan_amount")
    private Double perSuccessLoanAmount;
    /**
     * 当日放款成功金额
     */
    @TableField("day_success_loan_amount")
    private Double daySuccessLoanAmount;
    /**
     * 历史放款成功金额
     */
    @TableField("his_success_loan_amount")
    private Double hisSuccessLoanAmount;
    /**
     * 当前时间段内放款失败笔数
     */
    @TableField("per_failed_loan_num")
    private Integer perFailedLoanNum;
    /**
     * 当日放款失败笔数
     */
    @TableField("day_failed_loan_num")
    private Integer dayFailedLoanNum;
    /**
     * 历史放款失败笔数
     */
    @TableField("his_failed_loan_num")
    private Integer hisFailedLoanNum;
    /**
     * 当前时间段内放款失败金额
     */
    @TableField("per_failed_loan_amount")
    private Double perFailedLoanAmount;
    /**
     * 当日放款失败金额
     */
    @TableField("day_failed_loan_amount")
    private Double dayFailedLoanAmount;
    /**
     * 历史放款失败金额
     */
    @TableField("his_failed_loan_amount")
    private Double hisFailedLoanAmount;
    /**
     * 当前已审未签笔数
     */
    @TableField("cur_not_signed_num")
    private Integer curNotSignedNum;
    /**
     * 当前已审未签金额
     */
    @TableField("cur_not_signed_amount")
    private Double curNotSignedAmount;
    /**
     * 当前未结清贷款笔数
     */
    @TableField("cur_outstanding_loan_num")
    private Integer curOutstandingLoanNum;
    /**
     * 当前贷款余额（剩余未还本金）
     */
    @TableField("cur_loan_balance")
    private Double curLoanBalance;


    public EmployeeCreditReport() {
    }

    public EmployeeCreditReport(Long id, Date today, String period, Integer perApplicationNum,
                                Integer dayApplicationNum, Integer hisApplicationNum, Integer perHrApprovalNum,
                                Integer dayHrApprovalNum, Integer hisHrApprovalNum, Integer curHrUnapprovedNum,
                                Integer perFirstTrialNum, Integer dayFirstTrialNum, Integer hisFirstTrialNum,
                                Integer curPendingFirstTrialNum, Integer perReviewNum, Integer dayReviewNum,
                                Integer hisReviewNum, Integer curPendingReviewNum, Integer perPassesNum,
                                Integer dayPassesNum, Integer hisPassesNum, Integer perSignedNum,
                                Integer daySignedNum, Integer hisSignedNum, Integer perSuccessLoanNum,
                                Integer daySuccessLoanNum, Integer hisSuccessLoanNum, Double perSuccessLoanAmount,
                                Double daySuccessLoanAmount, Double hisSuccessLoanAmount, Integer perFailedLoanNum,
                                Integer dayFailedLoanNum, Integer hisFailedLoanNum, Double perFailedLoanAmount,
                                Double dayFailedLoanAmount, Double hisFailedLoanAmount, Integer curNotSignedNum,
                                Double curNotSignedAmount, Integer curOutstandingLoanNum, Double curLoanBalance) {
        this.id = id;
        this.today = today;
        this.period = period;
        this.perApplicationNum = perApplicationNum;
        this.dayApplicationNum = dayApplicationNum;
        this.hisApplicationNum = hisApplicationNum;
        this.perHrApprovalNum = perHrApprovalNum;
        this.dayHrApprovalNum = dayHrApprovalNum;
        this.hisHrApprovalNum = hisHrApprovalNum;
        this.curHrUnapprovedNum = curHrUnapprovedNum;
        this.perFirstTrialNum = perFirstTrialNum;
        this.dayFirstTrialNum = dayFirstTrialNum;
        this.hisFirstTrialNum = hisFirstTrialNum;
        this.curPendingFirstTrialNum = curPendingFirstTrialNum;
        this.perReviewNum = perReviewNum;
        this.dayReviewNum = dayReviewNum;
        this.hisReviewNum = hisReviewNum;
        this.curPendingReviewNum = curPendingReviewNum;
        this.perPassesNum = perPassesNum;
        this.dayPassesNum = dayPassesNum;
        this.hisPassesNum = hisPassesNum;
        this.perSignedNum = perSignedNum;
        this.daySignedNum = daySignedNum;
        this.hisSignedNum = hisSignedNum;
        this.perSuccessLoanNum = perSuccessLoanNum;
        this.daySuccessLoanNum = daySuccessLoanNum;
        this.hisSuccessLoanNum = hisSuccessLoanNum;
        this.perSuccessLoanAmount = perSuccessLoanAmount;
        this.daySuccessLoanAmount = daySuccessLoanAmount;
        this.hisSuccessLoanAmount = hisSuccessLoanAmount;
        this.perFailedLoanNum = perFailedLoanNum;
        this.dayFailedLoanNum = dayFailedLoanNum;
        this.hisFailedLoanNum = hisFailedLoanNum;
        this.perFailedLoanAmount = perFailedLoanAmount;
        this.dayFailedLoanAmount = dayFailedLoanAmount;
        this.hisFailedLoanAmount = hisFailedLoanAmount;
        this.curNotSignedNum = curNotSignedNum;
        this.curNotSignedAmount = curNotSignedAmount;
        this.curOutstandingLoanNum = curOutstandingLoanNum;
        this.curLoanBalance = curLoanBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getPerApplicationNum() {
        return perApplicationNum;
    }

    public void setPerApplicationNum(Integer perApplicationNum) {
        this.perApplicationNum = perApplicationNum;
    }

    public Integer getDayApplicationNum() {
        return dayApplicationNum;
    }

    public void setDayApplicationNum(Integer dayApplicationNum) {
        this.dayApplicationNum = dayApplicationNum;
    }

    public Integer getHisApplicationNum() {
        return hisApplicationNum;
    }

    public void setHisApplicationNum(Integer hisApplicationNum) {
        this.hisApplicationNum = hisApplicationNum;
    }

    public Integer getPerHrApprovalNum() {
        return perHrApprovalNum;
    }

    public void setPerHrApprovalNum(Integer perHrApprovalNum) {
        this.perHrApprovalNum = perHrApprovalNum;
    }

    public Integer getDayHrApprovalNum() {
        return dayHrApprovalNum;
    }

    public void setDayHrApprovalNum(Integer dayHrApprovalNum) {
        this.dayHrApprovalNum = dayHrApprovalNum;
    }

    public Integer getHisHrApprovalNum() {
        return hisHrApprovalNum;
    }

    public void setHisHrApprovalNum(Integer hisHrApprovalNum) {
        this.hisHrApprovalNum = hisHrApprovalNum;
    }

    public Integer getCurHrUnapprovedNum() {
        return curHrUnapprovedNum;
    }

    public void setCurHrUnapprovedNum(Integer curHrUnapprovedNum) {
        this.curHrUnapprovedNum = curHrUnapprovedNum;
    }

    public Integer getPerFirstTrialNum() {
        return perFirstTrialNum;
    }

    public void setPerFirstTrialNum(Integer perFirstTrialNum) {
        this.perFirstTrialNum = perFirstTrialNum;
    }

    public Integer getDayFirstTrialNum() {
        return dayFirstTrialNum;
    }

    public void setDayFirstTrialNum(Integer dayFirstTrialNum) {
        this.dayFirstTrialNum = dayFirstTrialNum;
    }

    public Integer getHisFirstTrialNum() {
        return hisFirstTrialNum;
    }

    public void setHisFirstTrialNum(Integer hisFirstTrialNum) {
        this.hisFirstTrialNum = hisFirstTrialNum;
    }

    public Integer getCurPendingFirstTrialNum() {
        return curPendingFirstTrialNum;
    }

    public void setCurPendingFirstTrialNum(Integer curPendingFirstTrialNum) {
        this.curPendingFirstTrialNum = curPendingFirstTrialNum;
    }

    public Integer getPerReviewNum() {
        return perReviewNum;
    }

    public void setPerReviewNum(Integer perReviewNum) {
        this.perReviewNum = perReviewNum;
    }

    public Integer getDayReviewNum() {
        return dayReviewNum;
    }

    public void setDayReviewNum(Integer dayReviewNum) {
        this.dayReviewNum = dayReviewNum;
    }

    public Integer getHisReviewNum() {
        return hisReviewNum;
    }

    public void setHisReviewNum(Integer hisReviewNum) {
        this.hisReviewNum = hisReviewNum;
    }

    public Integer getCurPendingReviewNum() {
        return curPendingReviewNum;
    }

    public void setCurPendingReviewNum(Integer curPendingReviewNum) {
        this.curPendingReviewNum = curPendingReviewNum;
    }

    public Integer getPerPassesNum() {
        return perPassesNum;
    }

    public void setPerPassesNum(Integer perPassesNum) {
        this.perPassesNum = perPassesNum;
    }

    public Integer getDayPassesNum() {
        return dayPassesNum;
    }

    public void setDayPassesNum(Integer dayPassesNum) {
        this.dayPassesNum = dayPassesNum;
    }

    public Integer getHisPassesNum() {
        return hisPassesNum;
    }

    public void setHisPassesNum(Integer hisPassesNum) {
        this.hisPassesNum = hisPassesNum;
    }

    public Integer getPerSignedNum() {
        return perSignedNum;
    }

    public void setPerSignedNum(Integer perSignedNum) {
        this.perSignedNum = perSignedNum;
    }

    public Integer getDaySignedNum() {
        return daySignedNum;
    }

    public void setDaySignedNum(Integer daySignedNum) {
        this.daySignedNum = daySignedNum;
    }

    public Integer getHisSignedNum() {
        return hisSignedNum;
    }

    public void setHisSignedNum(Integer hisSignedNum) {
        this.hisSignedNum = hisSignedNum;
    }

    public Integer getPerSuccessLoanNum() {
        return perSuccessLoanNum;
    }

    public void setPerSuccessLoanNum(Integer perSuccessLoanNum) {
        this.perSuccessLoanNum = perSuccessLoanNum;
    }

    public Integer getDaySuccessLoanNum() {
        return daySuccessLoanNum;
    }

    public void setDaySuccessLoanNum(Integer daySuccessLoanNum) {
        this.daySuccessLoanNum = daySuccessLoanNum;
    }

    public Integer getHisSuccessLoanNum() {
        return hisSuccessLoanNum;
    }

    public void setHisSuccessLoanNum(Integer hisSuccessLoanNum) {
        this.hisSuccessLoanNum = hisSuccessLoanNum;
    }

    public Double getPerSuccessLoanAmount() {
        return perSuccessLoanAmount;
    }

    public void setPerSuccessLoanAmount(Double perSuccessLoanAmount) {
        this.perSuccessLoanAmount = perSuccessLoanAmount;
    }

    public Double getDaySuccessLoanAmount() {
        return daySuccessLoanAmount;
    }

    public void setDaySuccessLoanAmount(Double daySuccessLoanAmount) {
        this.daySuccessLoanAmount = daySuccessLoanAmount;
    }

    public Double getHisSuccessLoanAmount() {
        return hisSuccessLoanAmount;
    }

    public void setHisSuccessLoanAmount(Double hisSuccessLoanAmount) {
        this.hisSuccessLoanAmount = hisSuccessLoanAmount;
    }

    public Integer getPerFailedLoanNum() {
        return perFailedLoanNum;
    }

    public void setPerFailedLoanNum(Integer perFailedLoanNum) {
        this.perFailedLoanNum = perFailedLoanNum;
    }

    public Integer getDayFailedLoanNum() {
        return dayFailedLoanNum;
    }

    public void setDayFailedLoanNum(Integer dayFailedLoanNum) {
        this.dayFailedLoanNum = dayFailedLoanNum;
    }

    public Integer getHisFailedLoanNum() {
        return hisFailedLoanNum;
    }

    public void setHisFailedLoanNum(Integer hisFailedLoanNum) {
        this.hisFailedLoanNum = hisFailedLoanNum;
    }

    public Double getPerFailedLoanAmount() {
        return perFailedLoanAmount;
    }

    public void setPerFailedLoanAmount(Double perFailedLoanAmount) {
        this.perFailedLoanAmount = perFailedLoanAmount;
    }

    public Double getDayFailedLoanAmount() {
        return dayFailedLoanAmount;
    }

    public void setDayFailedLoanAmount(Double dayFailedLoanAmount) {
        this.dayFailedLoanAmount = dayFailedLoanAmount;
    }

    public Double getHisFailedLoanAmount() {
        return hisFailedLoanAmount;
    }

    public void setHisFailedLoanAmount(Double hisFailedLoanAmount) {
        this.hisFailedLoanAmount = hisFailedLoanAmount;
    }

    public Integer getCurNotSignedNum() {
        return curNotSignedNum;
    }

    public void setCurNotSignedNum(Integer curNotSignedNum) {
        this.curNotSignedNum = curNotSignedNum;
    }

    public Double getCurNotSignedAmount() {
        return curNotSignedAmount;
    }

    public void setCurNotSignedAmount(Double curNotSignedAmount) {
        this.curNotSignedAmount = curNotSignedAmount;
    }

    public Integer getCurOutstandingLoanNum() {
        return curOutstandingLoanNum;
    }

    public void setCurOutstandingLoanNum(Integer curOutstandingLoanNum) {
        this.curOutstandingLoanNum = curOutstandingLoanNum;
    }

    public Double getCurLoanBalance() {
        return curLoanBalance;
    }

    public void setCurLoanBalance(Double curLoanBalance) {
        this.curLoanBalance = curLoanBalance;
    }

}
