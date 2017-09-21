# schedule
定时任务框架

## 功能

* 扫描初始化一系列的定时任务
* 运行过程中动态的加入、删除定时任务
* 用户可以自定义定时策略并可对策略进行缓存

## 定时表达式

例：`1,7,11,12`

周一日至周六的每天11点12分执行

## demo

方式一

```java
	@Scheduled(cron = "1,7,10,45")
	public void test1() {
		System.out.println("do test1");
	}
```

方式二：

```java
public void testSchedule() {
		ScheduleHelper helper = ScheduleFactory.getScheduleHelper(PoolScheduleStrategy.class);
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName());
				throw new RuntimeException();
			}
		};
		ScheduleEntity entity = new ScheduleEntity("1,7,11,11", runnable);

		helper.addSchedule(entity);
	}
```



