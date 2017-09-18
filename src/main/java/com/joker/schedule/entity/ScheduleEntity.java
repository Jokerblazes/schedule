package com.joker.schedule.entity;

import java.lang.reflect.Method;
import java.util.Date;

import com.joker.schedule.utils.Utils;

//1 1 1 1 1
public class ScheduleEntity {
	private int weekBegin;
	private int weekEnd;
//	private int betweenDays;
	private int hours;
	private int minutes;
	private Type type;
	private Runnable runnable;
	
	
	public long getInitialDelay() {
		Date date = new Date();
		if (type == Type.NotSingle) {
			Date thisBeginDate = Utils.getWeekTime(0, weekBegin,hours,minutes);
			Date thisEndDate = Utils.getWeekTime(0, weekEnd,hours,minutes);
			if (date.before(thisEndDate) && date.after(thisBeginDate)) {
				thisEndDate.setDate(date.getDate());
				return thisEndDate.getTime() - date.getTime();
			} else if (date.before(thisBeginDate)) {
				return thisBeginDate.getTime() - date.getTime();
			} else {
				thisBeginDate = Utils.getWeekTime(1, weekBegin, hours, minutes);
				return thisBeginDate.getTime() - date.getTime();
			}
		} else {
			Date thisBeginDate = Utils.getWeekTime(0, weekBegin,hours,minutes);
			return thisBeginDate.getTime() - date.getTime();
		}
	}
	
	
	public ScheduleEntity(String cron,Runnable runnable) {
		String[] conrs = cron.split(",");
		weekBegin = Integer.parseInt(conrs[0]);
		weekEnd = Integer.parseInt(conrs[1]);
		hours = Integer.parseInt(conrs[2]);
		minutes = Integer.parseInt(conrs[3]);
		if (weekEnd != 0) 
			type = Type.NotSingle;
		else 
			type = Type.Single;
		this.runnable = runnable;
	}
	
	
	public int getWeekBegin() {
		return weekBegin;
	}
	public void setWeekBegin(int weekBegin) {
		this.weekBegin = weekBegin;
	}
	public int getWeekEnd() {
		return weekEnd;
	}
	public void setWeekEnd(int weekEnd) {
		this.weekEnd = weekEnd;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public Runnable getRunnable() {
		return runnable;
	}
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


	public long getDelay() {
		int DAYSECONDS = 86400;
		if (type == Type.NotSingle) 
			return DAYSECONDS;
		return 0;
	}


	@Override
	public String toString() {
		return "ScheduleEntity [weekBegin=" + weekBegin + ", weekEnd=" + weekEnd + ", hours=" + hours + ", minutes="
				+ minutes + ", type=" + type + ", runnable=" + runnable + "]";
	}
	
	
	
}
