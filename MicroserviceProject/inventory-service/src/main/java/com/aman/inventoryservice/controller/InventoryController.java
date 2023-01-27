package com.aman.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aman.inventoryservice.dto.InventoryCheckDTO;
import com.aman.inventoryservice.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

	@Autowired
	public InventoryService inventoryService;

    
    @GetMapping
    public List<InventoryCheckDTO> isInStock(@RequestParam List<String> skuCode) {
        log.info("Checking stock for product with skucode");
        return inventoryService.isInStock(skuCode);
    }
}