package com.example.tradestore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tradestore.model.Trade;
import com.example.tradestore.repository.TradeRepository;

@Service
public class TradeService {
	
	@Autowired
	private TradeRepository repo;

	public boolean validateTrade(Trade createTrade)  {
		
		Optional<Trade> dbTrade = repo.findById(createTrade.getTradeId());
		if(dbTrade.isPresent())
		{
			if((createTrade.getVersion() >= dbTrade.get().getVersion())&&(!createTrade.getMatuarityDate().isBefore(LocalDate.now())))
			{
				return true;
			}else {
				return false;
			}
		}else { 
			if(!createTrade.getMatuarityDate().isBefore(LocalDate.now()))
			{
			return true;
			}
			else { return false;
		}
		
		}
	}

	public void save(Trade createTrade) {
		
		repo.save(createTrade);
		
	}
	public List<Trade> findAll(){
	       return repo.findAll();
	    }
	
	public void updateTradeExpiry()
	{
		repo.findAll().stream().forEach(t -> {
            if (t.getMatuarityDate().isBefore(LocalDate.now())) {
                t.setExpired("Y");
                repo.save(t);
            }
        });
    }
		
	}

