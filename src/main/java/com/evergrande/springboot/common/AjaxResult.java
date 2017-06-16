package com.evergrande.springboot.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * Ajax响应结果
 * @author CuiCan
 *
 */
@SuppressWarnings("serial")
public class AjaxResult implements Serializable {
	
	/** 失败 */
	public static final int CODE_FAILURE = 0;
	/** 成功 */
	public static final int CODE_SUCCESS = 1;
	/** 未登录或session过期 */
	public static final int CODE_INVALID = 2;
	/** 无权限 */
	public static final int CODE_DENIED = 3;
	
	/** 0:失败,1:成功,2:未登录或session过期,3:无权限 */
	private int code;
	/** 响应结果描述 */
	private String msg;
	/** 其它数据信息 */
	private Object obj;

	public AjaxResult() {
	}

	/**
	 * 
	 * @param code 结果状态码 ,看该类常量
	 * @param msg 响应结果描述
	 * @param obj 其它数据信息
	 */
	public AjaxResult(int code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	/**
	 * 操作成功
	 * @param message 提示信息
	 * @return AjaxResult
	 */
	public static AjaxResult success(String message) {
		return new AjaxResult(CODE_SUCCESS, message, null);
	}
	
	/**
	 * 操作成功
	 * @param message 提示信息
	 * @param obj 其他数据信息
	 * @return AjaxResult
	 */
	public static AjaxResult success(String message,Object obj) {
		return new AjaxResult(CODE_SUCCESS, message, obj);
	}

	/**
	 * 操作失败
	 * @param message 提示信息
	 * @return AjaxResult
	 */
	public static AjaxResult failure(String message) {
		return new AjaxResult(CODE_FAILURE, message, null);
	}
	
	/**
	 * 操作失败
	 * @param message 提示信息
	 * @param obj 其他数据信息
	 * @return AjaxResult
	 */
	public static AjaxResult failure(String message,Object obj) {
		return new AjaxResult(CODE_FAILURE, message, obj);
	}
	
	/**
	 * 未登录或session过期
	 * @param message 提示信息
	 * @return AjaxResult
	 */
	public static AjaxResult invalid(String message) {
		return new AjaxResult(CODE_INVALID, message, null);
	}
	
	/**
	 * 未登录或session过期
	 * @param message 提示信息
	 * @param obj 其他数据信息
	 * @return AjaxResult
	 */
	public static AjaxResult invalid(String message,Object obj) {
		return new AjaxResult(CODE_INVALID, message, obj);
	}
	
	/**
	 * 无权限
	 * @param message 提示信息
	 * @return AjaxResult
	 */
	public static AjaxResult denied(String message) {
		return new AjaxResult(CODE_DENIED, message, null);
	}
	
	/**
	 * 无权限
	 * @param message 提示信息
	 * @param obj 其他数据信息
	 * @return AjaxResult
	 */
	public static AjaxResult denied(String message,Object obj) {
		return new AjaxResult(CODE_DENIED, message, obj);
	}

	/**
	 * 结果状态码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置结果状态码
	 * 
	 * @param code 结果状态码,参考本类状态码常量
	 */
	public AjaxResult setCode(int code) {
		this.code = code;
		return this;
	}

	/**
	 * 响应结果描述
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置响应结果描述
	 * @param msg  响应结果描述
	 */
	public AjaxResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * 其它数据信息
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * 设置其它数据信息
	 * 
	 * @param obj 其它数据信息
	 */
	public AjaxResult setObj(Object obj) {
		this.obj = obj;
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
	}

}
