package com.evergrande.springboot.excel.xml;


import com.evergrande.springboot.excel.ExcelDefinitionReader;
import com.evergrande.springboot.excel.config.FieldValue;
import com.evergrande.springboot.excel.parsing.CellValueConverter;
import com.evergrande.springboot.excel.util.ReflectUtil;
import com.evergrande.springboot.excel.config.ExcelDefinition;
import com.evergrande.springboot.excel.exception.ExcelException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel XML定义读取注册
 * @author lisuo
 *
 */
public class XMLExcelDefinitionReader implements ExcelDefinitionReader {
	
	/** 注册信息 */
	private Map<String, ExcelDefinition> registry;
	
	/** 配置文件路径 */
	private String location;
	
	/**
	 * 
	 * @param location xml配置路径
	 * @throws Exception
	 */
	public XMLExcelDefinitionReader(String location) throws Exception {
		this.location = location;
		registry = new HashMap<String, ExcelDefinition>();
		Resource resource = new ClassPathResource(location);
		loadExcelDefinitions(resource.getInputStream());
	}
	
	/**
	 * 加载解析配置文件信息
	 * @param inputStream
	 * @throws Exception
	 */
	protected void loadExcelDefinitions(InputStream inputStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream);
		registerExcelDefinitions(doc);
		inputStream.close();
	}

	/**
	 * 注册Excel定义信息
	 * @param doc
	 */
	protected void registerExcelDefinitions(Document doc) {
		Element root = doc.getDocumentElement();
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				processExcelDefinition(ele);
			}
		}
	}

	/**
	 * 解析和校验Excel定义
	 * @param ele
	 */
	protected void processExcelDefinition(Element ele) {
		ExcelDefinition excelDefinition = new ExcelDefinition();
		String id = ele.getAttribute("id");
		Validate.notNull(id, "Excel 配置文件[" + location + "] , id为 [ null ] ");
		if (registry.containsKey(id)) {
			throw new ExcelException("Excel 配置文件[" + location + "] , id为 [" + id + "] 的不止一个");
		}
		excelDefinition.setId(id);
		String className = ele.getAttribute("class");
		Validate.notNull(className, "Excel 配置文件[" + location + "] , id为 [" + id + "] 的 class 为 [ null ]");
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ExcelException("Excel 配置文件[" + location + "] , id为 [" + id + "] 的 class 为 [" + className + "] 类不存在 ");
		}
		excelDefinition.setClassName(className);
		excelDefinition.setClazz(clazz);
		if(StringUtils.isNotBlank(ele.getAttribute("defaultColumnWidth"))){
			try{
				excelDefinition.setDefaultColumnWidth(Integer.parseInt(ele.getAttribute("defaultColumnWidth")));
			}catch(NumberFormatException e){
				throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
				+ " ] 的 defaultColumnWidth 属性不能为 [ "+ele.getAttribute("defaultColumnWidth")+" ],只能为int类型");
			}
		}
		if(StringUtils.isNotBlank(ele.getAttribute("sheetname"))){
			excelDefinition.setSheetname(ele.getAttribute("sheetname"));
		}
		if(StringUtils.isNotBlank(ele.getAttribute("enableStyle"))){
			excelDefinition.setEnableStyle(Boolean.parseBoolean(ele.getAttribute("enableStyle")));
		}
		//默认对齐方式
		String defaultAlign = ele.getAttribute("defaultAlign");
		if(StringUtils.isNotBlank(defaultAlign)){
			try{
				//获取cell对齐方式的常量值
				short constValue = ReflectUtil.getConstValue(CellStyle.class, "ALIGN_"+defaultAlign.toUpperCase());
				excelDefinition.setDefaultAlign(constValue);
			}catch(Exception e){
				throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
				+ " ] 的 defaultAlign 属性不能为 [ "+defaultAlign+" ],目前支持的left,center,right");
			}
		}
		//Sheet Index
		if(StringUtils.isNotBlank(ele.getAttribute("sheetIndex"))){
			try{
				int sheetIndex = Integer.parseInt(ele.getAttribute("sheetIndex"));
				if(sheetIndex<0){
					throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
					+ " ] 的 sheetIndex 属性不能为 [ "+ele.getAttribute("sheetIndex")+" ],索引位置至少从0开始");
				}
				excelDefinition.setSheetIndex(sheetIndex);
			}catch(NumberFormatException e){
				throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
				+ " ] 的 sheetIndex 属性不能为 [ "+ele.getAttribute("sheetIndex")+" ],只能为int类型");
			}
		}
		processField(ele, excelDefinition);
		registry.put(id, excelDefinition);
	}
	
	/**
	 * 解析和校验Field属性定义
	 * @param ele
	 * @param excelDefinition
	 */
	protected void processField(Element ele, ExcelDefinition excelDefinition) {
		NodeList fieldNode = ele.getElementsByTagName("field");
		if (fieldNode != null) {
			for (int i = 0; i < fieldNode.getLength(); i++) {
				Node node = fieldNode.item(i);
				if (node instanceof Element) {
					FieldValue fieldValue = new FieldValue();
					Element fieldEle = (Element) node;
					String name = fieldEle.getAttribute("name");
					Validate.isTrue(StringUtils.isNotBlank(name), "Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
							+ " ] 的 name 属性不能为 [ null ]");
					fieldValue.setName(name);
					String title = fieldEle.getAttribute("title");
					Assert.isTrue(StringUtils.isNotBlank(title), "Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
							+ " ] 的 title 属性不能为 [ null ]");
					fieldValue.setTitle(title);
					String pattern = fieldEle.getAttribute("pattern");
					if(StringUtils.isNotBlank(pattern)){
						fieldValue.setPattern(pattern);
					}
					String format = fieldEle.getAttribute("format");
					if(StringUtils.isNotBlank(format)){
						fieldValue.setFormat(format);;
					}
					String isNull = fieldEle.getAttribute("isNull");
					if(StringUtils.isNotBlank(isNull)){
						fieldValue.setNull(Boolean.parseBoolean(isNull));
					}
					String regex = fieldEle.getAttribute("regex");
					if(StringUtils.isNotBlank(regex)){
						fieldValue.setRegex(regex);
					}
					String regexErrMsg = fieldEle.getAttribute("regexErrMsg");
					if(StringUtils.isNotBlank(regexErrMsg)){
						fieldValue.setRegexErrMsg(regexErrMsg);
					}
					//对齐方式
					String align = fieldEle.getAttribute("align");
					if(StringUtils.isNotBlank(align)){
						try{
							//获取cell对齐方式的常量值
							short constValue = ReflectUtil.getConstValue(CellStyle.class, "ALIGN_"+align.toUpperCase());
							fieldValue.setAlign(constValue);
						}catch(Exception e){
							throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
							+ " ] 的 align 属性不能为 [ "+align+" ],目前支持的left,center,right");
						}
					}
					//cell 宽度
					String columnWidth = fieldEle.getAttribute("columnWidth");
					if(StringUtils.isNotBlank(columnWidth)){
						try{
							int intVal = Integer.parseInt(columnWidth);
							fieldValue.setColumnWidth(intVal);
						}catch(NumberFormatException e){
							throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
							+ " ] 的 columnWidth 属性 [ "+columnWidth+" ] 不是一个合法的数值");
						}
					}
					//cell标题背景色
					String titleBgColor = fieldEle.getAttribute("titleBgColor");
					if(StringUtils.isNotBlank(titleBgColor)){
						try{
							//获取cell对齐方式的常量值
							IndexedColors color = ReflectUtil.getConstValue(IndexedColors.class,titleBgColor.toUpperCase());
							fieldValue.setTitleBgColor(color.index);
						}catch(Exception e){
							throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
							+ " ] 的 titleBgColor 属性不能为 [ "+titleBgColor+" ],具体看[org.apache.poi.ss.usermodel.IndexedColors]支持的颜色");
						}
					}
					//cell标题字体颜色
					String titleFountColor = fieldEle.getAttribute("titleFountColor");
					if(StringUtils.isNotBlank(titleFountColor)){
						try{
							//获取cell对齐方式的常量值
							IndexedColors color = ReflectUtil.getConstValue(IndexedColors.class,titleFountColor.toUpperCase());
							if(color==null){
								throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
								+ " ] 的 titleFountColor 属性不能为 [ "+titleFountColor+" ],具体看[org.apache.poi.ss.usermodel.IndexedColors]支持的颜色");
							}
							fieldValue.setTitleFountColor(color.index);
						}catch(Exception e){
							throw new ExcelException(e);
						}
					}
					//cell 样式是否与标题样式一致
					String uniformStyle = fieldEle.getAttribute("uniformStyle");
					if(StringUtils.isNotBlank(uniformStyle)){
						fieldValue.setUniformStyle(Boolean.parseBoolean(uniformStyle));
					}
					
					//解析自定义转换器
					String cellValueConverterName = fieldEle.getAttribute("cellValueConverter");
					if(StringUtils.isNotBlank(cellValueConverterName)){
						try {
							Class<?> clazz = Class.forName(cellValueConverterName);
							if(!CellValueConverter.class.isAssignableFrom(clazz)){
								throw new ExcelException("配置的："+cellValueConverterName+"错误,不是一个标准的["+CellValueConverter.class.getName()+"]实现");
							}
							fieldValue.setCellValueConverterName(cellValueConverterName);
						} catch (ClassNotFoundException e) {
							throw new ExcelException("无法找到定义的解析器：["+cellValueConverterName+"]"+"请检查配置信息");
						}
					}
					
					//roundingMode 解析
					String roundingMode = fieldEle.getAttribute("roundingMode");
					if(StringUtils.isNotBlank(roundingMode)){
						try{
							//获取roundingMode常量值
							RoundingMode mode =  ReflectUtil.getConstValue(RoundingMode.class,roundingMode.toUpperCase());
							if(mode == null){
								throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
								+ " ] 的 roundingMode 属性不能为 [ "+roundingMode+" ],具体看[java.math.RoundingMode]支持的值");
							}
							fieldValue.setRoundingMode(mode);
						}catch(Exception e){
							throw new ExcelException(e);
						}
					}
					
					//解析decimalFormat
					String decimalFormatPattern = fieldEle.getAttribute("decimalFormatPattern");
					if(StringUtils.isNotBlank(decimalFormatPattern)){
						try{
							fieldValue.setDecimalFormatPattern(decimalFormatPattern);
							fieldValue.setDecimalFormat(new DecimalFormat(decimalFormatPattern));
							fieldValue.getDecimalFormat().setRoundingMode(fieldValue.getRoundingMode());
						}catch(Exception e){
							throw new ExcelException("Excel 配置文件[" + location + "] , id为 [ " + excelDefinition.getId()
							+ " ] 的 decimalFormatPattern 属性不能为 [ "+decimalFormatPattern+" ],请配置标准的JAVA格式");
						}
					}

					//解析其他配置项
					String otherConfig = fieldEle.getAttribute("otherConfig");
					if(StringUtils.isNotBlank(otherConfig)){
						fieldValue.setOtherConfig(otherConfig);
					}
					
					//解析,值为空时,字段的默认值
					String defaultValue = fieldEle.getAttribute("defaultValue");
					if(StringUtils.isNotBlank(defaultValue)){
						fieldValue.setDefaultValue(defaultValue);
					}
					
					excelDefinition.getFieldValues().add(fieldValue);
				}
			}
		}
	}

	/**
	 * @return 不可被修改的注册信息
	 */
	@Override
	public Map<String, ExcelDefinition> getRegistry() {
		return Collections.unmodifiableMap(registry);
	}
	
	public String getLocation() {
		return location;
	}
}
