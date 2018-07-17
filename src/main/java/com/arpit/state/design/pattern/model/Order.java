package com.arpit.state.design.pattern.model;

public class Order extends BaseModel {

	private int id;
	private int amount;

	public Order(final int id, final int amount) {
		this.amount = amount;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

}
