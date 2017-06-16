package com.evergrande.springboot.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-10
 */
@TableName("test_user")
public class TestUser implements Serializable {


	private static final long serialVersionUID = -4237708926994823675L;

	/**
     * 主键ID
     */
    @TableId("test_id")
	private Long testId;
    /**
     * 名称
     */
	private String name;
    /**
     * 年龄
     */
	private Integer age;
    /**
     * 测试下划线字段命名类型
     */
	@TableField("test_type")
	private Integer testType;
    /**
     * 日期
     */
	@TableField("test_date")
	private Date testDate;
    /**
     * 测试
     */
	private Long role;
    /**
     * 手机号码
     */
	private String phone;


	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
