package com.example.tradestore;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.tradestore.controller.TradeController;
import com.example.tradestore.exception.TradeNotValidException;
import com.example.tradestore.model.Trade;


@SpringBootTest
public class TradeStoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TradeController tradeController;
	
	void testTradeValidation()
	{
		ResponseEntity responseEntity = tradeController.validTradeStore(createTrade("T1",1,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<Trade> tradeList =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeId());
	}
	
	@Test
	void testTradeValidMaturDatePast() {
		try {
			LocalDate localDate = LocalDate.of(2021,06,17);
			ResponseEntity responseEntity = tradeController.validTradeStore(createTrade("T2", 1, localDate));
		}catch (TradeNotValidException ex) {
			Assertions.assertEquals("Trade not valid", ex.getMessage());
		}
	}

	@Test
	void testTradeValidateOldVersion() {

		ResponseEntity responseEntity = tradeController.validTradeStore(createTrade("T2",2,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<Trade> tradeList =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T2",tradeList.get(0).getTradeId());
		Assertions.assertEquals(2,tradeList.get(0).getVersion());
		Assertions.assertEquals("T2B1",tradeList.get(0).getBookId());

		
		try {
			ResponseEntity responseEntity1 = tradeController.validTradeStore(createTrade("T2", 1, LocalDate.now()));


		}catch (TradeNotValidException e){
			System.out.println(e.getMessage());
		}
		List<Trade> tradeList1 =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList1.size());
		Assertions.assertEquals("T2",tradeList1.get(0).getTradeId());
		Assertions.assertEquals(2,tradeList1.get(0).getVersion());
	}
	
	@Test
	void testTradeSameVersionPastMatDate() {

		ResponseEntity responseEntity = tradeController.validTradeStore(createTrade("T2",2,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<Trade> tradeList =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T2",tradeList.get(0).getTradeId());
		Assertions.assertEquals(2,tradeList.get(0).getVersion());
		Assertions.assertEquals("T2B1",tradeList.get(0).getBookId());

		
		try {
			ResponseEntity responseEntity1 = tradeController.validTradeStore(createTrade("T2", 3, LocalDate.of(2021,06,17)));


		}catch (TradeNotValidException e){
			System.out.println(e.getMessage());
		}
		List<Trade> tradeList1 =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList1.size());
		Assertions.assertEquals("T2",tradeList1.get(0).getTradeId());
		Assertions.assertEquals(2,tradeList1.get(0).getVersion());
	}
	
	
	@Test
	void testTradeValidateSameVersion() {

		ResponseEntity responseEntity = tradeController.validTradeStore(createTrade("T1",2,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<Trade> tradeList =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeId());
		Assertions.assertEquals(2,tradeList.get(0).getVersion());
		Assertions.assertEquals("T1B1",tradeList.get(0).getBookId());

		Trade trade2 = createTrade("T1",2,LocalDate.now());
		trade2.setBookId("T1B1C2");
		ResponseEntity responseEntity2 = tradeController.validTradeStore(trade2);
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity2);
		List<Trade> tradeList2 =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList2.size());
		Assertions.assertEquals("T1",tradeList2.get(0).getTradeId());
		Assertions.assertEquals(2,tradeList2.get(0).getVersion());
		Assertions.assertEquals("T1B1C2",tradeList2.get(0).getBookId());

	}
	
	private Trade createTrade(String tradeId,int version, LocalDate matuarityDate)
	{		
		Trade trade= new Trade();
		trade.setTradeId(tradeId);
		trade.setBookId(tradeId +"B1");
		trade.setVersion(version);
		trade.setCounterPartyId(tradeId+"Cp");
		trade.setMatuarityDate(matuarityDate);
		trade.setExpired("Y");
		return trade;	
}
	
	
}

	
