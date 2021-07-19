package com.example.tradestore.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.tradestore.service.TradeService;

@Component
public class TradeScheduler {
	
	@Autowired
	TradeService updateService; 
	  private int count =0;

	@Scheduled(fixedRate=50000)
	public void updateTradeExp()
	{
		updateService.updateTradeExpiry();
		this.count++;
	}
	public int getCount() {
        return this.count;
    }
}
