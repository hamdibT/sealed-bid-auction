package com.teads.model;

import com.google.common.collect.Sets;
import com.teads.exception.BiddingException;

import java.util.Set;

/**
 * Model used to illustrate the auction object
 * Every auction has its reserve price and a number of bidders
 *
 */
public class Auction {

	private int reservePrice;
	//using Set to have unique bidders
	private Set<Bidder> bidders= Sets.newHashSet();

	public Auction(int reservePrice) {
		this.reservePrice = reservePrice;
	}

	/**
	 * * add a new bidder and throws a bidding exception when we try to add an existing bidder
	 *
	 * @param bidder
	 * @throws BiddingException
	 */

	public void addBidder(Bidder bidder) throws BiddingException {
		if (!bidders.add(bidder)) {
			throw new BiddingException("Bidder already exists");
		}
	}

	public int getReservePrice() {
		return reservePrice;
	}

	public Set<Bidder> getBidders() {
		return bidders;
	}
}
