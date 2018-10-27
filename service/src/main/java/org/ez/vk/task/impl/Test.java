package org.ez.vk.task.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		Pattern statusTextPattern  = Pattern.compile("reward\":\"([\\d]+)");
		Matcher m = statusTextPattern.matcher(" \"reward\":\"1\",");
		if (m.find()) {
			System.out.println(Integer.parseInt(m.group(1)));
		}
	}

}
