package com.saein.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.saein.rabbitmq.util.RabbitAmqpRunners;

@SpringBootApplication
@EnableScheduling
public class RabbitmqApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring profiles to control its behavior.\n");
			System.out.println("Sample usage: java -jar rabbitmq.jar --spring.profiles.active=hello-world,sender");
		};
	}
	
	@Profile("!usage_message")
	@Bean
	public CommandLineRunner basic() {
		return new RabbitAmqpRunners();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

}
