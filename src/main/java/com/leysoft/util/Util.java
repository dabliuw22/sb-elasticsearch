package com.leysoft.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
	private Util() {}
	
	public static <T> List<T> iterableToList(Iterable<T> iterable) {
		List<T> list = new ArrayList<>();
		iterable.forEach(list::add);
		return list;
	}
}
