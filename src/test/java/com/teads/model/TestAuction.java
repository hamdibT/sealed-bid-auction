package com.teads.model;

import com.google.common.collect.Lists;
import com.teads.exception.BiddingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAuction {
	private Auction auction;

	@Before
	public void init() {
		auction = new Auction(100);
	}

	@Test
	public void test_Adding_Bidders() {
		Bidder bidder1 = new Bidder("A", Lists.newArrayList(100, 500));
		Bidder bidder2 = new Bidder("B", Lists.newArrayList(200));
		Bidder bidder3 = new Bidder("C", Lists.newArrayList(300, 600));

		auction.addBidder(bidder1);
		auction.addBidder(bidder2);
		auction.addBidder(bidder3);

		Assert.assertEquals("Expecting bidding number in this auction", 3, auction.getBidders().size());
	}

	@Test
	public void test_Adding_Bidder_With_No_Bid() {
		Bidder bidder1 = new Bidder("A", Lists.newArrayList());
		auction.addBidder(bidder1);

		Assert.assertEquals("Expecting bidding number in this auction", 1, auction.getBidders().size());
	}

	@Test(expected = BiddingException.class)
	public void test_Failing_When_Adding_Existing_Bidder() {
		Bidder bidder1 = new Bidder("A", Lists.newArrayList(100));
		Bidder bidder2 = new Bidder("A", Lists.newArrayList(200));

		auction.addBidder(bidder1);
		auction.addBidder(bidder2);
	}
}
