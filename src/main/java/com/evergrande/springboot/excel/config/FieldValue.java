package com.evergrande.springboot.excel.config;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Excel字段定义
 * 
 * @author lisuo
 *
 */
public class FieldValue {
	
	//导入导出都有效
	/** 属性名称,必须 */
	private String name;
	/** 标题,必须 */
	private String title;
	/** 日期pattern,如果设置的类型不是date,注册时,会抛出异常 */
	private String pattern;
	/** 表达式,例如(1:男,2:女)表示,值为1,取 (男)作为value ,2则取 (女)作为value */
	private String format;
	/** 解析Excel值接口定义：自定义实现(全类名) */
	private String cellValueConverterName;
	
	//导入Excel时生效
	/** 是否可以为null */
	private boolean isNull = true;
	/** 正则表达式,导入有效 */
	private String regex;
	/** 正则表达式不通过时,错误提示信息 */
	private String regexErrMsg;
	
	//导出时生效
	/** cell的宽度,此属性不受enableStyle影响,如自己把数值写的过大,看org.apache.poi.ss.usermodel.Sheet.setColumnWidth(int, int)解决 */
	private Integer columnWidth;
	/** cell对其方式:支持,center,left,right */
	private Short align ;
	/** 标题cell背景色:看org.apache.poi.ss.usermodel.IndexedColors 可用颜色*/
	private Short titleBgColor;
	/** 标题cell字体色:看org.apache.poi.ss.usermodel.IndexedColors 可用颜色*/
	private Short titleFountColor;
	/** cell 样式是否与标题样式一致 */
	private boolean uniformStyle = false;
	
	/** DecimalFormat pattern 只对Number类型有效 */
	private String decimalFormatPattern;
	/** DecimalFormat实例,不可配置,它的创建规则基于decimalFormatPattern属性 */
	private DecimalFormat decimalFormat;
	/** DecimalFormat实例,RoundingMode ,当处理字符时,假设保留2位小数,那么遇到3位甚至更多的位数如何处理？通过该配置可以指定处理方式,默认向下取整 */
	private RoundingMode roundingMode = RoundingMode.DOWN;
	/** 当值为空时,字段的默认值 */
	private String defaultValue;
	
	
	/*
	 * 其他配置项:
	 * 与Excel导入导出无关,或许在自定义转换器时利用该参数可以更灵活配置一些其他信息,
	 * 比如一个转换器映射多个自动,配置该参数会更加灵活,可以配置成JSON等类型数据,具体根据自己的需求
	 */
	private String otherConfig;
	
	
	public FieldValue() {
	}

	public FieldValue(String name, String title, String pattern, String format) {
		this.name = name;
		this.title = title;
		this.pattern = pattern;
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getRegexErrMsg() {
		return regexErrMsg;
	}

	public void setRegexErrMsg(String regexErrMsg) {
		this.regexErrMsg = regexErrMsg;
	}

	public String getCellValueConverterName() {
		return cellValueConverterName;
	}

	public void setCellValueConverterName(String cellValueConverterName) {
		this.cellValueConverterName = cellValueConverterName;
	}

	public Short getAlign() {
		return align;
	}

	public void setAlign(Short align) {
		this.align = align;
	}

	public Integer getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(Integer columnWidth) {
		this.columnWidth = columnWidth;
	}

	public Short getTitleBgColor() {
		return titleBgColor;
	}

	public void setTitleBgColor(Short titleBgColor) {
		this.titleBgColor = titleBgColor;
	}

	public Short getTitleFountColor() {
		return titleFountColor;
	}

	public void setTitleFountColor(Short titleFountColor) {
		this.titleFountColor = titleFountColor;
	}

	public boolean isUniformStyle() {
		return uniformStyle;
	}

	public void setUniformStyle(boolean uniformStyle) {
		this.uniformStyle = uniformStyle;
	}

	public String getOtherConfig() {
		return otherConfig;
	}

	public void setOtherConfig(String otherConfig) {
		this.otherConfig = otherConfig;
	}

	public String getDecimalFormatPattern() {
		return decimalFormatPattern;
	}

	public void setDecimalFormatPattern(String decimalFormatPattern) {
		this.decimalFormatPattern = decimalFormatPattern;
	}

	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public RoundingMode getRoundingMode() {
		return roundingMode;
	}

	public void setRoundingMode(RoundingMode roundingMode) {
		this.roundingMode = roundingMode;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	

}
