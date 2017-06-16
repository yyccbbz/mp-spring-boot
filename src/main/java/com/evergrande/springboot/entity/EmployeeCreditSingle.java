package com.evergrande.springboot.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 员工贷单条查询（贷款状态查询）
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-08
 */
@TableName("employee_credit_single")
public class EmployeeCreditSingle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 姓名
     */
	@TableField("employee_name")
	private String employeeName;
    /**
     * 身份证号  后四位打星
     */
	@TableField("id_no")
	private String idNo;
    /**
     * 手机号
     */
	@TableField("mobile_no")
	private String mobileNo;
    /**
     * 员工编码
     */
	@TableField("employee_code")
	private String employeeCode;
    /**
     * 性别
     */
	private String gender;
    /**
     * 年龄
     */
	private Integer age;
    /**
     * 入职时间
     */
	@TableField("entry_date")
	private Date entryDate;
    /**
     * 离职时间
     */
	@TableField("departure_date")
	private Date departureDate;
    /**
     * 是否VIP
     */
	@TableField("if_vip")
	private String ifVip;
    /**
     * 服务年限
     */
	@TableField("service_length")
	private Double serviceLength;
    /**
     * 公司名
     */
	private String company;
    /**
     * 在职状态
     */
	@TableField("inservice_status")
	private String inserviceStatus;
    /**
     * 特殊名单类型
     */
	@TableField("special_list_type")
	private String specialListType;
    /**
     * 是否特殊名单
     */
	@TableField("if_special_list")
	private String ifSpecialList;
    /**
     * 贷款申请流水号
     */
	@TableField("loan_application_no")
	private String loanApplicationNo;
    /**
     * 贷款申请日期
     */
	@TableField("loan_application_date")
	private Date loanApplicationDate;
    /**
     * 当前贷款状态
     */
	@TableField("loan_status")
	private String loanStatus;
    /**
     * 剩余未还本金
     */
	@TableField("loan_balance")
	private Double loanBalance;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public EmployeeCreditSingle() {
	}

	public EmployeeCreditSingle(Long id, String employeeName, String idNo, String mobileNo, String employeeCode,
								String gender, Integer age, Date entryDate, Date departureDate, String ifVip,
								Double serviceLength, String company, String inserviceStatus, String specialListType,
								String ifSpecialList, String loanApplicationNo, Date loanApplicationDate,
								String loanStatus, Double loanBalance, Date createTime) {
		this.id = id;
		this.employeeName = employeeName;
		this.idNo = idNo;
		this.mobileNo = mobileNo;
		this.employeeCode = employeeCode;
		this.gender = gender;
		this.age = age;
		this.entryDate = entryDate;
		this.departureDate = departureDate;
		this.ifVip = ifVip;
		this.serviceLength = serviceLength;
		this.company = company;
		this.inserviceStatus = inserviceStatus;
		this.specialListType = specialListType;
		this.ifSpecialList = ifSpecialList;
		this.loanApplicationNo = loanApplicationNo;
		this.loanApplicationDate = loanApplicationDate;
		this.loanStatus = loanStatus;
		this.loanBalance = loanBalance;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getIfVip() {
		return ifVip;
	}

	public void setIfVip(String ifVip) {
		this.ifVip = ifVip;
	}

	public Double getServiceLength() {
		return serviceLength;
	}

	public void setServiceLength(Double serviceLength) {
		this.serviceLength = serviceLength;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getInserviceStatus() {
		return inserviceStatus;
	}

	public void setInserviceStatus(String inserviceStatus) {
		this.inserviceStatus = inserviceStatus;
	}

	public String getSpecialListType() {
		return specialListType;
	}

	public void setSpecialListType(String specialListType) {
		this.specialListType = specialListType;
	}

	public String getIfSpecialList() {
		return ifSpecialList;
	}

	public void setIfSpecialList(String ifSpecialList) {
		this.ifSpecialList = ifSpecialList;
	}

	public String getLoanApplicationNo() {
		return loanApplicationNo;
	}

	public void setLoanApplicationNo(String loanApplicationNo) {
		this.loanApplicationNo = loanApplicationNo;
	}

	public Date getLoanApplicationDate() {
		return loanApplicationDate;
	}

	public void setLoanApplicationDate(Date loanApplicationDate) {
		this.loanApplicationDate = loanApplicationDate;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Double getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(Double loanBalance) {
		this.loanBalance = loanBalance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
