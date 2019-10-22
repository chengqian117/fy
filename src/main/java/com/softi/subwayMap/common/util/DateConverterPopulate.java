package com.softi.subwayMap.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.beanutils.Converter;
/**
 * 
 * @author cq
 * Map to object 的时间转换工具
 * 从redis 读取 date 时， 需要初始化 locale 为 us
 */
public class DateConverterPopulate implements Converter {
	private  Locale locale;
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public DateConverterPopulate(Locale locale) {
		super();
		this.locale = locale;
	}
	public DateConverterPopulate() {
		super();
	}
	@Override
	public Object convert(Class type, Object value) {
		// TODO Auto-generated method stub
		return toDate(type, value);
	}

	public  Object toDate(Class type, Object value) {
		SimpleDateFormat sdf;
		Date date;
		try {
			if (value == null || "".equals(value))
				return null;
			if (value instanceof String) {
				String s = (String) value;
				if (type.equals(Date.class)) {
					if (s.matches("^\\d{4}-\\d{2}-\\d{2}")) {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						date = sdf.parse(s);
					} if (s.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
						sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						date = sdf.parse(s);
					} else if (s.matches("^\\d{10,12}")) {
						long l1 = Long.parseLong(value + "000");
						date = new Date(l1);
					} else if (s.matches("^\\d{13,}")) {
						long l1 = Long.parseLong(s);
						date = new Date(l1);
					} else if(s.matches("^\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} .* \\d{4}")){
						sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",this.locale==null? java.util.Locale.getDefault(): this.locale);
						date=sdf.parse(s);
					}else{
						return null;
					}
					return date;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
