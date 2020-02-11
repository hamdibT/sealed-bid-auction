package com.teads.core;

import com.teads.model.Auction;
import com.teads.model.Winner;

public interface BiddingStrategy {

	/**
	 *
	 * @return the auction's winner
	 */
	Winner apply(Auction auction);
}
