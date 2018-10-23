package org.ez.vk.task.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("/[a-z]+([\\d]+)\"");
		Matcher m = p.matcher("{\"status\":\"SUCCESS\",\"link\":\"http://vk.com/club97574502\"}");
		if (m.find()) {
			System.out.println(m.group(1));
		}
	}

}
