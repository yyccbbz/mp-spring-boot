package com.evergrande.springboot.excel.util;


import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;


/**
 * 反射工具类
 * @author lisuo
 */
public abstract class ReflectUtil {
	
	/**
	 * 获取常量值
	 * @param clazz 常量类
	 * @param constName 常量名称
	 * @return 常量对应的值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getConstValue(Class<?> clazz,String constName){
		Field field = ReflectUtil.getField(clazz, constName);
		if(field!=null){
			field.setAccessible(true);
	    	try {
	    		Object object = field.get(null);
	    		if(object != null){
	    			return (T) object;
	    		}
	    		return null;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	/**
	 * 为String类型的属性做trim
	 * @param model 去除空格的模型(实体类)
	 * @param propNames 去除属性的名称,只有String类型生效
	 */
	public static void trimFields(Object model, String... propNames){
		Assert.notEmpty(propNames, "请指定要去前后空格的属性名");
		for(String propName : propNames){
			Object val = ReflectUtil.getProperty(model, propName);
			if(val instanceof String){
				String valStr = (String)val;
				valStr = valStr.trim();
				ReflectUtil.setProperty(model, propName, valStr);
			}
		}
	}
	

	/**
	 * 获取指定类的所有字段,排除static,final字段
	 * @param clazz 类型
	 * @return List<字段>
	 */
	public static List<Field> getFields(Class<?> clazz){
		List<Field> fieldResult = new ArrayList<Field>();
		while(clazz!=Object.class){
			try {
				Field[] fields = clazz.getDeclaredFields();
				for (Field field:fields) {
					int modifiers = field.getModifiers();
					//过滤static或final字段
					if(Modifier.isStatic(modifiers)||Modifier.isFinal(modifiers)){
						continue;
					}
					fieldResult.add(field);
				}
			} catch (Exception ignore) {}
			clazz = clazz.getSuperclass();
		}
		return fieldResult;
	}
	
