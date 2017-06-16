package com.evergrande.springboot.excel.parsing;

/**
 * Excel 导入时产生的错误消息
 * @author lisuo
 */
public class ExcelError {

	/** 第几行 */
	private int row;
	/** 错误消息 */
	private String errorMsg;
	
	public ExcelError(int row,String errorMsg) {
		this.row = row;
		this.errorMsg = errorMsg;
	}

	public int getRow() {
		return row;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
