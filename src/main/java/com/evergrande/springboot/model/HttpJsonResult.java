package com.evergrande.springboot.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/14
 * @Time: 17:23
 * @Description:
 */
public class HttpJsonResult {

    /*"status":"200","message":"","id":164,"configName":"终审","configType":"1","isInterface":1,"isTask":1,
    "remark":null,
    "details":Object{

       "list":{
            "id":1128,
            "values":

    ...}*/
    private String status;
    private String message;
    private Integer id;
    private String configName;
    private String configType;
    private Integer isInterface;
    private Integer isTask;
    private String remark;
    private HashMap<String, ConfigDetailBody> details;


    private List<Map<String, Object>> values;
    private String errorMessage;


}
