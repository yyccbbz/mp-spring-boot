package com.evergrande.springboot.excel.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel 操作工具类
 * @author lisuo
 *
 */
public abstract class ExcelUtil {
	
	/**
	 * 读取Excel,支持任何不规则的Excel文件,
	 * 外层List表示所有的数据行，内层List表示每行中的cell单元数据位置
	 * 假设获取一个Excel第三行第二个单元格的数据，例子代码：
	 * FileInputStream excelStream = new FileInputStream(path);
	 * List<List<Object>> list = ExcelUtil.readExcel(excelStream);
	 * System.out.println(list.get(2).get(1));//第三行第二列,索引行位置是2,列的索引位置是1
	 * @param excelStream Excel文件流
	 * @param sheetIndex Excel-Sheet 的索引
	 * @return List<List<Object>> 
	 * @throws Exception
	 */
	public static List<List<Object>> readExcel(InputStream excelStream,int sheetIndex)throws Exception {
		List<List<Object>> datas = new ArrayList<List<Object>>();
		Workbook workbook = WorkbookFactory.create(excelStream);
		//只读取第一个sheet
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rows; i++) {
			Row row = sheet.getRow(i);
			short cellNum = row.getLastCellNum();
			List<Object> item = new ArrayList<Object>(cellNum);
			for(int j=0;j<cellNum;j++){
				Cell cell = row.getCell(j);
				Object value = ExcelUtil.getCellValue(cell);
				item.add(value);
			}
			datas.add(item);
		}
		return datas;
	}
	
	/**
	 * 读取Excel,支持任何不规则的Excel文件,默认读取第一个sheet页
	 * 外层List表示所有的数据行，内层List表示每行中的cell单元数据位置
	 * 假设获取一个Excel第三行第二个单元格的数据，例子代码：
	 * FileInputStream excelStream = new FileInputStream(path);
	 * List<List<Object>> list = ExcelUtil.readExcel(excelStream);
	 * System.out.println(list.get(2).get(1));//第三行第二列,索引行位置是2,列的索引位置是1
	 * @param excelStream Excel文件流
	 * @return List<List<Object>> 
	 * @throws Exception
	 */
	public static List<List<Object>> readExcel(InputStream excelStream)throws Exception {
		return readExcel(excelStream,0);
	}
	
	/**
	 * 设置Cell单元的值
	 * 
	 * @param cell
	 * @param value
	 */
	public static void setCellValue(Cell cell, Object value) {
		if (value != null) {
			if (value instanceof String) {
				cell.setCellValue((String) value);
			} else if (value instanceof Number) {
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
			} else if (value instanceof Boolean) {
				cell.setCellValue((Boolean) value);
			} else if (value instanceof Date) {
				cell.setCellValue((Date) value);
			} else {
				cell.setCellValue(value.toString());
			}
		}
	}
	
	/**
	 * 获取cell值
	 * 
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		if (null != cell) {
			switch (cell.getCellType()) {
			// 空白
			case Cell.CELL_TYPE_BLANK:
				break;
			// Boolean
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			// 错误格式
			case Cell.CELL_TYPE_ERROR:
				break;
			// 公式
			case Cell.CELL_TYPE_FORMULA:
				Workbook wb = cell.getSheet().getWorkbook();
				CreationHelper crateHelper = wb.getCreationHelper();
				FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
				value = getCellValue(evaluator.evaluateInCell(cell));
				break;
			// 数值
			case Cell.CELL_TYPE_NUMERIC:
				// 处理日期格式
				if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
					value = cell.getDateCellValue();
				} else {
					value = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			default:
				value = null;
			}
		}
		return value;
	}
}
