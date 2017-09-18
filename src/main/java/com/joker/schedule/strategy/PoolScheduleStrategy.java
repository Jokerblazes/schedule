package com.joker.schedule.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.joker.schedule.entity.ScheduleEntity;
import com.joker.schedule.entity.Type;
import com.joker.schedule.handler.HandlerWithError;

public class PoolScheduleStrategy implements ScheduleStrategy {
	
	private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
	private Map<ScheduleEntity,ScheduledFuture> scheduledFuturesMap = new ConcurrentHashMap<ScheduleEntity, ScheduledFuture>();
	
	public synchronized void handleAddSchedule(ScheduleEntity scheduleEntity) {
		 long initialDelay = scheduleEntity.getInitialDelay();
		 long delay = scheduleEntity.getDelay();
//		 System.out.println("initialDelay:" + initialDelay + "delay:" + delay);
		 ScheduledFuture future = null;
		 if (scheduleEntity.getType() == Type.NotSingle) {
			 future = executorService.scheduleWithFixedDelay(
					 new HandlerWithError(scheduleEntity,this),
					 initialDelay, delay, TimeUnit.MILLISECONDS);
			 scheduledFuturesMap.put(scheduleEntity,future);
		 } else {
			 future = executorService.schedule(
					 new HandlerWithError(scheduleEntity,this),
					  initialDelay, TimeUnit.MILLISECONDS);
			 scheduledFuturesMap.put(scheduleEntity,future);
		 }
	}

	public synchronized void handleRemoveSchedule(ScheduleEntity scheduleEntity) {
		ScheduledFuture future = scheduledFuturesMap.get(scheduleEntity);
		if (future != null) {
			future.cancel(true);
		}
	}

	
}
