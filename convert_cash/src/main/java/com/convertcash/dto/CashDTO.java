package com.convertcash.dto;

import java.io.Serializable;

import com.convertcash.domain.Cash;

public class CashDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private double quantity;
	
	public CashDTO() {
	}
	
	public CashDTO(Cash obj) {
		id = obj.getId();
		name = obj.getName();
		quantity = obj.getQuantity();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

}
	