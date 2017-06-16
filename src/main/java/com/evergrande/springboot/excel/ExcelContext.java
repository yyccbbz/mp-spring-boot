package com.evergrande.springboot.excel;


import com.evergrande.springboot.excel.config.FieldValue;
import com.evergrande.springboot.excel.parsing.ExcelExport;
import com.evergrande.springboot.excel.result.ExcelExportResult;
import com.evergrande.springboot.excel.util.ReflectUtil;
import com.evergrande.springboot.excel.config.ExcelDefinition;
import com.evergrande.springboot.excel.exception.ExcelException;
import com.evergrande.springboot.excel.parsing.ExcelHeader;
import com.evergrande.springboot.excel.parsing.ExcelImport;
import com.evergrande.springboot.excel.result.ExcelImportResult;
import com.evergrande.springboot.excel.xml.XMLExcelDefinitionReader;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel上下文支持,只需指定location配置文件路径,即可使用
 *
 * @author lisuo
 */
public class ExcelContext {

    private ExcelDefinitionReader definitionReader;

    /**
     * 用于缓存Excel配置
     */
    private Map<String, List<FieldValue>> fieldValueMap = new HashMap<String, List<FieldValue>>();

    /**
     * 导出
     */
    private ExcelExport excelExport;
    /**
     * 导入
     */
    private ExcelImport excelImport;

