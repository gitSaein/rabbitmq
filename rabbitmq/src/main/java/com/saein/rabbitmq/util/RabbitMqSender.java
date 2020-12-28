package com.saein.rabbitmq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;


public class RabbitMqSender {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private EmitLog emitlog;
	
	@Autowired
	private ReceiveLogs receiveLogs;

	@Autowired
	private ReceiveLogsDirect receiveLogsDirect;
	
	@Autowired
	private EmitLogDirect emitLogDirect;
	
	@Autowired
	private Queue queue;
	
	//@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void send1() throws InterruptedException {
		System.out.println("[1] fixed delay tast ---------------------- " + System.currentTimeMillis() / 1000);
		String message = "[1] Hello World ~!" + System.currentTimeMillis();
		this.template.convertAndSend(queue.getName(), message);
		System.out.println("[1] send: " + message );
		System.out.printf("[1] current thread: ", Thread.currentThread().getName());
	}
	
	
	//@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void send2() throws InterruptedException {
		System.out.println("[2] fixed delay tast ---------------------- " + System.currentTimeMillis() / 1000);
		String message = "[2] Hello World ~!" + System.currentTimeMillis();
		this.template.convertAndSend(queue.getName(), message);
		System.out.println("[2] send: " + message );
		System.out.printf("[2] current thread: ", Thread.currentThread().getName());
	}
	
	//@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void emitLog() throws Exception {
		emitlog.emit();
	}
	
	//@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void receiveLog() throws Exception {
		log.info("receiveLog ########################");
		receiveLogs.receiveLogs();
	}
	//@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void receiveLog2() throws Exception {
		log.info("receiveLog2 #######################");
		receiveLogs.receiveLogs();
	}
	
	@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
		public void emitLog1Direct() throws Exception {
			log.info("emitLog1Direct ########################");
			emitLogDirect.main(new String[]{"1","2","3","4","5","6"});
		}
		
	@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void emitLog2Direct() throws Exception {
		log.info("emitLog2Direct ########################");
		emitLogDirect.main(new String[]{"1","2","3","4","5","6"});
	}
	
	@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void receiveLogDirect() throws Exception {
		log.info("receiveLogDirect ########################");
		receiveLogsDirect.main(new String[]{"1","2","3","4","5","6"});
		
	}
	
	@Scheduled( fixedDelay = 1000, initialDelay  = 500 )
	public void receiveLog2Direct() throws Exception {
		log.info("receiveLog2Direct #######################");
		receiveLogsDirect.main(new String[]{"1","2","3","4","5","6"});
	}
	
}
