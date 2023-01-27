package com.aman.orderservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aman.orderservice.dto.InventoryCheckDTO;
import com.aman.orderservice.dto.OrderResponseDTO;
import com.aman.orderservice.event.OrderPlacedEvent;
import com.aman.orderservice.model.Order;
import com.aman.orderservice.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceUsingWebclient implements OrderServiceImpl {

	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

	@Override
	public Long placeOrder(Order order) {
		List<String> orderItemSkuCodeList = order.getOrderLineItems().stream().map(orderItem -> orderItem.getSkuCode())
				.toList();

		InventoryCheckDTO[] inventoryCheckDTOArray = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", orderItemSkuCodeList).build())
				.retrieve().bodyToMono(InventoryCheckDTO[].class).block();
		boolean stockResult = Arrays.stream(inventoryCheckDTOArray)
				.allMatch(inventoryCheckDTO -> inventoryCheckDTO.isInStock());
		if (stockResult) {
			order = orderRepository.save(order);
			kafkaTemplate.send("NotificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
			log.info("Order placed with id " + order.getId());
		} else {
			log.info("Order is not in Stock");
			throw new IllegalArgumentException("Order is not in Stock");
		}
		return order.getId();
	}

	public List<OrderResponseDTO> getOrderDetails() {
		return orderRepository.findAll().stream().map(order -> maptoDTO(order)).toList();

	}

	public OrderResponseDTO maptoDTO(Order order) {
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		orderResponseDTO.setId(order.getId());
		orderResponseDTO.setOrderNumber(order.getOrderNumber());
		orderResponseDTO.setOrderLineItems(order.getOrderLineItems());
		return orderResponseDTO;
	}
}
