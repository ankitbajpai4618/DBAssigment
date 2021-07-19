package com.example.tradestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tradestore.exception.TradeNotValidException;
import com.example.tradestore.model.Trade;
import com.example.tradestore.service.TradeService;

@RestController
@EnableScheduling
public class TradeController {
	
	@Autowired
	private TradeService service;

@PostMapping("/trade")
	public ResponseEntity<String> validTradeStore(@RequestBody Trade createTrade) {
	
	try
	{
		
	if (service.validateTrade(createTrade))
	{
		service.save(createTrade);
	}
	else {
		throw new TradeNotValidException();
	}
	}catch(TradeNotValidException exc) 
	{
		throw new TradeNotValidException(HttpStatus.EXPECTATION_FAILED, "Not a valid trade", exc);
	}
	
	return ResponseEntity.status(HttpStatus.OK).build();
	}

@GetMapping("/getAllTrades")
	public List<Trade> findAllTrades() {
		
		return service.findAll();
	}

	

}
