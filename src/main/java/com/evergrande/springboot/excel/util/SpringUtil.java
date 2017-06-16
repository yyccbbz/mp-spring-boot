package com.evergrande.springboot.excel.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring工具类,用于静态获取Spring容器中的Bean
 * @author lisuo
 *
 */
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext ctx;
	
	/**
	 * 获取bean
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String id){
		return (T) ctx.getBean(id);
	}
	
	/**
	 * 按类型获取bean
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz){
		return ctx.getBean(clazz);
	}
	
	/**
	 * 按类型及ID获取bean
	 * @param id
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String id, Class<T> clazz){
		return ctx.getBean(id, clazz);
	}
	
	/**
	 * 
	 * 检查SpringUtil是否已完成初始化
	 * @param
	 * @return boolean
	 * @throws
	 */
	public static boolean isInited(){
		return null!=ctx;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
}
