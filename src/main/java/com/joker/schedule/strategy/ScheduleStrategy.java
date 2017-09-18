package com.joker.schedule.strategy;


import com.joker.schedule.entity.ScheduleEntity;

public interface ScheduleStrategy {
	public void handleAddSchedule(ScheduleEntity scheduleEntity);
	
	public void handleRemoveSchedule(ScheduleEntity scheduleEntity);
	
	
	
}
