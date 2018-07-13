package com.leysoft.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	
	private Util() {}
	
	public static <T> List<T> iterableToList(Iterable<T> iterable) {
		List<T> list = new ArrayList<>();
		iterable.forEach(list::add);
		return list;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = format.format(date);
		LOGGER.info("value date: {} - {}", date, dateFormat);
		return dateFormat;
	}
}
