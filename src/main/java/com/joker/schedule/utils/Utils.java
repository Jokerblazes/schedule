package com.joker.schedule.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
	private Utils() {}
	
	public static Date getWeekTime(int week,int day,int hour,int minute) {
		Calendar currentDate = new GregorianCalendar();  
		currentDate.add(Calendar.DAY_OF_WEEK, 7*week);
		currentDate.setFirstDayOfWeek(Calendar.SUNDAY);  
		currentDate.set(Calendar.HOUR_OF_DAY, hour);  
		currentDate.set(Calendar.MINUTE, minute);  
		currentDate.set(Calendar.SECOND, 0);  
		currentDate.set(Calendar.DAY_OF_WEEK, day);  
		Date date = (Date)currentDate.getTime();
		return date;
	}
	
	
}
