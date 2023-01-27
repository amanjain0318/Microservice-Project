package com.aman.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}	
	
	@KafkaListener(topics = {"NotificationTopic"})
	public void eventHandle(OrderPlacedEvent orderPlacedEvent) {
		log.info("Order is placed with ID {}", orderPlacedEvent.getOrderno());
	}

}
