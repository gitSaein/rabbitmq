package com.saein.rabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/*
 * author: @saeinin
 * description: rabbitMQ exchange type - direct( receive queue binded by using routing key)
 * 
 * 
 */
@Service
public class ReceiveLogsDirect {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private static final String EXCHANGE_NAME = "direct_logs";
	
	public void main(String[] argv) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection;
		connection = factory.newConnection();

		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();
		
		for(String key: argv) {
			channel.queueBind(queueName, EXCHANGE_NAME, key);
		}

		
		log.info(" [*] Waiting for messages. To exit press CTRL+C");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			log.info(" [x] Received " + delivery.getEnvelope().getRoutingKey() + ":" + message);
		};
		
		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
	}
	
}
