package com.softi.subwayMap.common.util;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;

public class BeanUtilss {

	public static Object getBeanByMap(Map<String, String> map, Class<?> beanClass) {
		if (map == null)
			return null;

		Object obj;
		try {
			DateConverterPopulate dateConverter=new DateConverterPopulate(java.util.Locale.US);
			ConvertUtils.register(dateConverter, Date.class); 
			ConvertUtils.register(new BigDecimalConverter(null),BigDecimal.class);
			obj = beanClass.newInstance();
			BeanUtils.populate(obj, map);
			return obj;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public static Map<String, String> getMapByBean(Object obj) {
		if (obj == null)
			return null;
		ConvertUtils.register(new DateConverter(null), Date.class); 
		ConvertUtils.register(new BigDecimalConverter(null),BigDecimal.class);
		Map<String, String> map;
		try {
			map = BeanUtils.describe(obj);
			removeMapEmptyValue(map);
			return map;
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}

	public static void removeMapEmptyValue(Map<String, String> paramMap) {

		if (null != paramMap.get("class"))
			paramMap.remove("class");
		Set<String> set = paramMap.keySet();
		Iterator<String> it = set.iterator();
		List<String> listKey = new ArrayList<String>();

		while (it.hasNext()) {
			String str = it.next();
			if (paramMap.get(str) == null || "".equals(paramMap.get(str))) {
				listKey.add(str);
			}
		}
		for (String key : listKey) {
			paramMap.remove(key);
		}
		// return paramMap;
	}
	
	
}
