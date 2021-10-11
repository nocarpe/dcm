package com.dcm.boot.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 对象属性拷贝
 * @Accessors 注解修改set属性方法  冲突
 */
public class ModelUtil {

	private static final Log LOGGER = LogFactory.getLog(ModelUtil.class);

	/*
	 * Java默认定义一个public的无参数构造方法，为了阻止静态工具方法被实例化,应该定义一个private的构造
	 */
	private ModelUtil() {

	}

	/**
	 * 将DTO中的属性拷贝至Model
	 */
	public static <T> T dtoToModel(Object dtoObj, Class<T> modelClass) {
		T modelObj = null;
		if (dtoObj == null) {
			return null;
		}
		try {
			//dtoObj = deepCopyBean(dtoObj);
			modelObj = modelClass.getDeclaredConstructor().newInstance();
			//BeanUtils.copyProperties(dtoObj, modelObj);
			CachedBeanCopier.copy(dtoObj,modelObj);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return modelObj;
	}

	/**
	 * 将Model中的属性拷贝至Dto
	 */
	public static <T> T modelToDto(Object modelObj, Class<T> dtoClass) {
		T dtoObj = null;
		if (modelObj == null) {
			return null;
		}
		try {
			//modelObj = deepCopyBean(modelObj);
			dtoObj = dtoClass.getDeclaredConstructor().newInstance();
			//BeanUtils.copyProperties(modelObj, dtoObj);
			CachedBeanCopier.copy(modelObj, dtoObj);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return dtoObj;
	}

	// 深拷贝,序列化不采用
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T deepCopyBean(T source) {
		if (source == null) {
			return null;
		}
		try {
			if (source instanceof Collection) {
				return (T) deepCopyCollection((Collection) source);
			}
			if (source.getClass().isArray()) {
				return (T) deepCopyArray(source);
			}
			if (source instanceof Map) {
				return (T) deepCopyMap((Map) source);
			}
			if (source instanceof Date) {
				return (T) new Date(((Date) source).getTime());
			}
			if (source instanceof Timestamp) {
				return (T) new Timestamp(((Timestamp) source).getTime());
			}
			// 基本类型直接返回原值
			if (source.getClass().isPrimitive() || source instanceof String || source instanceof Boolean
					|| Number.class.isAssignableFrom(source.getClass())) {
				return source;
			}
			if (source instanceof StringBuilder) {
				return (T) new StringBuilder(source.toString());
			}
			if (source instanceof StringBuffer) {
				return (T) new StringBuffer(source.toString());
			}
			Object dest = source.getClass().newInstance();
			BeanUtilsBean bean = BeanUtilsBean.getInstance();
			PropertyDescriptor[] origDescriptors = bean.getPropertyUtils().getPropertyDescriptors(source);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue;
				}

				if (bean.getPropertyUtils().isReadable(source, name) && bean.getPropertyUtils().isWriteable(dest, name)) {
					try {
						Object value = deepCopyBean(bean.getPropertyUtils().getSimpleProperty(source, name));
						bean.getPropertyUtils().setSimpleProperty(dest, name, value);
					} catch (NoSuchMethodException e) {
						LOGGER.error(e);
					}
				}
			}
			return (T) dest;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Collection deepCopyCollection(Collection source) throws InstantiationException,
        IllegalAccessException {
		Collection dest = source.getClass().newInstance();
		for (Object o : source) {
			dest.add(deepCopyBean(o));
		}
		return dest;
	}

	private static Object deepCopyArray(Object source) throws InstantiationException, IllegalAccessException,
        ArrayIndexOutOfBoundsException, IllegalArgumentException {
		int length = Array.getLength(source);
		Object dest = Array.newInstance(source.getClass().getComponentType(), length);
		if (length == 0) {
			return dest;
		}
		for (int i = 0; i < length; i++) {
			Array.set(dest, i, deepCopyBean(Array.get(source, i)));
		}
		return dest;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map deepCopyMap(Map source) throws InstantiationException, IllegalAccessException {
		Map dest = source.getClass().newInstance();
		for (Object o : source.entrySet()) {
			Entry e = (Entry) o;
			dest.put(deepCopyBean(e.getKey()), deepCopyBean(e.getValue()));
		}
		return dest;
	}
}