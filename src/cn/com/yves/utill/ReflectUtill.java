package cn.com.yves.utill;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtill {

	/**
	 * 
	 * @Descripton :根据属性名来反射调用方法获得该属性的get方法的返回值
	 * 
	 * @param obj
	 * @param attributeName
	 * @return Object
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getValue(Object obj, String attributeName)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Object value = null;
		Method[] m = obj.getClass().getMethods();
		for (int i = 0; i < m.length; i++) {
			if (("get" + attributeName).toLowerCase().equals(
					m[i].getName().toLowerCase())) {
				value = m[i].invoke(obj);

			}
		}
		return value;
	}
}
