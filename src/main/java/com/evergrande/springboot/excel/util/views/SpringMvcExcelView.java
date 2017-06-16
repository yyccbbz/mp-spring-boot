package com.evergrande.springboot.excel.util.views;

import com.evergrande.springboot.excel.util.ExcelDownLoadUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * SpringMvc Excel下载视图
 * 使用案例,标准的Spring自定义视图，该视图需要两个参数
 * Excel.excelName String
 * Excel.workbook POI Workbook
 * 
 * 示例代码：
 * public ModelAndView downloadExcel(){
 * 		//1.执行你的业务逻辑获取数据，使用ExcelContent生成Workbook
 * 		Workbook workbook = ...;
 * 		//2.跳转到Excel下载视图
 * 		ModelAndView view = new ModelAndView("springMvcExcelView");
 * 		view.addObject(SpringMvcExcelView.EXCEL_NAME,"测试Excel下载");
 * 		view.addObject(SpringMvcExcelView.EXCEL_WORKBOOK,workbook);
 * 		view.addObject(SpringMvcExcelView.EXCEL_EMPTY_MESSAGE,"XXX没有相关数据可以导出");
 * 		return view;
 * }
 * 
 */
public class SpringMvcExcelView extends AbstractView {

	/** Excel 名称 */
	public static final String EXCEL_NAME = "Excel.excelName";

	/** POI Workbook */
	public static final String EXCEL_WORKBOOK = "Excel.workbook";
	
	/** 当没有数据时提示的消息 */
	public static final String EXCEL_EMPTY_MESSAGE = "Excel.emptyMessage";
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Workbook workbook = (Workbook) model.get(EXCEL_WORKBOOK);
		String excelName = MapUtils.getString(model, EXCEL_NAME);
		String emptyMessage = MapUtils.getString(model, EXCEL_EMPTY_MESSAGE);
		if(StringUtils.isBlank(emptyMessage)){
			emptyMessage="没有可以导出的数据";
		}
		ExcelDownLoadUtil.downLoadExcel(workbook, excelName,emptyMessage, request, response);
	}

}