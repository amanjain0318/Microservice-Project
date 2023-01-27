package com.aman.inventoryservice.service;

import java.util.List;

import com.aman.inventoryservice.dto.InventoryCheckDTO;

public interface InventoryService {
		public List<InventoryCheckDTO> isInStock(List<String> skuCode);
}
