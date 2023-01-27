package com.aman.orderservice.dto;

import java.util.List;

import com.aman.orderservice.model.OrderLineItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
	
    private Long id;
    private String orderNumber;
    private List<OrderLineItems> orderLineItems;
    
}
