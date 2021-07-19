package com.example.tradestore;

import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.tradestore.TradeStoreApplication;
import com.example.tradestore.scheduler.TradeScheduler;

@SpringJUnitConfig(TradeStoreApplication.class)
@SpringBootTest
public class TraSchedulerTest {

	@Autowired
	private TradeScheduler sch;
	
	@Test
    public void givenSchedulerCountGreaterThenZero() 
      throws InterruptedException {
        Thread.sleep(100L);
        assertTrue(sch.getCount() >0 );
    }
}
