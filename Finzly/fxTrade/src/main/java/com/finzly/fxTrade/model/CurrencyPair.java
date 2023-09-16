package com.finzly.fxTrade.model;

import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
public class CurrencyPair {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long currencyPairId;
	@Column(unique = true)
	private String currencyPair;
	private double rate;

	public CurrencyPair() {
	}

	public CurrencyPair(String currencyPair, double rate) {
		this.currencyPair = currencyPair;
		this.rate = rate;
	}

	public Long getCurrencyPairId() {
		return currencyPairId;
	}

	public void setCurrencyPairId(Long currencyPairId) {
		this.currencyPairId = currencyPairId;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
