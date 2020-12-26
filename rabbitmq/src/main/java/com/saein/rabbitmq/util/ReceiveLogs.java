package com.saein.rabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Service
public class ReceiveLogs {

	private static final String EXCHANGE_NAME = "logs";
	
	public void receiveLogs() throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");
		
		System.out.println(" [*] Waiting for message. to exit press ctrl+c");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println("[x] received " + message);	
		};
		
		channel.basicConsume(queueName, true, deliverCallback, consumeTag -> {});
		
	}
}
