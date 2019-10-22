package com.softi.subwayMap.common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToLocpositConverter implements Converter<String, List<Object>>{

	@Override
	public List<Object> convert(String value) {
		List<Object> locposits=null;
		try {
			locposits=JSON.parseArray(value, Object.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locposits;
	}

}