	/**
	 * 获取指定类的所有字段名称,排除static,final字段
	 * @param clazz 类型
	 * @return List<字段名称>
	 */
	public static List<String> getFieldNames(Class<?> clazz){
		List<Field> fields = getFields(clazz);
		List<String> fieldNames = new ArrayList<String>(fields.size());
		for(Field field:fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}
	
	/**
	 * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
	 * 如: public EmployeeDao extends BaseDao<Employee, String>
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index){
		Type genType = clazz.getGenericSuperclass();
		
		if(!(genType instanceof ParameterizedType)){
			return Object.class;
		}
		Type [] params = ((ParameterizedType)genType).getActualTypeArguments();
		if(index >= params.length || index < 0){
			return Object.class;
		}
		if(!(params[index] instanceof Class)){
			return Object.class;
		}
		return (Class<?>) params[index];
	}
	
	/**
	 * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型
	 * 如: public EmployeeDao extends BaseDao<Employee, String>
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static<T> Class<T> getSuperGenericType(Class<?> clazz){
		return (Class<T>) getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 拷贝属性
	 *
	 * @param source
	 * @param target
	 * @param ignoreProps 忽略的属性
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static void copyProps(Object source, Object target, String... ignoreProps) {
		if (source instanceof Map) {
			Map sourceMap = (Map) source;
			Set<String> ignorPropsSet = new HashSet<String>();
			if (ArrayUtils.isNotEmpty(ignoreProps)) {
				for (String prop : ignoreProps) {
					ignorPropsSet.add(prop);
				}
			}
			Set<Entry<Object, Object>> entrySet = sourceMap.entrySet();
			for (Entry<Object, Object> e : entrySet) {
				if (ignorPropsSet.isEmpty()) {
					setProperty(target, e.getKey().toString(), e.getValue());
				} else if (!ignorPropsSet.contains(e.getKey())) {
					setProperty(target, e.getKey().toString(), e.getValue());
				}
			}

		} else if (source instanceof List) {
			List sourceList = (List) source;
			if (target instanceof List) {
				List targetList = (List) target;
				for (Object obj : sourceList) {
					targetList.add(obj);
				}
			}

		} else {
			BeanUtils.copyProperties(source, target, ignoreProps);
		}
	}
	
	/**
	 * 拷贝非空属性,指定copy的属性
	 * @param source 资源
	 * @param target 目标
	 * @param incProps 包含的属性
	 */
	public static void copyPropsInc(Object source, Object target, String...incProps) {
		if(ArrayUtils.isNotEmpty(incProps)){
			Set<String> incPropsSet = new HashSet<String>(incProps.length);
			for(String prop : incProps){
				incPropsSet.add(prop);
			}
			List<String> fieldNames = getFieldNames(source.getClass());
			for(String fieldName : fieldNames){
				if(!incPropsSet.contains(fieldName)){
					continue;
				}
				Object value = getProperty(source, fieldName);
				setProperty(target, fieldName, value);
			}
		}
	}
	
	/**
	 * java bean转Map
	 * @param bean
	 * @param propNames 需要放到map中的属性名称
	 * @return
	 */
	public static Map<String,Object> beanToMap(Object bean, String...propNames) {
		Map<String,Object> rtn = new HashMap<String,Object>();
		if(ArrayUtils.isEmpty(propNames)){
			List<String> fieldNames = getFieldNames(bean.getClass());
			for (String fieldName: fieldNames) {
				Object value = getProperty(bean, fieldName);
				rtn.put(fieldName, value);
			}
		}else{
			for(String propName: propNames){
				Object value = getProperty(bean, propName);
				rtn.put(propName, value);
			}
		}
		return rtn;
	}
	
	/**
	 * Map 转 JavaBean
	 * @param map
	 * @param clazz
	 * @return 
	 */
	public static <T> T mapToBean(Map<String,?> map,Class<T> clazz){
		T bean = newInstance(clazz);
		for(Entry<String, ?> me:map.entrySet()){
			setProperty(bean, me.getKey(), me.getValue(), true);
		}
		return bean;
	}
	
	/**
	 * 反射无参创建对象
	 * @param clazz
	 * @return
	 */
	public static <T> T newInstance(Class<T> clazz){
		return BeanUtils.instantiate(clazz);
	}
	
	/**
	 * 获取私有属性Value
	 * @param bean
	 * @param name
	 * @return
	 */
	public static Object getPrivateProperty(Object bean, String name){
		try {
			Field field = getField(bean.getClass(), name);
			if(field!=null){
				field.setAccessible(true);
				return field.get(bean);
			}else{
				throw new RuntimeException("The field [ "+field+"] in ["+bean.getClass().getName()+"] not exists");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 设置私有属性Value
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static void setPrivateProperty(Object bean, String name, Object value){
		try {
			Field field = getField(bean.getClass(), name);
			if(field!=null){
				field.setAccessible(true);
				field.set(bean, value);
			}else{
				throw new RuntimeException("The field [ "+field+"] in ["+bean.getClass().getName()+"] not exists");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取字段
	 * @param clazz
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Field getField(Class<?> clazz,String name){
		return ReflectionUtils.findField(clazz, name);
		
	}
	
	/**
	 * 获取字段类型
	 * @param clazz
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Class<?> getFieldType(Class<?> clazz,String name) {
		Field field = getField(clazz, name);
		if(field!=null){
			return field.getType();
		}else{
			throw new RuntimeException("Cannot locate field " + name + " on " + clazz);
		}
	}

	/**
	 * @see #setProperty(Object, String, Object, boolean)
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static void setProperty(Object bean, String name, Object value){
		setProperty(bean, name, value, false);
	}

	/**
	 * 设置Bean属性,类型自动转换,如果涉及日期转换,只支持long类型或者long值的字符串
	 * @param bean
	 * @param name
	 * @param value
	 * @param ignoreError 忽略找不到属性错误
	 * @throws Exception
	 */
	public static void setProperty(Object bean, String name, Object value, boolean ignoreError) {
		if (value != null) {
			try {
				Class<?> type = getPropertyType(bean, name);
				if (type != null) {
					if (!value.getClass().equals(type)) {
						if (TypeUtils.isAssignable(Date.class, type) && value instanceof String) {
							try {
								value = new Date(Long.parseLong((String) value));
							} catch (NumberFormatException ignore) {}
						} else {
							//如果把一个字符串转换成数值,去除数值的,
							if(TypeUtils.isAssignable(Number.class, type)){
								value = StringUtils.deleteAny(value.toString(), ",");
							}
							value = ConvertUtils.convert(value, type);
						}
					}
				}
				PropertyUtils.setProperty(bean, name, value);
			} catch (NestedNullException e) {
				createNestedBean(bean, name);
				setProperty(bean, name, value, ignoreError);
			} catch (NoSuchMethodException e) {
				if (!ignoreError)
					throw new RuntimeException(e);
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 获取属性,忽略NestedNullException
	 * 
	 * @param bean
	 * @param name
	 * @return
	 */
	public static Object getProperty(Object bean, String name){
		try {
			return PropertyUtils.getProperty(bean, name);
		} catch (NestedNullException ignore) {
			return null;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取属性类型,忽略NestedNullException
	 * 
	 * @param bean
	 * @param name
	 * @return
	 */
	public static Class<?> getPropertyType(Object bean, String name) {
		try {
			return PropertyUtils.getPropertyType(bean, name);
		} catch (NestedNullException ignore) {
			return null;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建内嵌对象
	 * 
	 * @param bean
	 * @param name
	 */
	private static void createNestedBean(Object bean, String name){
		String[] names = name.split("[.]");
		if (names.length == 1)
			return;
		try {
			StringBuilder nestedName = new StringBuilder();
			for (int i = 0; i < names.length - 1; i++) {
				String n = names[i];
				if (i > 0) {
					nestedName.append("." + n);
				} else {
					nestedName.append(n);
				}
				Object val = PropertyUtils.getProperty(bean, nestedName.toString());
				if (val == null) {
					PropertyUtils.setProperty(bean, nestedName.toString(),
					PropertyUtils.getPropertyType(bean, nestedName.toString()).newInstance());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取n个类,相同的父类类型,如果多个相同的父类,获取最接近的的,
	 * 如果传递的对象包含Object.class 直接返回null 
	 * @param clazzs 
	 * @return 相同的父类Class
	 */
	public static Class<?> getEqSuperClass(Class<?> ...clazzs){
		Validate.notEmpty(clazzs);
		List<List<Class<?>>> container = new ArrayList<List<Class<?>>>(clazzs.length);
		for(Class<?>clazz :clazzs){
			if(clazz==Object.class)return null;
			List<Class<?>> superClazz = new ArrayList<Class<?>>(5);
			for(clazz=clazz.getSuperclass();clazz!=Object.class;clazz=clazz.getSuperclass()){
				superClazz.add(clazz);
			}
			container.add(superClazz);
		}
		List<Class<?>> result = new ArrayList<Class<?>>(5);  
		Iterator<List<Class<?>>> it = container.iterator();
		int len =0;
		while(it.hasNext()){
			if(len == 0){
				result.addAll(it.next());
			}else{
				result.retainAll(it.next());
				if(CollectionUtils.isEmpty(result)){
					break;
				}
			}
			len++;
		}
		//不管相同父类有几个,返回最接近的
		if(CollectionUtils.isNotEmpty(result)){
			return result.get(0);
		}
		return Object.class;
	}
	
}
