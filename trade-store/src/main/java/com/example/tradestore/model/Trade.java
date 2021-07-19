package com.example.tradestore.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
public class Trade {
	
	@Id
	private String	tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private LocalDate matuarityDate;
	private LocalDate createDate;
	private String expired;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getCounterPartyId() {
		return counterPartyId;
	}
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public LocalDate getMatuarityDate() {
		return matuarityDate;
	}
	public void setMatuarityDate(LocalDate matuarityDate) {
		this.matuarityDate = matuarityDate;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	public Trade(String tradeId, int version, String counterPartyId, String bookId, LocalDate matuarityDate, LocalDate createDate,
			String expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.matuarityDate = matuarityDate;
		this.createDate = createDate;
		this.expired = expired;
	}
	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
