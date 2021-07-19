package com.example.tradestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tradestore.model.Trade;

public interface TradeRepository extends JpaRepository<Trade,String>{

}
