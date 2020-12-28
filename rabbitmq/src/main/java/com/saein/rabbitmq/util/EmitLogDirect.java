package com.saein.rabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/*
 * author: @saeinin
 * description: rabbitMQ exchange type - direct( binding by using routing key)
 * 
 * 
 */
@Service
public class EmitLogDirect {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String EXCHANGE_NAME = "direct_logs";
	
	public void main(String[] argv) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection()){
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "direct");
			

			for(String key : argv) {
			channel.basicPublish(EXCHANGE_NAME, key, null, key.getBytes("UTF-8"));
			log.info("[direct] Sent {}, {}", "directKey", key);
			}
			
		} catch (IOException | TimeoutException e) {

			log.error("::::ERROR:::: {}", e);
		}
	}
}
