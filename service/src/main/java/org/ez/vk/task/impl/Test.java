package org.ez.vk.task.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		Pattern statusTextPattern = Pattern.compile("\"user_token\":\"([^\"]+)\"");
		Matcher m = statusTextPattern.matcher("{\"status\":\"SUCCESS\",\"user_token\":\"-xkJSnQq44k2Datkj756PTLb-QpQDmGUQ_wCY12bp1I\"}");
		if (m.find()) {
			System.out.println(m.group(1));
		}
	}

}
