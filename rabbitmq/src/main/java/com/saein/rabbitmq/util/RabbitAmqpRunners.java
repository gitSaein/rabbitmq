package com.saein.rabbitmq.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

public class RabbitAmqpRunners implements CommandLineRunner {

	@Value("${rabbitmq.client.duration:0}")
	private int duration;
	
	private ConfigurableApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ready... running for " + duration + "ms");
		Thread.sleep(duration);
		ctx.close();
	}
	
}
