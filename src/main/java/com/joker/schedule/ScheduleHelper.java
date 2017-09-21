package com.joker.schedule;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.schedule.entity.ScheduleEntity;
import com.joker.schedule.entity.Scheduled;
import com.joker.schedule.strategy.ScheduleStrategy;
import com.joker.support.scanner.ClasspathPackageScanner;

/**
 * 定时调度类
 * @author joker
 *  {@link https://github.com/Jokerblazes/schedule.git}
 */
public class ScheduleHelper {
	
	private Logger logger = LoggerFactory.getLogger(ScheduleHelper.class);
	
	private ScheduleStrategy strategy;
	
	private ScheduleHelper(ScheduleStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * 添加定时任务
	 * @param entity
	 * @author joker
	 * {@link https://github.com/Jokerblazes/schedule.git}
	 */
	public void addSchedule(ScheduleEntity entity) {
		logger.info("添加定时任务 {}",entity);
		strategy.handleAddSchedule(entity);
	}
	
	//删除定时任务
	/**
	 * 删除定时任务
	 * @param entity
	 * @author joker
	 * {@link https://github.com/Jokerblazes/schedule.git}
	 */
	public void removeSchedule(ScheduleEntity entity) {
		logger.info("删除定时任务 {}",entity);
		strategy.handleRemoveSchedule(entity);
	}
	
	/**
	 * 初始化定时任务
	 * @param beanPackage
	 * @author joker
	 * {@link https://github.com/Jokerblazes/schedule.git}
	 */
	public void initSchedule(String beanPackage) {
		//扫描包取出包中的所有类
		logger.info("准备扫描包 {}",beanPackage);
		ClasspathPackageScanner scanner = new ClasspathPackageScanner(beanPackage);
		List<String> classNames = null;
		try {
			classNames = scanner.getFullyQualifiedClassNameList();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logger.info("包中class有 {}",classNames);
		
		for (String className : classNames) {
			try {
				final Object object = Class.forName(className).newInstance();
				Method[] methods = object.getClass().getDeclaredMethods();
				for (final Method method : methods) {
					Scheduled scheduled = method.getDeclaredAnnotation(Scheduled.class);
					if (scheduled == null) {
						continue;
					}
					Runnable runnable = new Runnable() {
						public void run() {
							try {
								method.invoke(object, null);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					};
					ScheduleEntity scheduleEntity = new ScheduleEntity(scheduled.cron(),runnable);
					strategy.handleAddSchedule(scheduleEntity);
					logger.info("执行定时任务 {}", scheduleEntity);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ScheduleHelper构建工厂
	 * @author joker
	 * {@link https://github.com/Jokerblazes/schedule.git}
	 */
	public static class ScheduleFactory {
		private static Map<Class,ScheduleStrategy> strategyMap = new HashMap<Class, ScheduleStrategy>();
		public static ScheduleHelper getScheduleHelper(Class strategyClazz) {
			ScheduleStrategy strategy = strategyMap.get(strategyClazz);
			if (strategy == null) {
				try {
					strategy = (ScheduleStrategy) strategyClazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return new ScheduleHelper(strategy);
		}
	}
}
