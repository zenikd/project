package org.ez.vk.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.ez.vk.exception.internal.InternalException;
import org.springframework.stereotype.Service;
@Service
public class DateHelper {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public long getEndDateCurDay() throws InternalException {
		String curDate = dateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(curDate));

		} catch (ParseException e) {
			throw new InternalException();
		}
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.HOUR, 3);
		return c.getTime().getTime();
	}

	public long getEndPrivDay() throws InternalException {
		String curDate = dateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(curDate));
			c.add(Calendar.HOUR, 3);

		} catch (ParseException e) {
			throw new InternalException();
		}
		return c.getTime().getTime();
	}
}
