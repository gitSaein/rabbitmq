package com.saein.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.saein.rabbitmq.util.RabbitMqReceiver;
import com.saein.rabbitmq.util.RabbitMqSender;

@Profile({"hello-world"})
@Configuration
public class RabbitMqConfiguration {

	@Bean
	public Queue hello() {
		return new Queue("hello");
	}
	
	@Profile("receiver")
	@Bean
	public RabbitMqReceiver receiver() {
		return new RabbitMqReceiver();
	}
	
	@Profile("sender")
	@Bean
	public RabbitMqSender sender() {
		return new RabbitMqSender();
	}
}
