package com.evergrande.springboot.excel.parsing;


import com.evergrande.springboot.excel.config.FieldValue;

/**
 * CellValue转换器,用于解析cell值的规则
 * @author lisuo
 *
 */
public interface CellValueConverter {
	
	/**
	 * 操作类型，导入或导出
	 */
	enum Type {
		EXPORT, IMPORT
	}
	
	/**
	 * 转换cell的值
	 * @param bean Excel配置的JavaBean对象
	 * @param value Excel原值
	 * @param fieldValue FieldValue信息
	 * @param type 导入或导出
	 * @param rowNum 行号
	 * @return 解析结果对应的value
	 * @throws Exception
	 */
	public Object convert(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum) throws Exception;
}