    /**
     * @param location 配置文件类路径
     */
    public ExcelContext(String location) {
        try {
            //默认使用XMLExcelDefinitionReader
            definitionReader = new XMLExcelDefinitionReader(location);
            excelExport = new ExcelExport(definitionReader);
            excelImport = new ExcelImport(definitionReader);
        } catch (ExcelException e) {
            throw e;
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * @param definitionReader 自定义实现ExcelDefinitionReader
     */
    public ExcelContext(ExcelDefinitionReader definitionReader) {
        try {
            if (definitionReader == null) {
                throw new ExcelException("definitionReader 不能为空");
            }
            if (MapUtils.isEmpty(this.definitionReader.getRegistry())) {
                throw new ExcelException("definitionReader Registry 不能为空");
            }
            this.definitionReader = definitionReader;
            excelExport = new ExcelExport(definitionReader);
            excelImport = new ExcelImport(definitionReader);
        } catch (ExcelException e) {
            throw e;
        } catch (Exception e) {
            throw new ExcelException(e);
        }

    }

    /**
     * 创建Excel
     *
     * @param id    配置ID
     * @param beans 配置class对应的List
     * @return Workbook
     * @throws Exception
     */
    public Workbook createExcel(String id, List<?> beans) throws Exception {
        return createExcel(id, beans, null, null);
    }

    /**
     * 创建Excel部分信息
     *
     * @param id    配置ID
     * @param beans 配置class对应的List
     * @return Workbook
     * @throws Exception
     */
    public ExcelExportResult createExcelForPart(String id, List<?> beans) throws Exception {
        return createExcelForPart(id, beans, null, null);
    }

    /**
     * 创建Excel
     *
     * @param id     配置ID
     * @param beans  配置class对应的List
     * @param header 导出之前,在标题前面做出一些额外的操作，比如增加文档描述等,可以为null
     * @return Workbook
     * @throws Exception
     */
    public Workbook createExcel(String id, List<?> beans, ExcelHeader header) throws Exception {
        return createExcel(id, beans, header, null);
    }

    /**
     * 创建Excel部分信息
     *
     * @param id     配置ID
     * @param beans  配置class对应的List
     * @param header 导出之前,在标题前面做出一些额外的操作，比如增加文档描述等,可以为null
     * @return Workbook
     * @throws Exception
     */
    public ExcelExportResult createExcelForPart(String id, List<?> beans, ExcelHeader header) throws Exception {
        return createExcelForPart(id, beans, header, null);
    }

    /**
     * 创建Excel
     *
     * @param id     配置ID
     * @param beans  配置class对应的List
     * @param header 导出之前,在标题前面做出一些额外的操作,比如增加文档描述等,可以为null
     * @param fields 指定Excel导出的字段(bean对应的字段名称),可以为null
     * @return Workbook
     * @throws Exception
     */
    public Workbook createExcel(String id, List<?> beans, ExcelHeader header, List<String> fields) throws Exception {
        return excelExport.createExcel(id, beans, header, fields).build();
    }

    /**
     * 创建Excel部分信息
     *
     * @param id     配置ID
     * @param beans  配置class对应的List
     * @param header 导出之前,在标题前面做出一些额外的操作,比如增加文档描述等,可以为null
     * @param fields 指定Excel导出的字段(bean对应的字段名称),可以为null
     * @return Workbook
     * @throws Exception
     */
    public ExcelExportResult createExcelForPart(String id, List<?> beans, ExcelHeader header, List<String> fields) throws Exception {
        return excelExport.createExcel(id, beans, header, fields);
    }

    /**
     * 创建Excel,模板信息
     *
     * @param id     ExcelXML配置Bean的ID
     * @param header Excel头信息(在标题之前)
     * @param fields 指定导出的字段
     * @return
     * @throws Exception
     */
    public Workbook createExcelTemplate(String id, ExcelHeader header, List<String> fields) throws Exception {
        return excelExport.createExcelTemplate(id, header, fields);
    }

    /***
     * 读取Excel信息
     * @param id 配置ID
     * @param excelStream Excel文件流
     * @return ExcelImportResult
     * @throws Exception
     */
    public ExcelImportResult readExcel(String id, InputStream excelStream) throws Exception {
        return excelImport.readExcel(id, 0, excelStream, null, false);
    }

    /***
     * 读取Excel信息
     * @param id 配置ID
     * @param excelStream Excel文件流
     * @param sheetIndex Sheet索引位
     * @return ExcelImportResult
     * @throws Exception
     */
    public ExcelImportResult readExcel(String id, InputStream excelStream, int sheetIndex) throws Exception {
        return excelImport.readExcel(id, 0, excelStream, sheetIndex, false);
    }

    /***
     * 读取Excel信息
     * @param id 配置ID
     * @param titleIndex 标题索引,从0开始
     * @param excelStream Excel文件流
     * @return ExcelImportResult
     * @throws Exception
     */
    public ExcelImportResult readExcel(String id, int titleIndex, InputStream excelStream) throws Exception {
        return excelImport.readExcel(id, titleIndex, excelStream, null, false);
    }

    /***
     * 读取Excel信息
     * @param id 配置ID
     * @param titleIndex 标题索引,从0开始
     * @param excelStream Excel文件流
     * @param multivalidate 是否逐条校验，默认单行出错立即抛出ExcelException，为true时为批量校验,可通过ExcelImportResult.hasErrors,和getErrors获取具体错误信息
     * @return ExcelImportResult
     * @throws Exception
     */
    public ExcelImportResult readExcel(String id, int titleIndex, InputStream excelStream, boolean multivalidate) throws Exception {
        return excelImport.readExcel(id, titleIndex, excelStream, null, multivalidate);
    }

    /***
     * 读取Excel信息
     * @param id 配置ID
     * @param titleIndex 标题索引,从0开始
     * @param excelStream Excel文件流
     * @param sheetIndex Sheet索引位
     * @return ExcelImportResult
     * @throws Exception
     */
    public ExcelImportResult readExcel(String id, int titleIndex, InputStream excelStream, int sheetIndex) throws Exception {
        return excelImport.readExcel(id, titleIndex, excelStream, sheetIndex, false);
    }

    /***
     * 读取Excel信息
     * @param id 配置ID
     * @param titleIndex 标题索引,从0开始
     * @param excelStream Excel文件流
     * @param sheetIndex Sheet索引位
     * @param multivalidate 是否逐条校验，默认单行出错立即抛出ExcelException，为true时为批量校验,可通过ExcelImportResult.hasErrors,和getErrors获取具体错误信息
     * @return ExcelImportResult
     * @throws Exception
     */
    public ExcelImportResult readExcel(String id, int titleIndex, InputStream excelStream, int sheetIndex, boolean multivalidate) throws Exception {
        return excelImport.readExcel(id, titleIndex, excelStream, sheetIndex, multivalidate);
    }

    /**
     * 获取Excel 配置文件中的字段
     *
     * @param key
     * @return
     */
    public List<FieldValue> getFieldValues(String key) {
        List<FieldValue> list = fieldValueMap.get(key);
        if (list == null) {
            ExcelDefinition def = definitionReader.getRegistry().get(key);
            if (def == null) {
                throw new ExcelException("没有找到[" + key + "]的配置信息");
            }
            //使用copy方式,避免使用者修改原生的配置信息
            List<FieldValue> fieldValues = def.getFieldValues();
            list = new ArrayList<FieldValue>(fieldValues.size());
            for (FieldValue fieldValue : fieldValues) {
                FieldValue val = new FieldValue();
                ReflectUtil.copyProps(fieldValue, val);
                list.add(val);
            }
            fieldValueMap.put(key, list);
        }
        return list;
    }

}
