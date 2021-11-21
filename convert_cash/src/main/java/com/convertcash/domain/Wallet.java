package com.convertcash.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wallet")
public class Wallet {

	private Cash cash;

	public Wallet(Cash cash) {
		this.cash = cash;

	}

	public Cash getCash() {
		return cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}

}