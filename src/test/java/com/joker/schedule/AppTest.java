package com.joker.schedule;

import org.junit.Test;

import com.joker.schedule.ScheduleHelper.ScheduleFactory;
import com.joker.schedule.entity.ScheduleEntity;
import com.joker.schedule.strategy.PoolScheduleStrategy;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
//	@Test
//	public void testSchedule() {
//		ScheduleHelper helper = ScheduleFactory.getScheduleHelper(PoolScheduleStrategy.class);
//		System.out.println(helper);
//		Runnable runnable = new Runnable() {
//			public void run() {
//				System.out.println(Thread.currentThread().getName());
//				throw new RuntimeException();
//			}
//		};
//		ScheduleEntity entity = new ScheduleEntity("1,7,11,11", runnable);
//		Runnable runnable1 = new Runnable() {
//
//			public void run() {
//				System.out.println(Thread.currentThread().getName());
//				System.out.println(222);
//			}
//		};
//		ScheduleEntity entity1 = new ScheduleEntity("1,7,11,12", runnable1);
//
//		helper.addSchedule(entity);
//		helper.addSchedule(entity1);
//		try {
//			Thread.sleep(70000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		helper.removeSchedule(entity);
//	}
//	
//	@Test
//	public void testDate() {
//		Runnable runnable = new Runnable() {
//
//			public void run() {
//				System.out.println(1111);
//			}
//		};
//		ScheduleEntity entity = new ScheduleEntity("1,7,20,42", runnable);
//		System.out.println(entity.getInitialDelay());
//	}
	

	
}
