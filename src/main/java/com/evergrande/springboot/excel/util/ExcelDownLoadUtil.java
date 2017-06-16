package com.evergrande.springboot.excel.util;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Excel 下载工具类,提供原生的Servlet下载环境
 * @author lisuo
 *
 */
public class ExcelDownLoadUtil {

	/** 文件后缀 */
	public static final String FILE_SUFFIX = ".xlsx";

	/** 文件编码 */
	public static final String UTF8 = "UTF-8";
	/** 用户浏览器关键字：IE */
	private static final String USER_AGENT_IE = "MSIE";

	private static final String CONTENT_TYPE = "application/vnd.ms-excel";
	
	/**
	 * 下载Excel,如果Workbook为空，执行alert(emptyMessage);
	 * @param workbook POI Workbook
	 * @param excelName Excel名字（不需要后缀，支持中文处理）
	 * @param 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws IOException
	 */
	public static void downLoadExcel(Workbook workbook,String excelName,String emptyMessage,HttpServletRequest request,HttpServletResponse response)throws IOException{
		if (workbook != null) {
			String excelFileName = encodeDownloadFileName(request, excelName + FILE_SUFFIX);
			response.setContentType(CONTENT_TYPE);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + excelFileName + "\";target=_blank");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print("<script language='javascript'>alert('"+emptyMessage+"');</script>");
			writer.flush();
			writer.close();
		}
	}
	
	/**
	 * 根据不同的浏览器设置下载文件名称的编码
	 *
	 * @param request
	 * @param fileName
	 * @return 文件名称
	 */
	public static String encodeDownloadFileName(HttpServletRequest request, String fileName) {
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf(USER_AGENT_IE) > 0) {// 用户在用IE
			try {
				return URLEncoder.encode(fileName, UTF8);
			} catch (UnsupportedEncodingException ignore) {}
		} else {
			try {
				return new String(fileName.getBytes(UTF8), "ISO-8859-1");
			} catch (UnsupportedEncodingException ignore) {
			}
		}
		return fileName;
	}
	
}
