package com.aman.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aman.orderservice.dto.OrderResponseDTO;
import com.aman.orderservice.model.Order;
import com.aman.orderservice.service.OrderServiceUsingWebclient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderServiceUsingWebclient orderService;

	@PostMapping
	@ResponseStatus(code  =  HttpStatus.CREATED)
	@CircuitBreaker(name="inventory", fallbackMethod =  "fallbackMethod")
	public String placeOrder(@RequestBody Order order) {
		log.info("Order Place API Invoked");
		return "Order Placed with Id " + orderService.placeOrder(order);
	}
	
	public String fallbackMethod(Order order) {
		return "Oops! Error Occurred...Order is not Placed";
	}
	
	@GetMapping
	@ResponseStatus(code  =  HttpStatus.OK)
	public List<OrderResponseDTO> getOrderDetails() {
		log.info("Order detail fetch API Invoked");
		return orderService.getOrderDetails();
	}
}
