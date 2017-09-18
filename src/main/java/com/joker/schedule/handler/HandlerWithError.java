package com.joker.schedule.handler;


import com.joker.schedule.entity.ScheduleEntity;
import com.joker.schedule.strategy.ScheduleStrategy;

public class HandlerWithError implements Runnable {
	private ScheduleStrategy strategy;
	private ScheduleEntity entity;
	public HandlerWithError(ScheduleEntity entity,ScheduleStrategy strategy) {
		this.strategy = strategy;
		this.entity = entity;
	}
	
	public void run() {
		Runnable runnable = entity.getRunnable();
		try {
			runnable.run();
		} catch (Exception e) {
			strategy.handleRemoveSchedule(entity);
		}
	}
	
}
