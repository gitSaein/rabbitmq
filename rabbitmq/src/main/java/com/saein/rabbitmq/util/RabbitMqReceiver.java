package com.saein.rabbitmq.util;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class RabbitMqReceiver {

	@RabbitHandler
	public void receive(String in) {
		System.out.println("received: " + in);
	}
	
}
