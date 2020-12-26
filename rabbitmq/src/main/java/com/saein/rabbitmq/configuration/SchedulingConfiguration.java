package com.saein.rabbitmq.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulingConfiguration implements SchedulingConfigurer{
	
	private final static int POOL_SIZE = 10;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		final ThreadPoolTaskScheduler threadPoolThreadScheduler = 
				new ThreadPoolTaskScheduler();
		
		threadPoolThreadScheduler.setPoolSize(POOL_SIZE);
		threadPoolThreadScheduler.setThreadNamePrefix("hello-");
		threadPoolThreadScheduler.initialize();
		
		taskRegistrar.setTaskScheduler(threadPoolThreadScheduler);
		
	}

}
