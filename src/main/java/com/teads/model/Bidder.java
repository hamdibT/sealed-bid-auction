package com.teads.model;

import com.google.common.base.Objects;

import java.util.List;

/**
 * this class  illustrates a bidder with its different price propositions.
 * the bidder is identified by it's name and can add bids even after object has been created.
 */
public class Bidder {

	private String name;
	private List<Integer> prices;

	public Bidder(final String name, final List<Integer> prices) {
		this.name = name;
		this.prices = prices;
	}
	public String getName() {
		return name;
	}

	public List<Integer> getPrices() {
		return prices;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Bidder bidder = (Bidder) o;
		return Objects.equal(name, bidder.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}
}
