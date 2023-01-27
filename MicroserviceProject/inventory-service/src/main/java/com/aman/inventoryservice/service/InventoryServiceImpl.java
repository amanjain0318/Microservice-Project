package com.aman.inventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.inventoryservice.dto.InventoryCheckDTO;
import com.aman.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements  InventoryService {
		
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Override
	public List<InventoryCheckDTO>  isInStock(List<String> skuCode) {
		
	List<InventoryCheckDTO> inventoryCheckDTOResult    =  inventoryRepository.findBySkuCodeIn(skuCode)
							.stream()
							.map(inventory -> InventoryCheckDTO.builder()
									.isInStock(inventory.getStock()>0)
									.skuCode(inventory.getSkuCode()).build()
									).toList();
						
				return inventoryCheckDTOResult;
	}
}
