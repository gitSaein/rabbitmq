package com.saein.rabbitmq.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class EmitLog {

	private static final String EXCHANGE_NAME = "logs";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public void emit() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection()) {
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			
			String message = "hello channel fanout: " + ( System.currentTimeMillis() / 3000 );
			
			channel.basicPublish(EXCHANGE_NAME, "fanoutKey", null, message.getBytes("UTF-8"));
			log.info("send: {}", message);
		}
	}
	
}
