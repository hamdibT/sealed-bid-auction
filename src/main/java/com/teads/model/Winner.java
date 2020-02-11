package com.teads.model;

/**
 * Pojo which encapsulate the winner name and its winning price
 */
public class Winner {

	private String name;
	private Integer price;

	public Winner(final String name, final Integer price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}
}
