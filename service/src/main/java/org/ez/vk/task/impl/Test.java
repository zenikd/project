package org.ez.vk.task.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		Pattern statusTextPattern  = Pattern.compile("\"like_like\":\"([^\"]+)\"");
		Matcher m = statusTextPattern.matcher("{\"status\":\"VALIDATION_LIKE\",\"like_id\":\"290620\",\"like_like\":\"https://vk.com/photo329795391_456239118\"}");
		if (m.find()) {
			System.out.println(m.group(1));
		}
	}

}
