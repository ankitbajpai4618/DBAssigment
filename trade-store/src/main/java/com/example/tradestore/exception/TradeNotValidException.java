package com.example.tradestore.exception;

import org.springframework.http.HttpStatus;

import com.example.tradestore.model.Trade;

public class TradeNotValidException extends RuntimeException {


	public TradeNotValidException(HttpStatus expectationFailed, String string, TradeNotValidException exc) {
	
	}

	public TradeNotValidException() {
	}

	public TradeNotValidException(Trade createTrade) {
	}

}
